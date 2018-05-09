package com.srkapi.auth.api.dao;

import com.srkapi.auth.api.model.User;
import com.srkapi.common.dao.impl.GenericRepositoryMongoImpl;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface UserDao extends GenericRepositoryMongoImpl<User> {

    Mono<User> findByEmail(String email);

    Mono<User> findByUsernameOrEmail(String username, String email);

    Mono<User> findUserByToken(String token);
}
