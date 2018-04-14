package com.srkapi.common.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenericService<T,S>{

	Mono<S> getById(String id);

	Mono<S> add(S obj);

	Mono<S> edit(S obj);

	Flux<Boolean> delete(S object);

	Flux<S> getAll();
    
}
