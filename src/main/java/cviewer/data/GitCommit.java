package cviewer.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

/**
 * This class represent a git commit
 *
 */
@Entity
@Table(name = "GIT_COMMIT")
public class GitCommit {

	@Column(name = "SHA")
	@Id
	private String sha;

	@Column(name = "AUTHOR_DATE")
	private DateTime authorDate;

	@Column(name = "AUTHOR")
	private String author;

	@Column(name = "AUTHOR_EMAIL")
	private String authorEmail;

	@Column(name = "TITLE_LINE")
	private String titleLine;

	@Column(name = "BRANCH")
	private String branch;

	@Column(name = "REPOSITORY_URL")
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
