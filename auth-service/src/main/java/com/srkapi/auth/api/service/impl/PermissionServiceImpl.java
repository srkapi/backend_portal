package com.srkapi.auth.api.service.impl;

import javax.annotation.PostConstruct;

import com.srkapi.auth.api.dto.PermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srkapi.auth.api.dao.PermissionDao;
import com.srkapi.auth.api.service.PermissionService;
import com.srkapi.common.dao.SequenceDao;
import com.srkapi.common.exception.DataAccessException;
import com.srkapi.common.model.Permission;
import com.srkapi.common.service.impl.GenericServiceImpl;

import rx.Single;;

@Service
public class PermissionServiceImpl extends GenericServiceImpl<Permission, PermissionDto> implements PermissionService{

	@Autowired
	protected PermissionDao permissionDao;
	
	@Autowired
	protected SequenceDao sequenceDao;
	
	@PostConstruct
	void init() {
        init(Permission.class, permissionDao);
    }
	
	@Override
	public Single<PermissionDto> add(Permission permission){
		return super.add(permission);
	}
}
