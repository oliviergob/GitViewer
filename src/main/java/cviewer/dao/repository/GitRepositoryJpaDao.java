package cviewer.dao.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import cviewer.dao.JpaDAO;

public class GitRepositoryJpaDao extends JpaDAO<GitRepository> implements RepositoryDao {

	public GitRepositoryJpaDao(EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GitRepository> findByUrl(String repoUrl){
		Query q = entityManager.createQuery("SELECT e FROM " + GitRepository.class.getName()+" e where repository_url = :url ");
		q.setParameter("url", repoUrl);

		return (List<GitRepository>) q.getResultList();
	}

}
