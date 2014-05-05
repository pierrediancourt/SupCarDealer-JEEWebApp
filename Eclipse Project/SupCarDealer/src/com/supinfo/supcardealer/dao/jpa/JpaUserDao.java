package com.supinfo.supcardealer.dao.jpa;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.supinfo.supcardealer.dao.Dao;
import com.supinfo.supcardealer.entities.User;

public class JpaUserDao extends Dao<User> {

	private EntityManagerFactory emf;
	
	public JpaUserDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public User find(long id) {
		EntityManager em = emf.createEntityManager();
		return em.find(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<User> findAll() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT u FROM User u");
		return (ArrayList<User>) query.getResultList();
	}

	@Override
	public void add(User obj) {
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
	public void update(User obj) {
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
			User c = em.merge(find(id));
			em.remove(c);
			t.commit();
		} finally {
			if (t.isActive()) t.rollback();
			em.close();
		}
	}
	
	public User findUserByEmail(String email) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email");
		query.setParameter("email", email);
		try {
		return (User) query.getSingleResult();
		} catch(Exception e) {
			return null;
		}
	}
}
