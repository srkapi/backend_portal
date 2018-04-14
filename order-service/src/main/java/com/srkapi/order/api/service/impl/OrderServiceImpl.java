package com.srkapi.order.api.service.impl;

import com.srkapi.common.service.impl.GenericServiceImpl;
import com.srkapi.order.api.dao.OrderDao;
import com.srkapi.order.api.dto.OrderDto;
import com.srkapi.order.api.model.Order;
import com.srkapi.order.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl extends GenericServiceImpl<Order,OrderDto> implements OrderService {
	
	@Autowired
	private OrderDao productDao;
	

	@Override
	public Mono<OrderDto> add(OrderDto order) {
		return super.add(order);
	}



	@Override
	public Mono<OrderDto> edit(OrderDto order) {
		return super.edit(order);
	}


	@Override
	public OrderDto toDto(Order model){
	OrderDto orderModel = new OrderDto();
		orderModel.setId(model.getId());
		orderModel.setPrice(model.getPrice());
		orderModel.setCoordinateFinish(model.getCoordinateFinish());
		orderModel.setCoordinateInit(model.getCoordinateInit());
		orderModel.setDischargeDate(model.getDischargeDate());
		orderModel.setIdOwner(model.getIdOwner());
		orderModel.setIdTransport(model.getIdTransport());
		orderModel.setLoadType(model.getLoadType());
		return orderModel;
	}

	@Override
	public Order toModel(OrderDto Dto) {
		Order orderModel = new Order();
		orderModel.setPrice(Dto.getPrice());
		orderModel.setCoordinateFinish(Dto.getCoordinateFinish());
		orderModel.setCoordinateInit(Dto.getCoordinateInit());
		orderModel.setDischargeDate(Dto.getDischargeDate());
		orderModel.setIdOwner(Dto.getIdOwner());
		orderModel.setIdTransport(Dto.getIdTransport());
		orderModel.setLoadType(Dto.getLoadType());
		return orderModel;

	}
}
