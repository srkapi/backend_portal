package com.srkapi.common.dao.impl;

import com.srkapi.common.dao.GenericDao;
import com.srkapi.common.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

@CacheConfig(cacheResolver="primaryCacheResolver")
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

	private static final Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);

	@Autowired
	protected MongoOperations mongoOperations;

	private Class<T> type;

	public GenericDaoImpl(Class<T> type) {
		this.type = type;
	}

	@Override
	@Caching(
				put={@CachePut(key="#object.id")},
				evict={@CacheEvict(cacheResolver="secondaryCacheResolver", allEntries=true)}
			)
	public T add(T object) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} add", type);
		try {
			mongoOperations.insert(object);
			return object;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public T getById(Object id) throws DataAccessException {
		return null;
	}

	@Override
	public List<T> getAll() throws DataAccessException {
		return null;
	}

	@Override
	public T delete(T object) throws DataAccessException {
		return null;
	}

	@Override
	@Caching(
			put={@CachePut(key="#object.id")},
			evict={@CacheEvict(cacheResolver="secondaryCacheResolver", allEntries=true)}
			)
	public T modify(T object) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} modify", type);
		try {
			mongoOperations.save(object);
			return object;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
	



	@Override
	public MongoOperations getMongoOperations() {
		return mongoOperations;
	}
}
