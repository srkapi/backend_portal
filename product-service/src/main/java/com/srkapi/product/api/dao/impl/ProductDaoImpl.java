package com.srkapi.product.api.dao.impl;

import org.springframework.stereotype.Repository;
import com.srkapi.common.dao.impl.GenericDaoImpl;
import com.srkapi.product.api.dao.ProductDao;
import com.srkapi.product.api.model.Product;

@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao{
	
	public ProductDaoImpl() {
		super(Product.class);
	}
}
