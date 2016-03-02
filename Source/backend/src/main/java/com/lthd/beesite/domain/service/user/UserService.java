package com.lthd.beesite.domain.service.user;

import com.lthd.beesite.app.dto.RegistrationInfoDto;
import com.lthd.beesite.app.dto.UserDto;
import com.lthd.beesite.domain.entity.User;
import com.lthd.beesite.domain.service.generic.GenericService;

public interface UserService extends GenericService<User, UserDto, Integer> {
	UserDto registerNewUser(RegistrationInfoDto registrationInfo);
	UserDto findByUsername(String username);
}
