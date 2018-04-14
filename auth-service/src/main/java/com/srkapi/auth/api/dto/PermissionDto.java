package com.srkapi.auth.api.dto;

import java.io.Serializable;

public class PermissionDto   implements Serializable{
    private String code;
    private String id;

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


}
