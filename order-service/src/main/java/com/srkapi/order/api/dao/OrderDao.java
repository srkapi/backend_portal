package com.srkapi.order.api.dao;

import com.srkapi.common.dao.GenericDao;
import com.srkapi.order.api.model.Order;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDao extends GenericDao<Order> {

}