package com.quebic.auth.api.dao;

import com.quebic.auth.api.model.Role;
import com.quebic.common.dao.GenericDao;
import com.quebic.common.exception.DataAccessException;

import java.util.List;

public interface RoleDao extends GenericDao<Role>{


    List<Role> findByCode(String id);
}
