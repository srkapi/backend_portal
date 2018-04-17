package com.srkapi.product.api.service.impl;

import com.srkapi.common.service.impl.GenericServiceImpl;
import com.srkapi.product.api.dto.ProductDto;
import com.srkapi.product.api.model.Product;
import com.srkapi.product.api.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product,ProductDto> implements ProductService {


	@Override
	public ProductDto toDto(Product model) {
		return null;
	}

	@Override
	public Product toModel(ProductDto Dto) {
		return null;
	}
}
