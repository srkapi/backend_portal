package com.srkapi.product.api.dao;


import com.srkapi.common.dao.impl.GenericRepositoryMongoImpl;
import com.srkapi.product.api.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends GenericRepositoryMongoImpl<Product> {

}
