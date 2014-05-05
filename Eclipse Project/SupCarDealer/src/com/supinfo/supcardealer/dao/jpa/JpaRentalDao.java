package com.supinfo.supcardealer.dao.jpa;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.supinfo.supcardealer.dao.Dao;
import com.supinfo.supcardealer.entities.Rental;

public class JpaRentalDao extends Dao<Rental> {

	private EntityManagerFactory emf;
	
	public JpaRentalDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Rental find(long id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Rental.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Rental> findAll() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT r FROM Rental r");
		return (ArrayList<Rental>) query.getResultList();
	}

	@Override
	public void add(Rental obj) {
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
	public void update(Rental obj) {
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
			Rental c = em.merge(find(id));
			em.remove(c);
			t.commit();
		} finally {
			if (t.isActive()) t.rollback();
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Rental> findRentalsByCarId(long carId) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT r FROM Rental r WHERE r.rentedCarId = :carId");
		query.setParameter("carId", carId);
		try {
		return (ArrayList<Rental>) query.getResultList();
		} catch(Exception e) {
			return null;
		}
	}
	
}
