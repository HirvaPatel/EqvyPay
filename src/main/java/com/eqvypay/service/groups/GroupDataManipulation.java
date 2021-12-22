package com.eqvypay.service.groups;

import com.eqvypay.service.database.DatabaseConnectionManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Statement;

/**
 * {@code GroupDataManipulation} implements the
 * {@code IGroupDataManipulation} to provide a concrete
 * implementation for performing the queries operations related
 * to updating the Groups and GroupMembers table in the database.
 */
@Service
public class GroupDataManipulation implements IGroupDataManipulation {

    // reference of the database connection service class.
    @Autowired
    DatabaseConnectionManagementService dcms;

    /**
     * Perform the operation to create
     * Groups table in the database.
     *
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */
    @Override
    public void createTable() throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE TABLE Groups"
                + " ( group_id varchar(255)"
                + ",group_name varchar(255)"
                + ",group_desc varchar(255)  );");
        System.out.println("Groups table created");

    }

    /**
     * Perform the operation to create
     * GroupMembers table in the database.
     *
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */
    @Override
    public void createGroupMembersTable() throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE TABLE GroupMembers"
                + " ( group_id varchar(255)"
                + ",uuid varchar(255)  );");
        System.out.println("Groups table created");

    }
}
