package cviewer.git;

import java.util.List;

import cviewer.data.GitCommit;

public interface GitClient {
	
	public List<GitCommit> getCommits(String url, int maxCommits) throws GitClientException ;

}
