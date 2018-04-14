package com.srkapi.auth.api.dao;

import com.srkapi.auth.api.model.Role;
import com.srkapi.common.dao.ReactiveRepositoryMongo;
import reactor.core.publisher.Flux;

public interface RoleDao extends ReactiveRepositoryMongo<Role> {


    Flux<Role> findByCode(String id);
}
