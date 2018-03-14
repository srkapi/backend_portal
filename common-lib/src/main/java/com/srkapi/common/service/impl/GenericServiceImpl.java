package com.srkapi.common.service.impl;

import com.srkapi.common.dao.GenericDao;
import com.srkapi.common.exception.DataAccessException;
import com.srkapi.common.model.EntityBase;
import com.srkapi.common.service.GenericService;
import rx.Observable;
import rx.Single;

public class GenericServiceImpl<T extends EntityBase,S> implements GenericService<T,S> {
	
	private Class<? extends T> type;
	protected GenericDao<T> genericDao;
	
	protected void init(Class<? extends T> type, GenericDao<T> dao) {
        this.type = type;
        this.genericDao = dao;
    }
	
	@Override
	public Single<S> getById(String id){
		try {
			return Single.just(genericDao.getById(id)).map(o->(S)o.toDto());
        } catch (DataAccessException de) {
        	return Single.error(de);
        } 
	}

	@Override
	public Single<S> add(T obj){
		try {
			return Single.just(genericDao.add(obj)).map(o->(S)o.toDto());
		} catch (DataAccessException de) {
			return Single.error(de);
		}
	}

	@Override
	public Single<S> edit(T obj){
		try {
			return Single.just(genericDao.add(obj)).map(o->(S)o.toDto());
		} catch (DataAccessException de) {
			return Single.error(de);
		}
	}

	@Override
	public Single<Boolean> delete(T obj){
		try {
            genericDao.delete(obj);
            return Single.just(true);
        } catch (DataAccessException de) {
        	return Single.error(de);
        }
	}

	@Override
	public Observable<T> getAll(){
		try {
            return Observable.from(genericDao.getAll());
        } catch (DataAccessException de) {
        	return Observable.error(de);
        } 
	}

}
