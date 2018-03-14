package com.srkapi.order.api.dao.impl;

import com.srkapi.common.dao.impl.GenericDaoImpl;
import com.srkapi.order.api.model.Order;
import com.srkapi.order.api.dao.OrderDao;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
	
	public OrderDaoImpl() {
		super(Order.class);
	}
}
