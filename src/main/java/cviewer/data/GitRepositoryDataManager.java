package cviewer.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cviewer.dao.commit.CommitDao;
import cviewer.dao.commit.GitCommitJpaDao;
import cviewer.dao.repository.GitRepository;
import cviewer.dao.repository.GitRepositoryJpaDao;
import cviewer.dao.repository.RepositoryDao;
import cviewer.git.GitCliClient;
import cviewer.git.GitClient;
import cviewer.git.GitClientManager;

public class GitRepositoryDataManager {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(GitRepositoryDataManager.class);
	
	private EntityManagerFactory entityManagerFactory;
	
	
	
	public GitRepositoryDataManager(EntityManagerFactory entityManagerFactory) {
		super();
		this.entityManagerFactory = entityManagerFactory;
	}


	public List<GitCommit> getRepositoryCommits(String url, int maxCommits) throws DataAccessException
	{
		
		//EntityManagerFactory factory = null;
        EntityManager entityManager = null;
        List<GitCommit> commits = null;
		try
		{
			//factory = Persistence.createEntityManagerFactory("postgres-dev");
	        entityManager = entityManagerFactory.createEntityManager();
	        
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
	        
	        RepositoryDao repoDao = new GitRepositoryJpaDao(entityManager);
	        
	        List<GitRepository> reposList = repoDao.findByUrl(url);
	        
	        // If the repository is not persisted yet
	        if ((reposList == null) || (reposList.isEmpty()))
	        {
	        	if (LOGGER.isDebugEnabled())
	        		LOGGER.debug(String.format("Repository %s does not exists yet", url));
	        	
	        	// Let's create a new GitRepository
	        	GitRepository repository = new GitRepository();
	        	repository.setRepositoryUrl(url);
	        	// Let's persist it
	        	repoDao.create(repository);
	        	
	        	if (LOGGER.isDebugEnabled())
	        		LOGGER.debug(String.format("Repository %s has been persisted", url));
	        	
	        	// Now let's get the commits from git
	        	GitClient gitClient = new GitClientManager();
	        	
				commits = gitClient.getCommits(url,maxCommits);
				
				// And let's persist them
				CommitDao commitDao = new GitCommitJpaDao(entityManager);
				for (GitCommit commit : commits) {
					commit.setRepositoryUrl(url);
					commitDao.create(commit);
				}
	        	
	        	entityManager.getTransaction().commit();
	        }
	        // If already persisted, let's look for commits in the database
	        else
	        {
	        	CommitDao commitDao = new GitCommitJpaDao(entityManager);
	        	commits = commitDao.findByRepoUrl(url);
	        }
	        
		}
		catch (Exception e)
		{
			LOGGER.error("Database access error", e);
			throw new DataAccessException("Database access error: "+e.getMessage(), e);
			
		}
		finally {
			try {entityManager.close();} catch (Exception e){}
		}
		  
		return commits;
        
	}

}
