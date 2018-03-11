package cviewer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cviewer.cli.ListCommits;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CviewerApplication extends Application<CviewerConfiguration> {

	public static final Logger LOGGER = LoggerFactory.getLogger(CviewerApplication.class);

	public static void main(final String[] args) throws Exception {
		new CviewerApplication().run(args);
	}

	private String applicationName;

	@Override
	public String getName() {
		return applicationName;
	}

	@Override
	public void initialize(Bootstrap<CviewerConfiguration> bootstrap) {
		bootstrap.addCommand(new ListCommits());

	}

	@Override
	public void run(CviewerConfiguration configuration, Environment environment) throws Exception {
		applicationName = configuration.getAppName();
	}

}
