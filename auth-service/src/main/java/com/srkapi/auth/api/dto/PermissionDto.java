package com.srkapi.auth.api.dto;

import com.srkapi.common.BaseDto;
import com.srkapi.common.model.Permission;

import java.io.Serializable;

public class PermissionDto  extends BaseDto<Permission> implements Serializable{
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public Permission toModel() {
        Permission permission = new Permission();
        permission.setCode(this.code);
        return permission;
    }
}
