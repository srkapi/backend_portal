package com.srkapi.auth.api.controller;

import com.srkapi.auth.api.dto.PasswordDto;
import com.srkapi.auth.api.dto.UserDto;
import com.srkapi.auth.api.service.UserService;
import com.srkapi.common.sse.ListenerType;
import com.srkapi.common.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController extends ControllerBase {

	@Autowired
    private UserService userService;
	
	public UserController() {
		super(ListenerType.USER);
	}
	
	@GetMapping
    public Flux<UserDto> getAll() {
		return userService.getAll();
    }
	
	@GetMapping("/{id}")
    public Mono<UserDto> getById(@PathVariable("id") String id) {
		return userService.getById(id);
    }
	
	@PostMapping(value = "/changePassword")
	public Mono<Boolean> passwordChange(@RequestBody PasswordDto passwordDto) {
		return userService.changePassword(passwordDto.getOldPassword(), passwordDto.getNewPassword())
				;
	}
	
	@PutMapping
    public Mono<UserDto> update(@RequestBody UserDto user) {

		return userService.edit(user);
    }

	@PostMapping
	public Mono<UserDto> save(@RequestBody UserDto user) {
		return userService.add(user);
	}








}
