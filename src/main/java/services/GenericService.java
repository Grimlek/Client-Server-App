package services;

import javax.persistence.EntityManager;
import util.DatabaseUtil;

/**
 * Created by csexton on 7/18/16.
 */
public abstract class GenericService<T, Id> {
	
	private EntityManager manager;

	private final Class<T> entityType;

	public GenericService(Class<T> entityType) {
		manager = DatabaseUtil.getSessionFactory().createEntityManager();
		this.entityType = entityType;
	}

	public void shutdown() {
		manager.close();
	}

	public void update(T entity) {
		beginTransaction();
		manager.merge(entity);
		saveChanges();
	}

	public void create(T entity) {
		beginTransaction();
		manager.persist(entity);
		saveChanges();
	}

	public void delete(T entity) {
		beginTransaction();
		manager.remove(entity);
		saveChanges();
	}

	public T findById(Id id) {
		return manager.find(entityType, id);
	}
	
	protected void beginTransaction() {
		manager.getTransaction().begin();
	}

	protected void saveChanges() {
		manager.getTransaction().commit();
	}
	
	protected EntityManager getManager() {
		return this.manager;
	}
}
