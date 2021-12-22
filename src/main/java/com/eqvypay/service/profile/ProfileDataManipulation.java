package com.eqvypay.service.profile;

import com.eqvypay.persistence.IUser;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.service.user.UserDataManipulation;
import com.eqvypay.service.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

/**
 * {@code ProfileDataManipulation} implements the
 * {@code IProfileDataManipulation} to provide a concrete
 * implementation for displaying profile information of the
 * current logged-in user.
 */
@Service
public class ProfileDataManipulation implements IProfileDataManipulation{

    // reference of the database connection service class.
    @Autowired
    DatabaseConnectionManagementService dcms;

    // reference of the use data manipulation class.
    @Autowired
    private UserDataManipulation dataManipulation;

    // reference of the user repository class.
    @Autowired
    private UserRepository userRepository;


    /**
     * Display the profile details of this user.
     *
     * @param user object of the user.
     * @throws Exception if an error occurs while fetching user's
     *                   information from the Users table.
     *
     */
    @Override
    public void getProfile(IUser user) throws Exception {
        user = userRepository.getByEmail(user.getEmail());

        System.out.println("\nProfile Details for " + user.getName());
        System.out.println("Username: " + user.getName());
        System.out.println("Email id: " + user.getEmail());
        System.out.println("Contact number: " + user.getContact());

    }
}
