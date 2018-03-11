package cviewer.cli;

import cviewer.CviewerConfiguration;
import cviewer.git.GitCliClient;
import cviewer.git.GitClientException;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

public class ListCommits extends ConfiguredCommand<CviewerConfiguration> {
	
	public ListCommits() {        
	    super("list", "List git commits for a given url");
	  }

	@Override
	public void configure(Subparser subparser) 
	{
		super.configure(subparser);
		
	    // Add a command line option
	    subparser.addArgument("-u", "--url")
	      .dest("url")
	      .type(String.class)
	      .required(true)
	      .help("The git url to list the commits from");
		
	}


	@Override
	protected void run(Bootstrap<CviewerConfiguration> bootstrap, Namespace namespace,
			CviewerConfiguration configuration) throws Exception {
		
		try
		{
			GitCliClient gitCliClient = new GitCliClient();
			
			gitCliClient.getCommits(namespace.getString("url"), 100);
		}
		// If git client returned an error, let's inform the user
		catch (GitClientException e)
		{
			System.err.println(e.getMessage());
		}
		
		
		
	}


}
