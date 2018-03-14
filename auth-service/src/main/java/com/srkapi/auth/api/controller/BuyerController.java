package com.srkapi.auth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.srkapi.auth.api.model.Role;
import com.srkapi.auth.api.model.User;
import com.srkapi.auth.api.service.UserService;
import com.srkapi.common.async.response.AsyncResponseEntity;
import com.srkapi.common.util.ControllerBase;

@RestController
@RequestMapping("/buyers")
public class BuyerController extends ControllerBase {

    @Autowired
    private UserService userService;
    
    @RequestMapping(method = RequestMethod.POST)
    public AsyncResponseEntity<User> save(@ModelAttribute User user) {
    	user.getRoles().add(Role.Create_Buyer());
    	user.setSellerProfile(null);
        return makeAsyncResponse(userService.add(user), HttpStatus.CREATED);
    }

}
