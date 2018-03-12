package cviewer.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class JpaDAO<T> implements Dao<T> {

	@PersistenceContext
	protected EntityManager entityManager;
	
	public JpaDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public void create(T entity) {
		entityManager.persist(entity);
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}
}
