package com.quebic.auth.api.controller;

import com.quebic.auth.api.dto.UserDto;
import com.quebic.auth.api.model.Role;
import com.quebic.common.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.quebic.auth.api.dto.PasswordDto;
import com.quebic.auth.api.dto.UserImageDto;
import com.quebic.auth.api.model.User;
import com.quebic.auth.api.service.UserService;
import com.quebic.common.async.response.AsyncResponseEntity;
import com.quebic.common.response.CommonResponse;
import com.quebic.common.sse.ListenerType;
import com.quebic.common.util.ControllerBase;

import javax.mail.internet.ContentType;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends ControllerBase {

	@Autowired
    private UserService userService;
	
	public UserController() {
		super(ListenerType.USER);
	}
	
	@RequestMapping
    public AsyncResponseEntity<User> getAll() {
		return makeAsyncResponse(userService.getAll());
    }
	
	@RequestMapping("/{id}")
    public AsyncResponseEntity<User> getById(@PathVariable("id") String id) {
		return makeAsyncResponse(userService.getById(id));
    }
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public AsyncResponseEntity<CommonResponse> passwordChange(@ModelAttribute PasswordDto passwordDto) {
		return makeAsyncResponse(
				userService.changePassword(passwordDto.getOldPassword(), passwordDto.getNewPassword())
				.map(r->new CommonResponse(1, r)));
	}
	
	@RequestMapping(method = RequestMethod.PUT)
    public AsyncResponseEntity<User> update(@ModelAttribute User user) {
    	return makeAsyncResponse(userService.edit(user, new UserImageDto()).map(i->{
			publishMessage(i.getId(), i);
			return i;
		}), HttpStatus.ACCEPTED);
    }

	@RequestMapping(path = "/",method = RequestMethod.POST,
			produces="application/json", consumes="application/json;text/plain;charset=UTF-8")
	public AsyncResponseEntity<User> save(@RequestBody UserDto user) {
		return makeAsyncResponse(userService.add(toUser(user)).map(i->{
			publishMessage(i.getId(), i);
			return i;
		}), HttpStatus.ACCEPTED);
	}


	@RequestMapping(value="/updateFullProfile", method = RequestMethod.POST)
    public AsyncResponseEntity<User> updateFullProfile(@ModelAttribute User user, @ModelAttribute UserImageDto imageDto) {
    	return makeAsyncResponse(userService.edit(user, imageDto).map(i->{
			publishMessage(i.getId(), i);
			return i;
		}), HttpStatus.ACCEPTED);
    }


	private User toUser(UserDto user) {
		User result = new User();
		result.setAttempts(user.getAttempts());
		result.setEmail(user.getEmail());
		result.setFirstName(user.getFirstName());
		result.setLastName(user.getLastName());
		result.setPassword(user.getPassword());
		List<Role> roles = new ArrayList<>();
		user.getRoles().forEach(it->{
			Role aux = new Role();
			aux.setCode(it.getCode());
			roles.add(aux);
		});
		result.setRoles(roles);
		List<Permission> permissions = new ArrayList<>();
		user.getPermissions().forEach(it->{
			Permission aux = new Permission();
			aux.setCode(it.getCode());
			permissions.add(aux);
		});
		result.setPermissions(permissions);
		return result;
	}


}
