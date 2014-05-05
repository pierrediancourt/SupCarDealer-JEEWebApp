package com.supinfo.supcardealer.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.supinfo.supcardealer.globals.Globals;

public class PersistenceManager {
	
	private static EntityManagerFactory emf;
	
	public static EntityManagerFactory get() {
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory(Globals.PERSISTANCE_UNIT_NAME);
		}
		return emf;
	}
	
	public static void close() {
		emf.close();
	}
}
