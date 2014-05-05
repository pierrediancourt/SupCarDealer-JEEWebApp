package com.supinfo.supcardealer.dao.jpa;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.supinfo.supcardealer.dao.Dao;
import com.supinfo.supcardealer.entities.Car;

public class JpaCarDao extends Dao<Car> {

	private EntityManagerFactory emf;
	
	public JpaCarDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Car find(long id) {
		return emf.createEntityManager().find(Car.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Car> findAll() {
		return (ArrayList<Car>) emf	.createEntityManager()
									.createQuery("SELECT c FROM Car c")
									.getResultList();
	}

	@Override
	public void add(Car obj) {
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
	public void update(Car obj) {
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
			Car c = em.merge(find(id));
			em.remove(c);
			t.commit();
		} finally {
			if (t.isActive()) t.rollback();
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Car> findCarsByCategory(long id) {
		Query query = emf	.createEntityManager()
							.createQuery("SELECT c FROM Car c WHERE c.category.id = :id")
							.setParameter("id", id);
		try {
			return (ArrayList<Car>) query.getResultList();
		} catch(Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Car> findNewestCars(int nbCars) {
		return (ArrayList<Car>) emf	.createEntityManager()
									.createQuery("SELECT c FROM Car c ORDER BY c.id DESC")
									.setMaxResults(nbCars)
									.getResultList();
	}
}
