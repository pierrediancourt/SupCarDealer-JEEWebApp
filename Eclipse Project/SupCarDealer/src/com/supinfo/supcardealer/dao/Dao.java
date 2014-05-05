package com.supinfo.supcardealer.dao;

import java.util.ArrayList;

public abstract class Dao<T> {
	
	public abstract T find(long id);
	public abstract ArrayList<T> findAll();
	public abstract void add(T obj);
	public abstract void update(T obj);
	public abstract void remove(long id);
}
