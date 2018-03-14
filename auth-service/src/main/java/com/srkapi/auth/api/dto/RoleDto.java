package com.srkapi.auth.api.dto;

import com.srkapi.auth.api.model.Role;
import com.srkapi.common.BaseDto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class RoleDto extends BaseDto<Role> implements Serializable{
    private String code;
    private List<PermissionDto> permissions;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<PermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDto> permissions) {
        this.permissions = permissions;
    }

    @Override
    public Role toModel() {
        Role role = new Role();
        role.setCode(this.code);
        role.setPermissions(this.permissions.stream()
                .map(PermissionDto::toModel).collect(Collectors.toList()));
        return role;
    }
}
