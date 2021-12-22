package com.eqvypay.service;

import com.eqvypay.persistence.IUser;
import com.eqvypay.persistence.User;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.service.profile.ProfileRepository;
import com.eqvypay.service.user.UserDataManipulation;
import com.eqvypay.service.user.UserRepository;
import com.eqvypay.util.constants.Environment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SpringBootTest
public class ProfileServiceTest {

    @Autowired
    private DatabaseConnectionManagementService dcms;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UserDataManipulation userDataManipulation;

    private static Connection connection;

    @Test
    @Order(1)
    public void testUpdateUsername() throws Exception {
        connection = dcms.getConnection(Environment.TEST);

        String updatedUsername = "hirva";
        IUser user = userRepository.getByEmail("hirva@gmail.com");
        profileRepository.updateUsername(user, updatedUsername);

        PreparedStatement selectQuery = connection.prepareStatement("select * from Users where uuid = ?");
        selectQuery.setString(1, String.valueOf(user.getUuid()));
        ResultSet result = selectQuery.executeQuery();
        while(result.next()){
            Assertions.assertTrue(result.getString("name").toString().equals(updatedUsername));
        }
    }

    @Test
    @Order(2)
    public void testUpdateContact() throws Exception {
        connection = dcms.getConnection(Environment.TEST);

        String updatedContact = "9026404405";
        IUser user = userRepository.getByEmail("hirva@gmail.com");
        profileRepository.updateContact(user, updatedContact);

        PreparedStatement selectQuery = connection.prepareStatement("select * from Users where uuid = ?");
        selectQuery.setString(1, String.valueOf(user.getUuid()));
        ResultSet result = selectQuery.executeQuery();
        while(result.next()){
            Assertions.assertTrue(result.getString("contact").toString().equals(updatedContact));
        }
    }

    @Test
    @Order(3)
    public void testUpdatePassword() throws Exception {
        connection = dcms.getConnection(Environment.TEST);

        String updatedPassword = "hirva";
        IUser user = userRepository.getByEmail("hirva@gmail.com");
        profileRepository.updateContact(user, updatedPassword);

        PreparedStatement selectQuery = connection.prepareStatement("select * from Users where uuid = ?");
        selectQuery.setString(1, String.valueOf(user.getUuid()));
        ResultSet result = selectQuery.executeQuery();
        while(result.next()){
            Assertions.assertTrue(result.getString("password").toString().equals(updatedPassword));
        }
    }
}
