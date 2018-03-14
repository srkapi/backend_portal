package com.srkapi.auth.api.service.impl;

import com.srkapi.auth.api.dao.RoleDao;
import com.srkapi.auth.api.dto.RoleDto;
import com.srkapi.auth.api.model.Role;
import com.srkapi.auth.api.service.RoleService;
import com.srkapi.common.dao.SequenceDao;
import com.srkapi.common.service.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

import javax.annotation.PostConstruct;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role,RoleDto> implements RoleService{

	@Autowired
	protected RoleDao roleDao;
	
	@Autowired
	protected SequenceDao sequenceDao;
	
	@PostConstruct
	void init() {
        init(Role.class, roleDao);
    }
	
	@Override
	public Single<RoleDto> add(Role role){
		return super.add(role);
	}
}
