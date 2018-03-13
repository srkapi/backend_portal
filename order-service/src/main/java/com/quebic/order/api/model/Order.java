package com.quebic.order.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.quebic.common.model.EntityBase;

@Document(collection = "Order")
public class Order extends EntityBase{

	private String sellerId;
	private String name;
	private double price;
	
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
