package com.eqvypay.service.user;

import com.eqvypay.persistence.IUser;
import com.eqvypay.persistence.User;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.util.DtoUtils;
import com.eqvypay.util.constants.DatabaseConstants;
import com.eqvypay.util.constants.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

@Service
public class UserDataManipulation implements IUserDataManipulation {

    // reference of the database connection service class.
    @Autowired
    private DatabaseConnectionManagementService dcms;

    // reference of the DtoUtils class.
    @Autowired
    DtoUtils dtoUtils;

    /**
     * Perform the operation to create
     * Users table in the database.
     *
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */
    @Override
    public void createTable() throws Exception {
        String query = "CREATE TABLE Users"
                + " ( uuid varchar(255) PRIMARY KEY"
                + " ,name varchar(255)"
                + " ,email varchar(255)"
                + " ,contact varchar(255)"
                + " ,password varchar(255)"
                + " ,security_answer varchar(255) );";
        Connection connection = dcms.getConnection(dcms.parseEnvironment());

        Statement s = connection.createStatement();
        String tableName = "Users";
        if (!dtoUtils.tableExist(dcms, tableName)) {
            s.executeUpdate(query);
        }
    }

    /**
     * Perform the operation to save the user
     * in the Users table in the database.
     *
     * @param user object of the User.
     * @throws Exception if any error occurs while
     *                   saving user object.
     */
    @Override
    public void save(IUser user) throws Exception {
        createTable();

        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        PreparedStatement preparedStatement = connection.prepareStatement(DatabaseConstants.INSERT_USER);
        preparedStatement.setString(1, user.getUuid().toString());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getContact());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setString(6, user.getSecurityAnswer());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("user inserted");
        }
    }

    /**
     * Perform the operation to delete the user
     * from the Users table in the database.
     *
     * @param userId unique id of the user.
     * @throws Exception if any error occurs while
     *                   deleting user object.
     */
    @Override
    public void delete(UUID userId) throws Exception {
        if (dtoUtils.tableExist(dcms, "Users")) {
            Connection connection = dcms.getConnection(dcms.parseEnvironment());
            PreparedStatement statement = connection.prepareStatement("DELETE from Users where uuid = ?");
            statement.setString(1, userId.toString());
            int count = statement.executeUpdate();
            if (count > 0) {
                System.out.println("user deleted");
            }
        } else {
            System.out.println("Table 'Users' does not exist.");
        }
    }

}
