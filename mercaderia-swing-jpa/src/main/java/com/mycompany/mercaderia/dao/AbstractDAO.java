package com.mycompany.mercaderia.dao;

import com.mycompany.mercaderia.model.AbstractEntity;
import java.lang.reflect.ParameterizedType;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public abstract class AbstractDAO<Entity extends AbstractEntity, PK extends Number> {
	
	private Class<Entity> entityClass;
	
	
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	public AbstractDAO(EntityManager em) {
		this.em = em;
		
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<Entity>) genericSuperclass.getActualTypeArguments()[0];
	}
	
	
	public Entity save(Entity e) {
		if (e.getId() != null)
			return em.merge(e);
		else {
			em.persist(e);
			return e;
		}
	}

	
	public void remove(Entity e) {
		em.remove(e);
	}
	

	public Entity findById(PK id) { 
		return em.find(entityClass, id); 
	}
	
	@SuppressWarnings("unchecked")  // JPQL - Java Persistence Query Language
	public List<Entity> getAll() {
		Query query = getPersistenceContext().createQuery("SELECT o FROM "+entityClass.getName() + " o");
		return (List<Entity>) query.getResultList(); 
	}
	

	protected EntityManager getPersistenceContext() {
		return this.em;
	}
}
