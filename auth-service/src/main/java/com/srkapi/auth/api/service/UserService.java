package com.srkapi.auth.api.service;

import com.srkapi.auth.api.dto.UserDto;
import com.srkapi.auth.api.dto.UserImageDto;
import com.srkapi.auth.api.model.User;
import com.srkapi.common.service.GenericService;
import rx.Single;

import java.util.List;

public interface UserService extends GenericService<User,UserDto>{
	
	Single<User> findByEmail(String email);
	
	Single<User> findByUsernameOrEmail(String username, String email);
    
	Single<UserDto> edit(User user, UserImageDto imageDto);


	Single<Boolean> delete(String id);

	Single<Boolean> delete(List<String> idList);
    
	Single<User> findUserByToken(String token);

	Single<Boolean> changePassword(String oldPassword, String newPassword);
	
}
