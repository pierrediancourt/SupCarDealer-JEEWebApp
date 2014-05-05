package com.supinfo.supcardealer.dao;

import com.supinfo.supcardealer.dao.jpa.JpaCarDao;
import com.supinfo.supcardealer.dao.jpa.JpaCategoryDao;
import com.supinfo.supcardealer.dao.jpa.JpaRentalDao;
import com.supinfo.supcardealer.dao.jpa.JpaUserDao;
import com.supinfo.supcardealer.utils.PersistenceManager;


public class DaoFactory {

	private DaoFactory() {}
	
	public static JpaCarDao getJpaCarDao() {
		return new JpaCarDao(PersistenceManager.get());
	}
	
	public static JpaCategoryDao getJpaCategoryDao() {
		return new JpaCategoryDao(PersistenceManager.get());
	}
	
	public static JpaUserDao getJpaUserDao() {
		return new JpaUserDao(PersistenceManager.get());
	}
	
	public static JpaRentalDao getJpaRentalDao() {
		return new JpaRentalDao(PersistenceManager.get());
	}
	
}
