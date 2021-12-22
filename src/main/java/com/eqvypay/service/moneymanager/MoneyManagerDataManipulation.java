package com.eqvypay.service.moneymanager;

import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Statement;

/**
 * {@code MoneyManagerDataManipulation} implements the
 * {@code IMoneyManagerDataManipulation} to provide a concrete
 * implementation for performing the queries related to
 * creating the PersonalActivities table in the database.
 */
@Service
public class MoneyManagerDataManipulation implements IMoneyManagerDataManipulation {

    // reference of the database connection service class.
    @Autowired
    DatabaseConnectionManagementService dcms;

    // reference of the DtoUtils class.
    @Autowired
    DtoUtils dtoUtils;

    /**
     * Perform the operation to create
     * PersonalActivities table in the database.
     *
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */
    @Override
    public void createTable() throws Exception {
        String query = "CREATE TABLE PersonalActivities"
                + " ( userId varchar(255)"
                + ",amount float"
                + ",description varchar(255)"
                + ",expenseCate varchar(255)"
                + " ,date varchar(255) );";

        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        Statement s = connection.createStatement();
        String tableName = "PersonalActivities";

        if (!dtoUtils.tableExist(dcms, tableName)) {
            s.executeUpdate(query);
        }
    }
}
