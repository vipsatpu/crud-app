package com.mycompany.asset.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.PersistentClass;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	protected Criteria createEntityCriteria() {

		System.out.println(persistentClass.getName());
		System.out.println(persistentClass.getSimpleName());
		System.out.println(persistentClass.getClass());
		System.out.println(persistentClass.getCanonicalName());
		// Create Criteria
		CriteriaQuery criteria = getSession().getCriteriaBuilder().createQuery(persistentClass);
		Root pClass = criteria.from(persistentClass);
		criteria.select(pClass);
		return (Criteria) criteria.select(pClass);
		// return getSession().createCriteria(persistentClass);
	}

	protected Criteria createEntityCriteriawithAlias(String aliasName) {
		return getSession().createCriteria(persistentClass, aliasName);
	}

}
