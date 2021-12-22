package com.eqvypay.service.friends;

import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Statement;

/**
 * {@code FriendDataManipulation} implements the
 * {@code IFriendDataManipulation} to provide a concrete
 * implementation for performing the queries related to
 * updating the Friend table in the database.
 */
@Service
public class FriendDataManipulation implements IFriendDataManipulation {

    // reference of the database connection service class.
    @Autowired
    private DatabaseConnectionManagementService dcms;

    // reference of the DtoUtils class.
    @Autowired
    DtoUtils dtoUtils;

    /**
     * Perform the operation to create
     * Friend table in the database.
     *
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */
    @Override
    public void createTable() throws Exception {
        String query = "CREATE TABLE Friend"
                + " ( user_id varchar(255) "
                + " ,friend_id varchar(255) );";
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        Statement s = connection.createStatement();
        String tableName = "Friend";

        if (!dtoUtils.tableExist(dcms, tableName)) {
            s.executeUpdate(query);
        }
    }
}