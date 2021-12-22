package com.eqvypay.service.friends;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.eqvypay.service.activity.ActivityHelper;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.service.user.UserDataManipulation;
import com.eqvypay.service.user.UserRepository;
import com.eqvypay.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eqvypay.persistence.IUser;
import com.eqvypay.util.constants.Constants;

/**
 * {@code FriendService} implements the
 * {@code FriendRepository} to provide a concrete implementation
 * for making and removing friend of this user.
 */
@Service
public class FriendService implements FriendRepository {

    // reference of the database connection service class.
    @Autowired
    private DatabaseConnectionManagementService dcms;

    // reference of the user data manipulation class.
    @Autowired
    private UserDataManipulation userDataManipulation;

    // reference of the user repository class.
    @Autowired
    private UserRepository userRepository;

    // reference of the friend data manipulation class.
    @Autowired
    private FriendDataManipulation friendDataManipulation;

    // reference of the DtoUtils class.
    @Autowired
    DtoUtils dtoUtils;

    /**
     * Implement the query to perform insert record of making
     * friend of the user in the Friend table.
     *
     * @param user  object of the user.
     * @param email registered email address of the friend.
     * @throws Exception if any error occurs while performing
     *                   operation to make friend of this user.
     */
    @Override
    public void addFriendByEmail(IUser user, String email) throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        ResultSet result = null;
        IUser friend = null;
        try {
            friend = userRepository.getByEmail(email);

            friendDataManipulation.createTable();

            if (friend.getEmail() == null) {
                System.out.println("No user with email '" + email + "' found.");
            } else {
                PreparedStatement insertQuery = connection.prepareStatement("insert into Friend (user_id, friend_id) values (?, ?)");
                insertQuery.setString(1, user.getUuid().toString());
                insertQuery.setString(2, friend.getUuid().toString());
                insertQuery.execute();
                System.out.println("Friend added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Enter a valid email id of a registered user!" + e);
        }

    }

    /**
     * Implement the query to perform insert record of making
     * friend of the user in the Friend table.
     *
     * @param user    object of the user.
     * @param contact registered contact number of the friend.
     * @throws Exception if any error occurs while performing
     *                   operation to make friend of this user.
     */
    @Override
    public void addFriendByContact(IUser user, String contact) throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        ResultSet result = null;
        IUser friend = null;
        try {
            PreparedStatement selectQuery = connection.prepareStatement("select * from Users where contact = ?");
            selectQuery.setString(1, contact);
            result = selectQuery.executeQuery();

            String friendUuid = null;
            int count = dtoUtils.getCountOfRecords(result);
            selectQuery = connection.prepareStatement("select * from Users where contact = ?");
            selectQuery.setString(1, contact);
            result = selectQuery.executeQuery();

            if (count == 0) {
                System.out.println("No user with contact " + contact + " is registered in the system. Please try again");
            } else {

                if (result.next()) {
                    friendUuid = result.getString("uuid");
                    friend = userRepository.getByUuid(UUID.fromString(friendUuid));
                }
                friendDataManipulation.createTable();

                PreparedStatement insertQuery = connection.prepareStatement("insert into Friend (user_id, friend_id) values (?, ?)");
                insertQuery.setString(1, user.getUuid().toString());
                insertQuery.setString(2, friendUuid);
                insertQuery.execute();
                System.out.println("Friend added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Enter a valid contact number of a registered user!");
        }

        ActivityHelper.addActivity(user.getUuid().toString(), String.format(Constants.friends, friend.getName()));
        ActivityHelper.addActivity(friend.getUuid().toString(), String.format(Constants.friends, user.getName()));

    }

    /**
     * Implement the query to perform delete record of the
     * friend of the user from the Friend table.
     *
     * @param user  object of the user.
     * @param email registered email address of the friend.
     * @throws Exception if any error occurs while performing
     *                   operation to remove friend of this user.
     */
    @Override
    public void removeFriendByEmail(IUser user, String email) throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        ResultSet result = null;
        try {
            if (dtoUtils.tableExist(dcms, "Friend")) {
                PreparedStatement selectQuery = connection.prepareStatement("select * from Users where email = ?");
                selectQuery.setString(1, email);
                result = selectQuery.executeQuery();

                String friendUuid = null;
                while (result.next()) {
                    friendUuid = result.getString("uuid");
                }
                PreparedStatement insertQuery = connection.prepareStatement("delete from Friend where friend_id = ?");
                insertQuery.setString(1, friendUuid);
                insertQuery.execute();
            } else {
                System.out.println("No friend found");
            }
        } catch (SQLException e) {
            System.out.println("Enter a valid email id of a registered user!");
        }
    }

    /**
     * Implement the query to perform delete record of the
     * friend of the user from the Friend table.
     *
     * @param user    object of the user.
     * @param contact registered contact number of the friend.
     * @throws Exception if any error occurs while performing
     *                   operation to remove friend of this user.
     */
    @Override
    public void removeFriendByContact(IUser user, String contact) throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        ResultSet result = null;
        try {
            if (dtoUtils.tableExist(dcms, "Friend")) {
                PreparedStatement selectQuery = connection.prepareStatement("select * from Users where contact = ?");
                selectQuery.setString(1, contact);
                result = selectQuery.executeQuery();

                String friendUuid = null;
                while (result.next()) {
                    friendUuid = result.getString("uuid");
                }
                PreparedStatement insertQuery = connection.prepareStatement("delete from Friend where friend_id = ?");
                insertQuery.setString(1, friendUuid);
                insertQuery.execute();
            } else {
                System.out.println("No friend found");
            }
        } catch (SQLException e) {
            System.out.println("Enter a valid contact number of a registered user!");
        }
    }

}
