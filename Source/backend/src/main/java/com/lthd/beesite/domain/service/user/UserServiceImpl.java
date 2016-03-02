package com.lthd.beesite.domain.service.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lthd.beesite.app.controller.common.utils.Constant;
import com.lthd.beesite.app.controller.common.utils.ValidatorUtil;
import com.lthd.beesite.app.dto.RegistrationInfoDto;
import com.lthd.beesite.app.dto.UserDto;
import com.lthd.beesite.domain.entity.Member;
import com.lthd.beesite.domain.entity.User;
import com.lthd.beesite.domain.service.exception.AppException;
import com.lthd.beesite.domain.service.exception.EntityNotFoundException;
import com.lthd.beesite.domain.service.generic.GenericServiceImpl;
import com.lthd.beesite.repository.MemberRepository;
import com.lthd.beesite.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User, UserDto, Integer> implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	ValidatorUtil validatorUtil;
	
	@Override
	public UserDto registerNewUser(RegistrationInfoDto registrationInfo) {
		
		validatorUtil.validate(registrationInfo);
		
		if (userRepository.findByUsername(registrationInfo.getUsername()) != null) {
			throw new AppException(HttpStatus.CONFLICT.value(), "The username " + registrationInfo.getUsername() + " already exists.",
					"The " + registrationInfo.getUsername() + " already exists.");
		}
		if (userRepository.findByEmail(registrationInfo.getEmail()) != null) {
			throw new AppException(HttpStatus.CONFLICT.value(), "The email " + registrationInfo.getEmail() + " already exists.",
					"The email " + registrationInfo.getEmail() + " already exists.");
		}
		
		
		User newUser = new User();
		newUser.setUsername(registrationInfo.getUsername());
		//TODO encode password
		//newUser.setPassword(passwordEncoder.encode(registrationInfo.getPassword()));
		newUser.setPassword(registrationInfo.getPassword());
		newUser.setEmail(registrationInfo.getEmail());
		newUser.setRole(Constant.ROLE_USER);
		
		userRepository.saveAndFlush(newUser);
		
		Member newMember = new Member();
		newMember.setName(registrationInfo.getName());
		newMember.setUser(newUser);
		
		memberRepository.save(newMember);
		
		UserDto returnUserDto = mapper.map(newUser, UserDto.class); 
		returnUserDto.setPassword(null);
		
		return returnUserDto;
	}

	@Override
	public UserDto findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new EntityNotFoundException("Cannot find " + username, "Cannot find " + username);
		}
		
		return mapper.map(user, UserDto.class);
	}

}
