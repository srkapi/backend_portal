package com.srkapi.product.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.srkapi.common.async.response.AsyncResponseEntity;
import com.srkapi.common.util.ControllerBase;
import com.srkapi.product.api.model.Product;
import com.srkapi.product.api.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController extends ControllerBase{

	@Autowired
	private ProductService productService;
	
	@RequestMapping
    public AsyncResponseEntity<Product> getAll() {
		return makeAsyncResponse(productService.getAll());
    }
	
	@RequestMapping("/{id}")
    public AsyncResponseEntity<Product> getById(@PathVariable("id") String id) {
		return makeAsyncResponse(productService.getById(id));
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public AsyncResponseEntity<Product> add(@ModelAttribute Product product) {
    	return makeAsyncResponse(productService.add(product), HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public AsyncResponseEntity<Product> edit(@ModelAttribute Product product) {
    	return makeAsyncResponse(productService.edit(product), HttpStatus.ACCEPTED);
    }
    
}
