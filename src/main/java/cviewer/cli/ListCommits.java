package cviewer.cli;

import java.util.List;

import com.scottescue.dropwizard.entitymanager.EntityManagerBundle;

import cviewer.CviewerConfiguration;
import cviewer.data.DataAccessException;
import cviewer.data.GitCommit;
import cviewer.data.GitRepositoryDataManager;
import io.dropwizard.Application;
import io.dropwizard.cli.EnvironmentCommand;
import io.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

public class ListCommits extends EnvironmentCommand<CviewerConfiguration> {

	private EntityManagerBundle<CviewerConfiguration> entityManagerBundle;
	
	public ListCommits(Application<CviewerConfiguration> application, String name, String description, EntityManagerBundle<CviewerConfiguration> entityManagerBundle) {
		super(application, name, description);
		
		this.entityManagerBundle = entityManagerBundle;
	}
	
	public ListCommits(Application<CviewerConfiguration> application,  EntityManagerBundle<CviewerConfiguration> entityManagerBundle) {
		super(application, "listCommits", "List all commits");
		
		this.entityManagerBundle = entityManagerBundle;
	}

	

	@Override
	public void configure(Subparser subparser) {
		super.configure(subparser);

		// Add a command line option
		subparser.addArgument("-u", "--url").dest("url").type(String.class).required(true)
				.help("The git url to list the commits from");

	}


	@Override
	protected void run(Environment environment, Namespace namespace, CviewerConfiguration configuration)
			throws Exception {

		try {
			
			entityManagerBundle.getEntityManagerFactory();

			GitRepositoryDataManager repoDataManager = new GitRepositoryDataManager(entityManagerBundle.getEntityManagerFactory());
			List<GitCommit> commits = repoDataManager.getRepositoryCommits(namespace.getString("url"), configuration.getMaxCommitsPerCall());

			if (commits != null)
			{
				for (GitCommit commit : commits) {
					System.out.println(commit);
				}
			}
			
		}
		// If git client returned an error, let's inform the user
		catch (DataAccessException e) {
			System.err.println(e.getMessage());
		}

	}

}
