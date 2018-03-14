package com.srkapi.common.service;

import rx.Observable;
import rx.Single;

public interface GenericService<T,S>{

	Single<S> getById(String id);

	Single<S>  add(T obj);

	Single<S> edit(T obj);

	Single<Boolean> delete(T object);

	Observable<T> getAll();
    
}
