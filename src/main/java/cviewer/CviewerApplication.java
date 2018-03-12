package cviewer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scottescue.dropwizard.entitymanager.EntityManagerBundle;

import cviewer.cli.ListCommits;
import cviewer.dao.repository.GitRepository;
import cviewer.data.GitCommit;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CviewerApplication extends Application<CviewerConfiguration> {

	public static final Logger LOGGER = LoggerFactory.getLogger(CviewerApplication.class);

	public static void main(final String[] args) throws Exception {
		new CviewerApplication().run(args);
	}

	private String applicationName;
	
	private final EntityManagerBundle<CviewerConfiguration> entityManagerBundle = 
	        new EntityManagerBundle<CviewerConfiguration>(GitRepository.class, GitCommit.class) {
	    @Override
	    public DataSourceFactory getDataSourceFactory(CviewerConfiguration configuration) {
	        return configuration.getDataSourceFactory();
	    }
	};

	@Override
	public String getName() {
		return applicationName;
	}

	@Override
	public void initialize(Bootstrap<CviewerConfiguration> bootstrap) {
		bootstrap.addBundle(entityManagerBundle);
		bootstrap.addCommand(new ListCommits(this, entityManagerBundle));
		

	}

	@Override
	public void run(CviewerConfiguration configuration, Environment environment) throws Exception {
		applicationName = configuration.getAppName();
	}

}
