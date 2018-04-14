package com.srkapi.auth.api.dao;

import com.srkapi.auth.api.model.Permission;
import com.srkapi.auth.api.model.User;
import com.srkapi.common.dao.GenericDao;
import com.srkapi.common.exception.DataAccessException;

import java.util.List;

public interface UserDao extends GenericDao<User>{
	
	User findByEmail(String email) throws DataAccessException;

	List<Permission> findUserPermissions()throws DataAccessException;
	
	User findUserByToken(String token)throws DataAccessException;

	User findByUsernameOrEmail(String username, String email) throws DataAccessException;
	
}
