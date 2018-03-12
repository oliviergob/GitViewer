package cviewer.git;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cviewer.data.GitCommit;

/**
 * Git API client allowing to list git commit using git CLI
 * This class assumes the application runs on a linux environment
 */
public class GitApiClient implements GitClient {

	public static final Logger LOGGER = LoggerFactory.getLogger(GitApiClient.class);


	
	@Override
	public List<GitCommit> getCommits(String url, int maxCommits) throws GitClientException {
		
		// TODO Implement API Call
		
		throw new GitClientException("Not Yet Implemented");

	}

}
