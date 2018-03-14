package com.srkapi.auth.api.dto;

import com.srkapi.auth.api.model.User;
import com.srkapi.common.BaseDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDto extends BaseDto<User> implements Serializable {
    private String id;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Integer attempts;
    private List<RoleDto> roles = new ArrayList<>();
    private List<PermissionDto> permissions = new ArrayList<>();


    public  UserDto(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public List<PermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDto> permissions) {
        this.permissions = permissions;
    }

    @Override
    public User toModel() {
        User user = new User();
        user.setAttempts(this.attempts);
        user.setEmail(this.email);
        user.setUsername(this.username);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setRoles(this.roles.stream().map(RoleDto::toModel).collect(Collectors.toList()));
        return  user;
    }
}
