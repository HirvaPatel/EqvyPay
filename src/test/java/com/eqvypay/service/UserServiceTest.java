package com.eqvypay.service;

import com.eqvypay.persistence.IUser;
import com.eqvypay.persistence.User;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
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
import java.util.UUID;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private DatabaseConnectionManagementService dcms;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDataManipulation userDataManipulation;

    private static Connection connection;

    @Test
    @Order(1)
    public void testSave() throws Exception {
        connection = dcms.getConnection(Environment.TEST);

        User user = new User();
        user.setName("PritS");
        user.setEmail("prits@gmail.com");
        user.setContact("9021278643");
        user.setPassword("prit");

        userDataManipulation.save(user);

        PreparedStatement selectQuery = connection.prepareStatement("select * from Users where uuid = ?");
        selectQuery.setString(1, String.valueOf(user.getUuid()));
        ResultSet result = selectQuery.executeQuery();
        Assertions.assertTrue(result.next());
    }


    @Test
    @Order(2)
    public void testDelete() throws Exception {
        connection = dcms.getConnection(Environment.TEST);

        IUser user = userRepository.getByUuid(UUID.fromString("51e0b823-4704-4b82-bed8-e62c68ea5ab0"));
        userDataManipulation.delete(UUID.fromString("51e0b823-4704-4b82-bed8-e62c68ea5ab0"));
        PreparedStatement selectQuery = connection.prepareStatement("select * from Users where uuid = ?");
        selectQuery.setString(1, String.valueOf(user.getUuid()));
        ResultSet result = selectQuery.executeQuery();
        Assertions.assertFalse(result.next());
    }
}


