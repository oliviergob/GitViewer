package cviewer.dao.commit;

import java.util.List;

import cviewer.dao.Dao;
import cviewer.data.GitCommit;

public interface CommitDao extends Dao< GitCommit > {
	
	public List<GitCommit> findByRepoUrl(String repoUrl);

}
