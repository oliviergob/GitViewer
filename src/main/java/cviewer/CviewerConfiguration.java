package cviewer;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class CviewerConfiguration extends Configuration {
	
	@NotEmpty
	/** Application Name **/
	private String appName = "Git Commit Viewer";
	
	/** Maximum number of commits returned in one call **/
	private int maxCommitsPerCall = 100;

	@Valid
    @NotNull
	private DataSourceFactory database = new DataSourceFactory();

	@JsonProperty("database")
	public DataSourceFactory getDataSourceFactory() {
        return database;
    }
	
	@JsonProperty
	public void setDatabase(DataSourceFactory database) {
	    this.database = database;
	}
	

	@JsonProperty
	public String getAppName() {
		return appName;
	}

	@JsonProperty
	public void setAppName(String appName) {
		this.appName = appName;
	}

	@JsonProperty
	public int getMaxCommitsPerCall() {
		return maxCommitsPerCall;
	}

	@JsonProperty
	public void setMaxCommitsPerCall(int maxCommit) {
		this.maxCommitsPerCall = maxCommit;
	}

}
