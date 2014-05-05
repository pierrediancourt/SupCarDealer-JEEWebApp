package com.supinfo.supcardealer.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="rentals")
public class Rental implements Serializable {
	
	private static final long serialVersionUID = -5546981130301989187L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long rentedCarId;
	private long renterId;
	private String startDate;
	private String endDate;
	private float price;
	
	public Rental() {}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getRentedCarId() {
		return rentedCarId;
	}
	public void setRentedCarId(long rentedCarId) {
		this.rentedCarId = rentedCarId;
	}
	
	public long getRenterId() {
		return renterId;
	}
	
	public void setRenterId(long renterId) {
		this.renterId = renterId;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
}
