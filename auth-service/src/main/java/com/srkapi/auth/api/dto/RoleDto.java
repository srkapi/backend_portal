package com.srkapi.auth.api.dto;

import java.io.Serializable;
import java.util.List;

public class RoleDto implements Serializable{
    private String code;
    private String id;
    private List<PermissionDto> permissions;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
