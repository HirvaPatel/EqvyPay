package com.eqvypay.service.moneymanager;

import com.eqvypay.persistence.*;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.util.DtoUtils;
import com.eqvypay.util.constants.DatabaseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * {@code MoneyManagerService} implements the
 * {@code MoneyManagerRepository} to provide a concrete implementation
 *  for adding personal income,expenses and get all the personal
 *  activities.
 */
@Service
public class MoneyManagerService implements MoneyManagerRepository {

    // reference of the database connection service class.
    @Autowired
    private DatabaseConnectionManagementService dcms;

    // reference of the DtoUtils class.
    @Autowired
    DtoUtils dtoUtils;

    /**
     * @param activity object of the PersonalActivity.
     * @throws Exception if any error occurs while performing
     *                   operation of add income and expenses
     *                   in the personal activity table of the database.
     */
    @Override
    public void addIncomeExpense(IPersonalActivity activity) throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        PreparedStatement preparedStatement = connection.prepareStatement(DatabaseConstants.INSERT_PERSONAL_ACTIVITY);

        preparedStatement.setString(1, activity.getUserId());
        preparedStatement.setString(2, activity.getAmount().toString());
        preparedStatement.setString(3, activity.getDescription());
        preparedStatement.setString(4, activity.getExpenseCategory());
        preparedStatement.setString(5, activity.getDate());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Activity added successfully");
        }
    }

    /**
     * @param userId unique id of the user.
     * @throws Exception if any error occurs while performing
     *                   operation to get all the personal activities
     *                   of this user.
     */
    @Override
    public ArrayList<IPersonalActivity> getActivities(String userId) throws Exception {
        if (dtoUtils.tableExist(dcms, "PersonalActivities")) {
            Connection connection = dcms.getConnection(dcms.parseEnvironment());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from PersonalActivities WHERE userId ='" + userId + "'");
            return dtoUtils.getAllActivities(resultSet);
        }
        return null;
    }
}
