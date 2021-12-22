package com.eqvypay.service.expense;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.eqvypay.persistence.Expense;
import com.eqvypay.persistence.IExpense;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eqvypay.util.DtoUtils;
import com.eqvypay.util.constants.Environment;

/**
 * {@code ExpenseService} implements the
 * {@code ExpenseRepository} to provide a concrete implementation
 * for fetching and settling up expenses of this user.
 *
 */
@Service
public class ExpenseService implements ExpenseRepository {

    // reference of the database connection service class.
    @Autowired
    private DatabaseConnectionManagementService dcms;

    // reference of the DtoUtils class.
    @Autowired
    DtoUtils dtoUtils;

    /**
     * Gets the query to user ID to fetch the expenses records from
     * the database.
     *
     * @param userId unique id of the user.
     * @return list of expenses of this user.
     * @throws Exception if an error occurs while getting database
     *                   connection or performing sql query operation.
     *
     */
    @Override
    public List<IExpense> getExpensesByUserId(String userId) throws Exception {
        if (dtoUtils.tableExist(dcms, "Expenses")) {
            Connection connection = dcms.getConnection(dcms.parseEnvironment());
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from Expenses where sourceUserId = '" + userId + "'"
                    + " OR targetUserId = '" + userId + "'");
            return dtoUtils.getExpenseFromResultSet(rs);
        } else {
            return null;
        }
    }

    /**
     * Gets the object of the expense to settle up that expense.
     *
     * @param expense object of the IExpense.
     * @return status of the operation.
     * @throws Exception if an error occurs while getting database
     *                   connection or performing sql query operation.
     *
     */
    @Override
    public boolean settleExpense(IExpense expense) throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        Statement statement = connection.createStatement();
        int count = statement.executeUpdate("DELETE from Expenses where id = '" + expense.getId() + "'");
        if (count > 0) {
            System.out.println("Success!");
            return true;
        }
        return false;
    }

}
