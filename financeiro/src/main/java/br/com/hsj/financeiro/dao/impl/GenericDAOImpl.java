package br.com.hsj.financeiro.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.hsj.financeiro.dao.GenericDAO;

/**
 * 
 * @author Hamilton dos Santos Junior
 *
 * Classe que implementa os métodos genéricos para todos os DAOs
 * @param <K> Id
 * @param <E> Classe
 */
@SuppressWarnings("serial")
public abstract class GenericDAOImpl<K extends Serializable, E> implements GenericDAO<K, E>, Serializable {
	protected Class<E> entityClass;

	@Autowired
	protected SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass
				.getActualTypeArguments()[1];
	}

	public void persist(E entity) {
		sessionFactory.getCurrentSession().persist(entity);
	}

	public void remove(E entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public E merge(E entity) {
		return (E) sessionFactory.getCurrentSession().merge(entity);
	}

	public void refresh(E entity) {
		sessionFactory.getCurrentSession().refresh(entity);
	}

	@SuppressWarnings("unchecked")
	public E findById(K id) {
		return (E) sessionFactory.getCurrentSession().get(entityClass, id);
	}

	public E flush(E entity) {
		sessionFactory.getCurrentSession().flush();
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		Query q = sessionFactory.getCurrentSession().createQuery("SELECT h FROM "
				+ entityClass.getName() + " h");
		return q.list();
	}
}