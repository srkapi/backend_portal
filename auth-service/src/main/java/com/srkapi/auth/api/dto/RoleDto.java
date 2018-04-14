package com.srkapi.auth.api.dto;

import java.io.Serializable;
import java.util.List;

public class RoleDto implements Serializable{
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

}
