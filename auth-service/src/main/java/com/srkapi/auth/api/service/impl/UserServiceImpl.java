package com.srkapi.auth.api.service.impl;

import com.srkapi.auth.api.dao.RoleDao;
import com.srkapi.auth.api.dao.UserDao;
import com.srkapi.auth.api.dto.PermissionDto;
import com.srkapi.auth.api.dto.RoleDto;
import com.srkapi.auth.api.dto.UserDto;
import com.srkapi.auth.api.dto.UserImageDto;
import com.srkapi.auth.api.model.Role;
import com.srkapi.auth.api.model.User;
import com.srkapi.auth.api.security.SecurityUtil;
import com.srkapi.auth.api.service.UserService;
import com.srkapi.common.constants.ConfigConstants;
import com.srkapi.common.exception.DataAccessException;
import com.srkapi.common.exception.DuplicateEmailRegisteredException;
import com.srkapi.common.exception.OldPasswordNotMatch;
import com.srkapi.common.exception.RequiredFieldMissingException;
import com.srkapi.common.service.impl.GenericServiceImpl;
import com.srkapi.common.util.image.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;
import rx.exceptions.Exceptions;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, UserDto> implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value(ConfigConstants.IMAGE_UPLOAD_LOCATION)
    private String IMAGE_LOCATION;

    @PostConstruct
    void init() {
        init(User.class, userDao);
    }

    @Override
    public Mono findByEmail(String email) {
        try {

            return Mono.just(userDao.findByEmail(email));
        } catch (DataAccessException e) {
            return Mono.error(e);
        }
    }

    @Override
    public Mono<User> findByUsernameOrEmail(String username, String email) {
        try {
            return Mono.just(userDao.findByUsernameOrEmail(username, email));
        } catch (DataAccessException e) {
            return Mono.error(e);
        }
    }

    @Override
    public Mono<UserDto> add(User user) {

        registerValidation(user);

        Mono byEmail = findByEmail(user.getEmail());

        return byEmail.map(checkUser -> {

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
            user.setRegisteredOn(new Date());
            user.setLastPasswordResetDate(new Date());

            for (Role r : user.getRoles()) {

                List<Role> byCode = roleDao.findByCode(r.getCode());
                user.setRoles(byCode);
                byCode.forEach(it -> {
                            user.setPermissions(it.getPermissions());
                        }
                );


            }

            user.setStatus(User.STATUS_ACTIVE);

             return super.add(user);

        });

    }

    @Override
    public Mono<Object> edit(User user, UserImageDto imageDto) {

        User loggedUser = SecurityUtil.getLoggedDbUser();

        ImageUtil imageUtil = ImageUtil.createDropBoxStorageImageUtil();

        Mono<UserDto> byId = getById(loggedUser.getId());
        return byId.map(savedUser -> {
                user.setId(savedUser.getId());
                user.setEmail(savedUser.getEmail());
                user.setUsername(savedUser.getUsername());
                user.setPassword(savedUser.getPassword());
                user.setRoles(savedUser.getRoles().stream().map(RoleDto::toModel).collect(Collectors.toList()));
                user.setPermissions(savedUser.getPermissions().stream().map(PermissionDto::toModel).collect(Collectors.toList()));
                user.setAttempts(savedUser.getAttempts());
                user.setStatus(1);

                return  super.edit(user);


        });


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
        User user = new User();
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

    private boolean registerValidation(User user) {

        List<String> missingFields = new ArrayList<>();

        if (StringUtils.isEmpty(user.getEmail()))
            missingFields.add("user.email");

        if (StringUtils.isEmpty(user.getPassword()))
            missingFields.add("user.password");

        if (!missingFields.isEmpty())
            throw new RequiredFieldMissingException(missingFields.toArray());

        return true;

    }

}
