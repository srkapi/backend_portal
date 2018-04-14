package com.srkapi.auth.api.dao;

import com.srkapi.auth.api.model.Permission;
import com.srkapi.auth.api.model.User;
import com.srkapi.common.dao.ReactiveRepositoryMongo;

import java.util.List;

public interface UserDao extends ReactiveRepositoryMongo<User> {
	
	User findByEmail(String email);

	List<Permission> findUserPermissions();
	
	User findUserByToken(String token);

	User findByUsernameOrEmail(String username, String email);
	
}
