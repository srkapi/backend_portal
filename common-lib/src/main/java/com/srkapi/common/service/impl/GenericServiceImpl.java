package com.srkapi.common.service.impl;

import com.srkapi.common.dao.impl.GenericRepositoryMongoImpl;
import com.srkapi.common.model.EntityBase;
import com.srkapi.common.service.GenericService;
import com.srkapi.common.service.Mapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class GenericServiceImpl<T extends EntityBase,S> implements GenericService<T,S>,Mapper<T,S> {
	
	protected GenericRepositoryMongoImpl<T> genericDao;
	

	@Override
	public Mono<S> getById(String id){
			return this.genericDao.findById(id).map(it -> toDto(it));
	}

	public abstract S toDto(T model);
	public abstract T toModel(S Dto);



	@Override
	public Mono<S> add(S obj){
		return this.genericDao.save(toModel(obj)).map(it->toDto(it));
	}

	@Override
	public Mono<S> edit(S obj){
		return this.genericDao.save(toModel(obj)).map(it->toDto(it));
	}

	@Override
	public Flux<Boolean> delete(S obj){
		Mono<Void> delete = this.genericDao.delete(toModel(obj));
		Flux<Boolean> booleanFlux = delete.flatMap(it ->
				Mono.just(true)
		);
		return booleanFlux;
	}

	@Override
	public Flux<S> getAll(){
		return this.genericDao.findAll().map(it -> toDto(it));
	}

}
