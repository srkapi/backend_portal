package com.quebic.order.api.controller;

import com.quebic.common.async.response.AsyncResponseEntity;
import com.quebic.common.util.ControllerBase;
import com.quebic.order.api.model.Order;
import com.quebic.order.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController extends ControllerBase{

	@Autowired
	private OrderService orderService;
	
	@RequestMapping
    public AsyncResponseEntity<Order> getAll() {
		return makeAsyncResponse(orderService.getAll());
    }
	
	@RequestMapping("/{id}")
    public AsyncResponseEntity<Order> getById(@PathVariable("id") String id) {
		return makeAsyncResponse(orderService.getById(id));
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public AsyncResponseEntity<Order> add(@ModelAttribute Order order) {
    	return makeAsyncResponse(orderService.add(order), HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public AsyncResponseEntity<Order> edit(@ModelAttribute Order Order) {
    	return makeAsyncResponse(orderService.edit(Order), HttpStatus.ACCEPTED);
    }
    
}
