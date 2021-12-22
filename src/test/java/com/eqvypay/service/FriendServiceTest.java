package com.eqvypay.service;

import com.eqvypay.persistence.IUser;
import com.eqvypay.persistence.User;
import com.eqvypay.service.authentication.AuthenticationService;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.service.friends.FriendDataManipulation;
import com.eqvypay.service.friends.FriendRepository;
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
import java.sql.Statement;
import java.util.ArrayList;

@SpringBootTest
public class FriendServiceTest {

    @Autowired
    private DatabaseConnectionManagementService dcms;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDataManipulation userDataManipulation;

    @Autowired
    FriendDataManipulation friendDataManipulation;

    private static Connection connection;

    @Test
    @Order(1)
    public void testCreateTable() throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());

        friendDataManipulation.createTable();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SHOW tables;");
        ArrayList<String> tables = new ArrayList<>();
        while (resultSet.next()) {
            String table = resultSet.getString("Tables_in_CSCI5308_11_TEST");
            tables.add(table);
        }

        Assertions.assertTrue(tables.contains("Friend"));
    }

    @Test
    @Order(2)
    public void testAddFriendByEmail() throws Exception {
        connection = dcms.getConnection(Environment.TEST);

        User user1 = new User();
        user1.setName("ADD_FRIEND_TEST_USER");
        user1.setEmail("testUser1@gmail.com");
        user1.setContact("1234567891");
        user1.setPassword(AuthenticationService.getHashedPassword("Test@123"));
        user1.setSecurityAnswer("test");
        userDataManipulation.save(user1);

        User user2 = new User();
        user2.setName("ADD_FRIEND_TEST_USER");
        user2.setEmail("testUser2@gmail.com");
        user2.setContact("1232134891");
        user2.setPassword(AuthenticationService.getHashedPassword("Test@123"));
        user2.setSecurityAnswer("test");
        userDataManipulation.save(user2);

        IUser user = userRepository.getByEmail("testUser1@gmail.com");
        IUser friend = userRepository.getByEmail("testUser2@gmail.com");

        friendRepository.addFriendByEmail(user, friend.getEmail());
        PreparedStatement selectQuery = connection.prepareStatement("select * from Friend where user_id = ? and friend_id=?");
        selectQuery.setString(1, String.valueOf(user.getUuid()));
        selectQuery.setString(2, String.valueOf(friend.getUuid()));
        ResultSet result = selectQuery.executeQuery();
        Assertions.assertTrue(result.next());
    }

   /* @Test
    @Order(3)
    public void testAddFriendByContact() throws Exception {
        connection = dcms.getConnection(Environment.TEST);

        IUser user = userRepository.getByEmail("preet@gmail.com");
        IUser friend = userRepository.getByEmail("test@gmail.com");

        System.out.println(user.getName());
        System.out.println(friend.getName());
        
        System.out.println("contact: " + friend.getContact());
        friendRepository.addFriendByContact(user, friend.getContact());
        PreparedStatement selectQuery = connection.prepareStatement("select * from Friend where user_id = ? and friend_id=?");
        selectQuery.setString(1, String.valueOf(user.getUuid().toString()));
        selectQuery.setString(2, String.valueOf(friend.getUuid().toString()));
        ResultSet result = selectQuery.executeQuery();
        Assertions.assertTrue(result.next());
    }*/

    @Test
    @Order(4)
    public void testRemoveFriendByEmail() throws Exception {
        connection = dcms.getConnection(Environment.TEST);

        IUser user = userRepository.getByEmail("testUser1@gmail.com");
        IUser friend = userRepository.getByEmail("testUser2@gmail.com");

        friendRepository.removeFriendByEmail(user, friend.getEmail());
        PreparedStatement selectQuery = connection.prepareStatement("select * from Friend where user_id = ? and friend_id=?");
        selectQuery.setString(1, String.valueOf(user.getUuid()));
        selectQuery.setString(2, String.valueOf(friend.getUuid()));
        ResultSet result = selectQuery.executeQuery();
        Assertions.assertFalse(result.next());
    }

    @Test
    @Order(5)
    public void testRemoveFriendByContact() throws Exception {
        connection = dcms.getConnection(Environment.TEST);

        IUser user = userRepository.getByEmail("testUser3@gmail.com");
        IUser friend = userRepository.getByEmail("testUser4@gmail.com");

        friendRepository.removeFriendByContact(user, friend.getContact());
        PreparedStatement selectQuery = connection.prepareStatement("select * from Friend where user_id = ? and friend_id=?");
        selectQuery.setString(1, String.valueOf(user.getUuid()));
        selectQuery.setString(2, String.valueOf(friend.getUuid()));
        ResultSet result = selectQuery.executeQuery();
        Assertions.assertFalse(result.next());
    }
}
