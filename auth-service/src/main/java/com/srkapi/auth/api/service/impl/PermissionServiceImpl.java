package com.srkapi.auth.api.service.impl;

import com.srkapi.auth.api.dao.PermissionDao;
import com.srkapi.auth.api.dto.PermissionDto;
import com.srkapi.auth.api.model.Permission;
import com.srkapi.auth.api.service.PermissionService;
import com.srkapi.common.dao.SequenceDao;
import com.srkapi.common.service.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

import javax.annotation.PostConstruct;

;

@Service
public class PermissionServiceImpl extends GenericServiceImpl<Permission, PermissionDto> implements PermissionService{

	@Autowired
	protected PermissionDao permissionDao;
	
	@Autowired
	protected SequenceDao sequenceDao;
	
	
	@Override
	public Single<PermissionDto> add(Permission permission){
		return super.add(permission);
	}

	@Override
	public PermissionDto toDto(Permission model) {
		return null;
	}

	@Override
	public Permission toModel(PermissionDto Dto) {
		return null;
	}
}
