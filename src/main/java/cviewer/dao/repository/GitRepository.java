package cviewer.dao.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "GIT_REPOSITORY")
public class GitRepository {
		
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy= GenerationType.TABLE)
	private Long id;
	
	@Column(name = "REPOSITORY_URL")
	private String repositoryUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRepositoryUrl() {
		return repositoryUrl;
	}

	public void setRepositoryUrl(String repositoryUrl) {
		this.repositoryUrl = repositoryUrl;
	}
	
	

}
