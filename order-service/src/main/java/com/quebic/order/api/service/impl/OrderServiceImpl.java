package com.quebic.order.api.service.impl;

import com.quebic.common.security.SecurityUtil;
import com.quebic.common.service.impl.GenericServiceImpl;
import com.quebic.order.api.dao.OrderDao;
import com.quebic.order.api.model.Order;
import com.quebic.order.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

import javax.annotation.PostConstruct;

@Service
public class OrderServiceImpl extends GenericServiceImpl<Order> implements OrderService {
	
	@Autowired
	private OrderDao productDao;
	
	@PostConstruct
	void init() {
		init(Order.class, productDao);
	}
	
	@Override
	public Single<Order> add(Order order) {
		String sellerId = SecurityUtil.getAuthUserDetails().getUserId();
		order.setSellerId(sellerId);
		return super.add(order);
	}
	
	@Override
	public Single<Order> edit(Order product) {
		String sellerId = SecurityUtil.getAuthUserDetails().getUserId();
		product.setSellerId(sellerId);
		return super.edit(product);
	}
	
}
