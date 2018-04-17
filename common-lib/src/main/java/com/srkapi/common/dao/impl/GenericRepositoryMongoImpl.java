package com.srkapi.common.dao.impl;

import com.srkapi.common.dao.ReactiveRepositoryMongo;
import org.springframework.stereotype.Repository;

@Repository
public  interface GenericRepositoryMongoImpl<T> extends ReactiveRepositoryMongo<T> {
}
