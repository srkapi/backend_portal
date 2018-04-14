package com.srkapi.auth.api.service.impl;

import com.srkapi.auth.api.dao.UserDao;
import com.srkapi.auth.api.dto.SellerDto;
import com.srkapi.auth.api.dto.UserDto;
import com.srkapi.auth.api.model.Role;
import com.srkapi.auth.api.model.User;
import com.srkapi.auth.api.security.SecurityUtil;
import com.srkapi.auth.api.service.SellerService;
import com.srkapi.auth.api.service.UserService;
import com.srkapi.common.exception.BadRequestException;
import com.srkapi.common.messages.ErrorMessage;
import com.srkapi.common.service.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;
import rx.exceptions.Exceptions;

@Service
public class SellerServiceImpl extends GenericServiceImpl<User, SellerDto> implements SellerService {

    @Autowired
    private UserDao sellerDao;


    @Autowired
    private UserService userService;


    @Override
    public Mono<SellerDto> registerOtherSeller(User seller) {

        User loggedUser = SecurityUtil.getLoggedDbUser();
        String id = loggedUser.getId();



        return saveSeller(getById(id));

    }


    private Mono<SellerDto> saveSeller(Mono<SellerDto> u) {
        u.flatMap(user -> {
            String storeId = user.getSellerProfile().getStoreId();

            if (StringUtils.isEmpty(storeId))
                Exceptions.propagate(new BadRequestException(ErrorMessage.STORE_ID_IS_NULL));

            user.getSellerProfile().setStoreId(storeId);
            user.getRoles().add(Role.Create_Seller());
            user.setBuyerProfile(null);
            return userService.add(toDtoUser(user));
        });
        return u;
    }

    private UserDto toDtoUser(SellerDto dto){
        return null;
    }

    @Override
    public SellerDto toDto(User model) {
        return null;
    }

    @Override
    public User toModel(SellerDto Dto) {
        return null;
    }
}
