package com.srkapi.auth.api.dao;

import java.util.List;

import com.srkapi.auth.api.model.User;
import com.srkapi.common.dao.GenericDao;
import com.srkapi.common.exception.DataAccessException;
import com.srkapi.common.model.Permission;

public interface UserDao extends GenericDao<User>{
	
	User findByEmail(String email) throws DataAccessException;

	List<Permission> findUserPermissions()throws DataAccessException;
	
	User findUserByToken(String token)throws DataAccessException;

	User findByUsernameOrEmail(String username, String email) throws DataAccessException;
	
}
