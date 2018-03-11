package cviewer;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class CviewerConfiguration extends Configuration {
	
	@NotEmpty
	/** Application Name **/
	private String appName = "Git Commit Viewer";
	
	/** Maximum number of commits returned in one call **/
	private int maxCommitsPerCall = 100;

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
