package com.srkapi.product.api.service.impl;

import com.srkapi.common.dao.impl.GenericRepositoryMongoImpl;
import com.srkapi.common.service.impl.GenericServiceImpl;
import com.srkapi.product.api.dao.ProductDao;
import com.srkapi.product.api.dto.ProductDto;
import com.srkapi.product.api.model.Product;
import com.srkapi.product.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product,ProductDto> implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public ProductDto toDto(Product model) {
		return null;
	}

	@Override
	public Product toModel(ProductDto Dto) {
		return null;
	}

	@Override
	public GenericRepositoryMongoImpl<Product> getRepository() {
		return this.productDao;
	}
}
