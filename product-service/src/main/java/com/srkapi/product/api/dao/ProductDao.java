package com.srkapi.product.api.dao;

import com.srkapi.common.dao.GenericDao;
import com.srkapi.product.api.model.Product;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDao extends GenericDao<Product> {

}
