package com.srkapi.auth.api.service.impl;

import javax.annotation.PostConstruct;

import com.srkapi.auth.api.dto.SellerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.srkapi.auth.api.dao.UserDao;
import com.srkapi.auth.api.model.Role;
import com.srkapi.auth.api.model.User;
import com.srkapi.auth.api.security.SecurityUtil;
import com.srkapi.auth.api.service.SellerService;
import com.srkapi.auth.api.service.UserService;
import com.srkapi.common.exception.BadRequestException;
import com.srkapi.common.messages.ErrorMessage;
import com.srkapi.common.service.impl.GenericServiceImpl;

import rx.Single;
import rx.exceptions.Exceptions;

@Service
public class SellerServiceImpl extends GenericServiceImpl<User, SellerDto> implements SellerService {

    @Autowired
    private UserDao sellerDao;





    @Autowired
    private UserService userService;

    @PostConstruct
    void init() {
        init(User.class, sellerDao);
    }

    @Override
    public Single<SellerDto> registerOtherSeller(User seller) {

        User loggedUser = SecurityUtil.getLoggedDbUser();
        String id = loggedUser.getId();

        return saveSeller(getById(id));

    }


    private Single<SellerDto> saveSeller(Single<SellerDto> u) {
        u.flatMap(user -> {
            String storeId = user.getSellerProfile().getStoreId();

            if (StringUtils.isEmpty(storeId))
                Exceptions.propagate(new BadRequestException(ErrorMessage.STORE_ID_IS_NULL));

            user.getSellerProfile().setStoreId(storeId);
            user.getRoles().add(Role.Create_Seller());
            user.setBuyerProfile(null);
            return userService.add(user).map(o -> o.toModel());
        });
        return u;
    }

}
