package com.srkapi.auth.api.service.impl;

import com.srkapi.auth.api.dao.RoleDao;
import com.srkapi.auth.api.dto.RoleDto;
import com.srkapi.auth.api.model.Role;
import com.srkapi.auth.api.service.RoleService;
import com.srkapi.common.dao.impl.GenericRepositoryMongoImpl;
import com.srkapi.common.service.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role,RoleDto> implements RoleService{

	@Autowired
	protected RoleDao roleDao;
	


	@Override
	public Flux<RoleDto> findByCode(String code){
		return this.roleDao.findByCode(code).map(it ->toDto(it));
	}


	@Override
	public RoleDto toDto(Role model) {
		return null;
	}

	@Override
	public Role toModel(RoleDto Dto) {
		return null;
	}

	@Override
	public GenericRepositoryMongoImpl getRepository() {
		return this.roleDao;
	}
}
