package com.srkapi.auth.api.service;

import com.srkapi.auth.api.dto.UserDto;
import com.srkapi.auth.api.dto.UserImageDto;
import com.srkapi.auth.api.model.User;
import com.srkapi.common.service.GenericService;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserService extends GenericService<User,UserDto>{
	
	Mono findByEmail(String email);

	Mono<User> findByUsernameOrEmail(String username, String email);
    
	Mono<Object> edit(User user, UserImageDto imageDto);


	Mono<Boolean> delete(String id);

	Mono<Boolean> delete(List<String> idList);

	Mono<User> findUserByToken(String token);

	Mono<Boolean> changePassword(String oldPassword, String newPassword);
	
}
