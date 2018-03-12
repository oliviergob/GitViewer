package cviewer.dao.repository;

import java.util.List;

import cviewer.dao.Dao;

public interface RepositoryDao extends Dao< GitRepository > {
	
	public List<GitRepository> findByUrl(String repoUrl);

}
