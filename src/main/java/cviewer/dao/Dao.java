package cviewer.dao;

public interface Dao< T > {
	
    public void create(T entity);
    
    public void update(T entity);
    
    public void delete(T entity);

}