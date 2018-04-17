package com.srkapi.order.api.controller;

import com.srkapi.common.util.ControllerBase;
import com.srkapi.order.api.dto.OrderDto;
import com.srkapi.order.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
public class OrderController extends ControllerBase{

	@Autowired
	private OrderService orderService;
	
	@GetMapping
    public Flux<OrderDto> getAll() {
	    return this.orderService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<OrderDto> getById(@PathVariable("id") String id) {
	    return this.orderService.getById(id);
    }
	
	@PostMapping
    public Mono<OrderDto> add(@RequestBody OrderDto order) {
	    return this.orderService.add(order);
    }
    
    @PutMapping
    public Mono<OrderDto> edit(@RequestBody OrderDto order) {
	    return this.orderService.edit(order);
    }
    
}
