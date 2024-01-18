package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	// creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		// TODO Auto-generated method stub

		User local = this.userRepository.findByUserName(user.getUserName());

		if (local != null) {
			System.out.println("User already present");
			throw new Exception("User already present");
		} else {
			// create user
			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
		}
		return local;
	}

	// getting user by username
	@Override
	public User getUser(String userName) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUserName(userName);
	}

	// deleting user by id
	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(userId);
	}

	// updating user by id
	@Override
	public User updateUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
