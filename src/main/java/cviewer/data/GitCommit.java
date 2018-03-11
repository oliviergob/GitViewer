package cviewer.data;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

/**
 * This class represent a git commit
 *
 */
public class GitCommit {

	private String sha;

	private DateTime authorDate;

	private String author;

	private String authorEmail;

	private String titleLine;

	private String branch;

	private String repositoryUrl;

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public DateTime getAuthorDate() {
		return authorDate;
	}

	public void setAuthorDate(DateTime authorDate) {
		this.authorDate = authorDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitleLine() {
		return titleLine;
	}

	public void setTitleLine(String titleLine) {
		this.titleLine = titleLine;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getRepositoryUrl() {
		return repositoryUrl;
	}

	public void setRepositoryUrl(String repositoryUrl) {
		this.repositoryUrl = repositoryUrl;
	}

	public String toString() {
		final char separator = '|';
		return new StringBuilder().append(sha)
				.append(separator)
				.append(author)
				.append(separator)
				.append(authorEmail)
				.append(separator)
				.append(authorDate)
				.append(separator)
				.append(titleLine)
				.toString();
	}

}
