package com.srkapi.auth.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.srkapi.auth.api.dao.PermissionDao;
import com.srkapi.common.dao.impl.GenericDaoImpl;
import com.srkapi.common.model.Permission;

@Repository
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements PermissionDao {

	public PermissionDaoImpl() {
		super(Permission.class);
	}
}
