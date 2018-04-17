package com.srkapi.auth.api.dao;

import com.srkapi.auth.api.model.Permission;
import com.srkapi.auth.api.model.User;
import com.srkapi.common.dao.impl.GenericRepositoryMongoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao extends GenericRepositoryMongoImpl<User> {
	
	User findByEmail(String email);

	List<Permission> findUserPermissions();
	
	User findUserByToken(String token);

	User findByUsernameOrEmail(String username, String email);
	
}
