package com.supinfo.supcardealer.dao.jpa;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.supinfo.supcardealer.dao.Dao;
import com.supinfo.supcardealer.entities.Category;

public class JpaCategoryDao extends Dao<Category> {

	private EntityManagerFactory emf;
	
	public JpaCategoryDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Category find(long id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Category.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Category> findAll() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT c FROM Category c");
		return (ArrayList<Category>) query.getResultList();
	}

	@Override
	public void add(Category obj) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.persist(obj);
			t.commit();
		} finally {
			if (t.isActive()) t.rollback();
			em.close();
		}
	}

	@Override
	public void update(Category obj) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.merge(obj);
			t.commit();
		} finally {
			if (t.isActive()) t.rollback();
			em.close();
		}
	}

	@Override
	public void remove(long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			Category c = em.merge(find(id));
			em.remove(c);
			t.commit();
		} finally {
			if (t.isActive()) t.rollback();
			em.close();
		}
	}
	
}
