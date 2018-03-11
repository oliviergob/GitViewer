package cviewer.data;

import java.util.Date;

/**
 * This class represent a git commit
 *
 */
public class GitCommit {
	
	private String sha;
	
	private Date authorDate;
	
	private String author;
	
	private String titleLine;
	
	private String branch;
	
	private String repository;

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public Date getAuthorDate() {
		return authorDate;
	}

	public void setAuthorDate(Date authorDate) {
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

	public String getRepository() {
		return repository;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}
	
	

}
