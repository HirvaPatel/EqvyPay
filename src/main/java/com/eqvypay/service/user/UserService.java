package com.eqvypay.service.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import com.eqvypay.service.database.DatabaseConnectionManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eqvypay.persistence.IUser;
import com.eqvypay.util.DtoUtils;

/**
 * {@code UserService} implements the
 * {@code UserRepository} to provide a concrete
 * implementation for fetching user information
 * from the database table.
 *
 */
@Service
public class UserService implements UserRepository {

    // reference of the database connection service class.
    @Autowired
    private DatabaseConnectionManagementService dcms;

    // reference of the DtoUtils class.
    @Autowired
    DtoUtils dtoUtils;

    /**
     * @param email emailId of the user.
     * @param password password of the user.
     * @return IUser Object of the user.
     * @throws Exception if any error occurs while performing
     *                   operation of fetching user information
     *                   from the Users table.
     */
    @Override
    public IUser getUserByEmailAndPassword(String email, String password) throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from Users WHERE email ="+"'"+email+"'"+"AND password="+"'"+password+"'");
        return dtoUtils.getUserFromResultSet(resultSet);
    }

    /**
     * @param email emailId of the user.
     * @return IUser Object of the user.
     * @throws Exception if any error occurs while performing
     *                   operation of fetching user information
     *                   from the Users table.
     */
    @Override
    public IUser getByEmail(String email) throws Exception {
        if (dtoUtils.tableExist(dcms,"Users")) {
            Connection connection = dcms.getConnection(dcms.parseEnvironment());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from Users WHERE email ="+"'"+email+"'");
            return dtoUtils.getUserFromResultSet(resultSet);
        }
        return null;
    }

    /**
     * @param uuid unique id of the user.
     * @return IUser Object of the user.
     * @throws Exception if any error occurs while performing
     *                   operation of fetching user information
     *                   from the Users table.
     */
    @Override
    public IUser getByUuid(UUID uuid) throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from Users WHERE uuid = '"+uuid+"'");
        return dtoUtils.getUserFromResultSet(resultSet);
    }

    /**
     * @param userId unique id of the user.
     * @return List of IUser Object of the user.
     * @throws Exception if any error occurs while performing
     *                   operation of fetching information of
     *                   all the friends from the Users table
     *                   in the database.
     */
    @Override
    public List<IUser> findAllFriends(String userId) throws Exception {
        if(dtoUtils.tableExist(dcms, "Friend")) {
            Connection connection = dcms.getConnection(dcms.parseEnvironment());
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Friend inner join Users on Friend.friend_id = Users.uuid where Friend.user_id ='" + userId + "'");
            return dtoUtils.getAllFriendsFromResultSet(rs);
        } else {
            System.out.println("Your friend list is empty! Please add a friend first.");
            return null;
        }
    }
}
