package com.srkapi.auth.api.service.impl;

import com.srkapi.auth.api.dao.UserDao;
import com.srkapi.auth.api.dto.RoleDto;
import com.srkapi.auth.api.dto.UserDto;
import com.srkapi.auth.api.model.Permission;
import com.srkapi.auth.api.model.Role;
import com.srkapi.auth.api.model.User;
import com.srkapi.auth.api.security.SecurityUtil;
import com.srkapi.auth.api.service.RoleService;
import com.srkapi.auth.api.service.UserService;
import com.srkapi.common.constants.ConfigConstants;
import com.srkapi.common.dao.impl.GenericRepositoryMongoImpl;
import com.srkapi.common.exception.DuplicateEmailRegisteredException;
import com.srkapi.common.exception.OldPasswordNotMatch;
import com.srkapi.common.exception.RequiredFieldMissingException;
import com.srkapi.common.service.impl.GenericServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rx.exceptions.Exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, UserDto> implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value(ConfigConstants.IMAGE_UPLOAD_LOCATION)
    private String IMAGE_LOCATION;


    @Override
    public Mono findByEmail(String email) {

        return Mono.just(userDao.findByEmail(email));
    }

    @Override
    public Mono<User> findByUsernameOrEmail(String username, String email) {
        return Mono.just(userDao.findByUsernameOrEmail(username, email));
    }

    @Override
    public Mono<UserDto> add(UserDto user) {
        registerValidation(user);
        Mono byEmail = findByEmail(user.getEmail());
        byEmail.map(checkUser -> {
            //check email already registered
            if (checkUser != null)
                throw Exceptions.propagate(new DuplicateEmailRegisteredException());
            //setting default username, if username is empty
            if (StringUtils.isEmpty(user.getUsername())) {
                String[] splictedEmail = user.getEmail().split("@");
                if (splictedEmail.length >= 2) user.setUsername(splictedEmail[0]);
            }
            //encode password
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            for (RoleDto r : user.getRoles()) {
                Flux<RoleDto> byCode = roleService.findByCode(r.getCode());
                user.setRoles(byCode.collectList().block());
            }
            return user;
        }).thenEmpty(it -> {
            throw Exceptions.propagate(new DuplicateEmailRegisteredException());
        });
        User userModel = toModel(user);
        userModel.setAttempts(0);
        userModel.setLastLoggedOn(new Date());
        return this.add(user);

    }

    @Override
    public Mono<UserDto> edit(UserDto user) {

        User loggedUser = SecurityUtil.getLoggedDbUser();

        Mono<UserDto> byId = getById(loggedUser.getId());
        byId.subscribe(savedUser -> {
            user.setId(savedUser.getId());
            user.setEmail(savedUser.getEmail());
            user.setUsername(savedUser.getUsername());
            user.setPassword(savedUser.getPassword());


        });

        return super.edit(user);

    }


    @Override
    public Mono<Boolean> changePassword(String oldPassword, String newPassword) {

        User loggedUser = SecurityUtil.getLoggedDbUser();

        if (passwordEncoder.matches(oldPassword, loggedUser.getPassword())) {

            Mono<UserDto> byId = getById(loggedUser.getId());
            Mono<Boolean> map = byId.map(savedUser -> {

                savedUser.setPassword(passwordEncoder.encode(newPassword));
//                savedUser.setLastPasswordResetDate(new Date());
                return true;

            });
            return map;

        } else
            return Mono.error(new OldPasswordNotMatch());
    }

    @Override
    public Mono<Boolean> delete(String id) {
        UserDto user = new UserDto();
        user.setId(id);
        super.delete(user);
        return Mono.just(true);
    }

    @Override
    public Mono<Boolean> delete(List<String> idList) {
        for (String id : idList) {
            this.delete(id);
        }
        return Mono.just(true);
    }

    @Override
    public Mono<User> findUserByToken(String token) {
        try {
            return Mono.just(userDao.findUserByToken(token));
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    private boolean registerValidation(UserDto user) {

        List<String> missingFields = new ArrayList<>();

        if (StringUtils.isEmpty(user.getEmail()))
            missingFields.add("user.email");

        if (StringUtils.isEmpty(user.getPassword()))
            missingFields.add("user.password");

        if (!missingFields.isEmpty())
            throw new RequiredFieldMissingException(missingFields.toArray());

        return true;

    }

    @Override
    public UserDto toDto(User model) {
        return null;
    }

    @Override
    public User toModel(UserDto Dto) {

        User result = new User();
        result.setEmail(Dto.getEmail());
        result.setFirstName(Dto.getFirstName());
        result.setLastName(Dto.getLastName());
        result.setPassword(Dto.getPassword());
        List<Role> roles = new ArrayList<>();
        Dto.getRoles().forEach(it -> {
            Role aux = new Role();
            aux.setCode(it.getCode());
            roles.add(aux);
        });
        result.setRoles(roles);
        List<Permission> permissions = new ArrayList<>();

        result.setPermissions(permissions);
        return result;
    }

    @Override
    public GenericRepositoryMongoImpl getRepository() {
        return this.userDao;
    }
}
