package com.srkapi.auth.api.dao;

import com.srkapi.auth.api.model.Role;
import com.srkapi.common.dao.impl.GenericRepositoryMongoImpl;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface RoleDao extends GenericRepositoryMongoImpl<Role> {


    Flux<Role> findByCode(String id);
}
