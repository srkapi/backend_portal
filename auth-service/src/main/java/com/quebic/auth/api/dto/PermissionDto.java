package com.quebic.auth.api.dto;

import java.io.Serializable;

public class PermissionDto  implements Serializable{
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
