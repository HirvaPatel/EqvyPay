package com.eqvypay.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProfileFactory {

	// static instance of ProfileFactory class.
	private static ProfileFactory profileFactory=null;

	// reference of the profile data manipulation interface.
	@Autowired
	private IProfileDataManipulation profileDataManipulation;

	// reference of the profile repository class.
	@Autowired
	private ProfileRepository profileRepository;

	// returns the instance of the ProfileFactory class.
	public static ProfileFactory getInstance() {
		if(profileFactory == null) {
			profileFactory = new ProfileFactory();
		}
		return profileFactory;
	}

	// returns the reference of the IProfileDataManipulation interface.
	public IProfileDataManipulation getProfileDataManipulation() {
		return profileDataManipulation;
	}

	// return the reference of the ProfileRepository class.
	public ProfileRepository getProfileRepository() {
		return profileRepository;
	}
	
}
