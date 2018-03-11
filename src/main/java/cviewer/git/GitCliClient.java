package cviewer.git;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cviewer.data.GitCommit;

/**
 * Git CLI client allowing to list git commit using git CLI
 * This class assumes the application runs on a linux environment
 */
public class GitCliClient implements GitClient {

	public static final Logger LOGGER = LoggerFactory.getLogger(GitCliClient.class);


	/**
	 * @param command - the command to be executed
	 * @param workDirectory - the directory where to execute the command, can be null
	 * @return - A List of String, one per line that the command generated
	 * @throws GitClientException
	 */
	private List<String> executeCommand(String command, File workDirectory) throws GitClientException {

		String s = null;
		List<String> output = new ArrayList<>();
		StringBuffer error = new StringBuffer();
		

		try {

			// using the Runtime exec method:
			Process process = Runtime.getRuntime().exec(command, null, workDirectory);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			int exitVal = process.waitFor();

			// read the output from the command
			while ((s = stdInput.readLine()) != null) {
				output.add(s);
			}

			// read any errors from the attempted command
			while ((s = stdError.readLine()) != null) {
				error.append(s);
			}

			if (exitVal != 0)
				throw new GitClientException(error.toString());

		} catch (IOException e) {
			throw new GitClientException(error.toString(), e);
		} catch (InterruptedException e) {
			throw new GitClientException(error.toString(), e);
		}

		return output;

	}
	
	/**
	 * Parse a commit line received from the command line into a GitCommit object 
	 * @param commitString
	 * @return a GitCommit object or null if the line could not be parsed
	 */
	private GitCommit parseCommits(String commitString)
	{
		
		GitCommit commit = new GitCommit();
		
		if (LOGGER.isDebugEnabled())
			LOGGER.debug(String.format("Parsing line %s", commitString ));
		
		String[] fields = commitString.split("\\|");
		
		if (fields.length < 5)
		{
			LOGGER.warn(String.format("Unable to parse line %s, not enough fields", commitString ));
			return null;
		}
		
		commit.setSha(fields[0]);
		commit.setAuthor(fields[1]);
		commit.setAuthorEmail(fields[2]);
		// Trying to convert the timestamp
		try{
			long authorTimestamp = Long.valueOf(fields[3]).longValue();
			commit.setAuthorDate(new DateTime(authorTimestamp*1000));
		}
		catch (NumberFormatException e)
		{
			LOGGER.warn(String.format("Unable to parse line %s, invalid timestamp", commitString,fields[2] ));
			return null;
		}
		// TODO - support for pipes in commit messages (risk of losing part of the title line)
		commit.setTitleLine(fields[4]);
		// TODO - support for branches
		commit.setBranch(null);
		
		return commit;
	}

	
	@Override
	public List<GitCommit> getCommits(String url, int maxCommits) throws GitClientException {
		
		List<GitCommit> commits = new ArrayList<>();
		
		// Let's generate a temporary path and ensure it does not exist
		String tempDirPath = "/tmp/" + url.hashCode();
		File tempDir = new File(tempDirPath);
		
		if (LOGGER.isDebugEnabled())
			LOGGER.debug(String.format("Cloning %s in temp directory %s", url, tempDirPath ));

		try {
			// If temp directory already exists, let's delete it
			if (tempDir.exists())
				FileUtils.deleteDirectory(tempDir);
			
			// Cloning the repository
			executeCommand(String.format("git clone %s %s --depth=%d", url, tempDirPath , maxCommits), null);
			
			if (LOGGER.isDebugEnabled())
				LOGGER.debug(String.format("Listing commits for %s", url ));
			
			// Listing the commits
			List<String> commitStringList = executeCommand("git log --max-count="+maxCommits+" --pretty=format:%H|%aN|%ae|%at|%s", tempDir);
			
			for (String commitString : commitStringList)
			{
				// Parsing the commit
				GitCommit commit = parseCommits(commitString);
				// If the commit could be parsed
				if (commit != null)
					commits.add(commit);
				
			}
			
		} 
		// If exception, let's log it and propagate it
		catch (GitClientException e) {
			LOGGER.warn(e.getMessage(), e);
			throw e;
		} catch (IOException e) {
			String msg = String.format("Error while trying to delete temp directory %s",tempDirPath);
			LOGGER.warn(msg, e);
			throw new GitClientException(msg,e);
		}

		return commits;
	}

}
