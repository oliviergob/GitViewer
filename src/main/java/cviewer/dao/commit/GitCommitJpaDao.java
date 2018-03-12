package cviewer.dao.commit;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import cviewer.dao.JpaDAO;
import cviewer.data.GitCommit;

public class GitCommitJpaDao extends JpaDAO<GitCommit> implements CommitDao {

	public GitCommitJpaDao(EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GitCommit> findByRepoUrl(String repoUrl) {
		Query q = entityManager.createQuery("SELECT e FROM GitCommit e WHERE repository_url = :repo");
		q.setParameter("repo", repoUrl);

		return (List<GitCommit>) q.getResultList();
	}

}
