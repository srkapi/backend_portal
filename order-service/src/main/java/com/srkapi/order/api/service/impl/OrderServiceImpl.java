package com.srkapi.order.api.service.impl;

import com.srkapi.common.service.impl.GenericServiceImpl;
import com.srkapi.order.api.dao.OrderDao;
import com.srkapi.order.api.dto.OrderDto;
import com.srkapi.order.api.model.Order;
import com.srkapi.order.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

import javax.annotation.PostConstruct;

@Service
public class OrderServiceImpl extends GenericServiceImpl<Order,OrderDto> implements OrderService {
	
	@Autowired
	private OrderDao productDao;
	
	@PostConstruct
	void init() {
		init(Order.class, productDao);
	}
	
	@Override
	public Single<OrderDto> add(Order order) {
		return super.add(order);
	}



	@Override
	public Single<OrderDto> edit(Order order) {
		return super.edit(order);
	}




}
