package com.srkapi.product.api.controller;

import com.srkapi.common.util.ControllerBase;
import com.srkapi.product.api.dto.ProductDto;
import com.srkapi.product.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController extends ControllerBase{

	@Autowired
	private ProductService productService;
	
	@GetMapping
    public Flux<ProductDto> getAll() {
	    return  this.productService.getAll();

    }
	
	@GetMapping("/{id}")
    public Mono<ProductDto> getById(@PathVariable("id") String id) {
	    return this.productService.getById(id);
    }
	
	@PostMapping
    public Mono<ProductDto> add(@RequestBody ProductDto product) {
	    return this.productService.add(product);
    }
    
    @PutMapping
    public Mono<ProductDto> edit(@RequestBody ProductDto product) {
	    return this.productService.edit(product);
    }
    
}
