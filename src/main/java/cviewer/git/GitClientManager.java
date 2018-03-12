package cviewer.git;

import java.util.List;

import cviewer.data.GitCommit;

public class GitClientManager implements GitClient {
	
	@Override
	public List<GitCommit> getCommits(String url, int maxCommits) throws GitClientException {
		
		GitApiClient gitApiClient = new GitApiClient();
		
		// Let's call the API first
		try
		{
			return gitApiClient.getCommits(url, maxCommits);
		}
		catch (GitClientException e)
		{
			// If the API returned an exception, let's try the CLI
			GitCliClient gitCliClient = new GitCliClient();
			return gitCliClient.getCommits(url, maxCommits);
		}
	}

}
