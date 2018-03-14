package com.srkapi.auth.api.controller;

import com.srkapi.auth.api.dto.UserDto;
import com.srkapi.auth.api.model.Role;
import com.srkapi.auth.api.model.SellerProfile;
import com.srkapi.auth.api.model.User;
import com.srkapi.auth.api.service.UserService;
import com.srkapi.common.async.response.AsyncResponseEntity;
import com.srkapi.common.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sellers")
public class SellerController extends ControllerBase {
	
	@Autowired
    private UserService userService;
    
    @RequestMapping(method = RequestMethod.POST)
    public AsyncResponseEntity<UserDto> save(@ModelAttribute User user) {
    	user.getRoles().add(Role.Create_Seller_Admin());


    	
    	SellerProfile sellerProfile = new SellerProfile();
    	user.setSellerProfile(sellerProfile);
    	
    	user.setBuyerProfile(null);
        return makeAsyncResponse(userService.add(user), HttpStatus.CREATED);
    }


}
