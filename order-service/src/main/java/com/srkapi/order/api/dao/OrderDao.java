package com.srkapi.order.api.dao;

import com.srkapi.common.dao.impl.GenericRepositoryMongoImpl;
import com.srkapi.order.api.model.Order;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDao extends GenericRepositoryMongoImpl<Order> {

}