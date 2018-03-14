package com.srkapi.auth.api.dao;

import com.srkapi.auth.api.model.Role;
import com.srkapi.common.dao.GenericDao;
import com.srkapi.common.exception.DataAccessException;

import java.util.List;

public interface RoleDao extends GenericDao<Role>{


    List<Role> findByCode(String id);
}
