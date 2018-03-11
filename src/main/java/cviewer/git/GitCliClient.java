package cviewer.git;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cviewer.data.GitCommit;

public class GitCliClient implements GitClient {

	public static final Logger LOGGER = LoggerFactory.getLogger(GitCliClient.class);

	@Override
	public List<GitCommit> getCommits(String url, int maxCommits) throws GitClientException {
		
		// Let's generate a temporary path and ensure it does not exist
		String tempDirPath = "/tmp/" + url.hashCode();
		File file = new File(tempDirPath);
		
		if (LOGGER.isDebugEnabled())
			LOGGER.debug(String.format("Cloning %s in temp directory %s", url, tempDirPath ));

		try {
			// If temp directory already exists, let's delete it
			if (file.exists())
				FileUtils.deleteDirectory(file);
			
			String output = executeCommand(String.format("git clone %s %s --depth=%d", url, tempDirPath , maxCommits));
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

		return null;
	}

	public String executeCommand(String command) throws GitClientException {

		String s = null;
		StringBuffer output = new StringBuffer();

		try {

			// using the Runtime exec method:
			Process process = Runtime.getRuntime().exec(command);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			int exitVal = process.waitFor();

			// read the output from the command
			while ((s = stdInput.readLine()) != null) {
				output.append(s);
			}

			// read any errors from the attempted command
			while ((s = stdError.readLine()) != null) {
				output.append(s);
			}

			if (exitVal != 0)
				throw new GitClientException(output.toString());

		} catch (IOException e) {
			throw new GitClientException(output.toString(), e);
		} catch (InterruptedException e) {
			throw new GitClientException(output.toString(), e);
		}

		return output.toString();

	}

}
