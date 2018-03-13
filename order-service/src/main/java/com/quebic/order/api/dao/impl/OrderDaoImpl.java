package com.quebic.order.api.dao.impl;

import com.quebic.common.dao.impl.GenericDaoImpl;
import com.quebic.order.api.model.Order;
import com.quebic.order.api.dao.OrderDao;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
	
	public OrderDaoImpl() {
		super(Order.class);
	}
}
