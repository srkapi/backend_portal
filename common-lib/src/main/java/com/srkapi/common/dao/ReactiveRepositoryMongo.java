package com.srkapi.common.dao;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReactiveRepositoryMongo<T> extends ReactiveMongoRepository<T,String> {
}
