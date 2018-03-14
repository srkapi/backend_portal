package com.srkapi.order.api.controller;

import com.srkapi.common.async.response.AsyncResponseEntity;
import com.srkapi.common.util.ControllerBase;
import com.srkapi.order.api.dto.OrderDto;
import com.srkapi.order.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController extends ControllerBase{

	@Autowired
	private OrderService orderService;
	
	@RequestMapping
    public AsyncResponseEntity<OrderDto> getAll() {
	    return makeAsyncResponse(orderService.getAll().map(order -> order.toDto()));
    }
	
	@RequestMapping("/{id}")
    public AsyncResponseEntity<OrderDto> getById(@PathVariable("id") String id) {
		return makeAsyncResponse(orderService.getById(id));
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public AsyncResponseEntity<OrderDto> add(@RequestBody OrderDto order) {
    	return makeAsyncResponse(orderService.add(order.toModel()), HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public AsyncResponseEntity<OrderDto> edit(@RequestBody OrderDto order) {
    	return makeAsyncResponse(orderService.edit(order.toModel()), HttpStatus.ACCEPTED);
    }
    
}
