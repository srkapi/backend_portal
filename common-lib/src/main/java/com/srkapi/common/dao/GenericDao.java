package com.srkapi.common.dao;

import com.srkapi.common.exception.DataAccessException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.List;

public interface GenericDao<T> extends ReactiveMongoRepository<T, String> {
    /**
     * This method delete given object from the database.
     *
     * @param id - Object id to load
     * @throws DataAccessException - throws if an error occurs
     */
    T getById(Object id) throws DataAccessException;

    /**
     * This method queries all the objects
     *
     * @throws DataAccessException - throws if an error occurs
     */
    List<T> getAll() throws DataAccessException;

    /**
     * This method insert a given object to the database.
     *
     * @param object - instance of Object class
     * @throws DataAccessException - throws if an error occurs
     */
    T add(T object) throws DataAccessException;

    /**
     * This method update given object in the database.
     *
     * @param object - instance of Object class
     * @throws DataAccessException - throws if an error occurs
     */
    T modify(T object) throws DataAccessException;
    


    MongoOperations getMongoOperations();


}
