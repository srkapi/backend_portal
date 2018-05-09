package com.srkapi.auth.api.dao;

import com.srkapi.auth.api.model.User;
import com.srkapi.common.dao.impl.GenericRepositoryMongoImpl;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends GenericRepositoryMongoImpl<User> {

    User findByEmail(String email);

    User findByUsernameOrEmail(String username, String email);

    User findUserByToken(String token);
}
