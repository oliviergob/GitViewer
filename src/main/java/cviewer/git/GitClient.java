package cviewer.git;

import java.util.List;

import cviewer.data.GitCommit;

/**
 * This Interface represents a Git Client
 */
public interface GitClient {
	
	public List<GitCommit> getCommits(String url, int maxCommits) throws GitClientException ;

}
