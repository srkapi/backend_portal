package com.srkapi.auth.api.service;

import com.srkapi.auth.api.dto.RoleDto;
import com.srkapi.auth.api.model.Role;
import com.srkapi.common.service.GenericService;
import reactor.core.publisher.Flux;

public interface RoleService extends GenericService<Role,RoleDto>{

    Flux<RoleDto> findByCode(String code);
}
