package com.eqvypay.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eqvypay.persistence.IUser;
import com.eqvypay.persistence.User;

@Service
public class UserFactory {
	// static instance of UserFactory class.
	private static UserFactory userFactory = null;

	// reference of the User Repository class.
	@Autowired
	private UserRepository userRepository;

	// reference of the user data manipulation interface.
	@Autowired
	private IUserDataManipulation userDataManipuation;

	// reference of the user.
	private IUser user;

	// returns the instance of the UserFactory class.
	public static UserFactory getInstance() {
		if(userFactory == null) {
			userFactory = new UserFactory();
		}
		return userFactory;
	}

	// returns the reference of the IUserDataManipulation interface.
	public IUserDataManipulation getUserDataManipuation() {
		return userDataManipuation;
	}

	// return the reference of the UserRepository class.
	public UserRepository getUserRepository() {
		return userRepository;
	}

	// returns the reference of the user object.
	public IUser getUser() {
		user = new User();
		return user;
	}
}
