package com.eqvypay.service.expense;

import com.eqvypay.persistence.IExpense;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.util.DtoUtils;
import com.eqvypay.util.constants.DatabaseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

/**
 * {@code ExpenseDataManipulation} implements the
 * {@code IExpenseDataManipulation} to provide a concrete
 * implementation for performing the queries related to
 * updating the Expense table in the database.
 */
@Service
public class ExpenseDataManipulation implements IExpenseDataManipulation {

    // reference of the database connection service class.
    @Autowired
    DatabaseConnectionManagementService dcms;

    // reference of the DtoUtils class.
    @Autowired
    DtoUtils dtoUtils;

    /**
     * Perform the operation to create
     * expense table in the database.
     *
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */
    @Override
    public void createTable() throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        Statement s = connection.createStatement();
        String query = "CREATE TABLE Expenses"
                + " ( id varchar(255)"
                + ",sourceUserId varchar(255)"
                + ",targetUserId varchar(266)"
                + ",groupId varchar(255)"
                + " ,expenseType varchar(255)"
                + " ,expenseAmt float"
                + " ,expenseDesc varchar(255)"
                + " ,currencyType varchar(255) );";

        if (!dtoUtils.tableExist(dcms, "Expenses")) {
            s.executeUpdate(query);
        }
    }

    /**
     * Perform the operation to save expense
     * in the expense database table.
     *
     * @param expense object of the IExpense.
     * @return saved object of the IExpense.
     * @throws Exception if any error occurs while
     *                   saving expense object.
     */
    @Override
    public IExpense save(IExpense expense) throws Exception {
        System.out.println("trying to save expense");
        expense.setId(UUID.randomUUID().toString());
        return expense;
    }

    /**
     * Perform the operation to save all the
     * expense in the expense database table.
     *
     * @param expenses List of the expenses.
     * @throws Exception if any error occurs while saving
     *                   all the expenses in the database.
     */
    @Override
    public void saveAll(List<IExpense> expenses) throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        if (!dtoUtils.tableExist(dcms, "Expenses")) {
            createTable();
        }

        for (IExpense expense : expenses) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(DatabaseConstants.INSERT_EXPENSE);
            preparedStatement.setString(1, expense.getId());
            preparedStatement.setString(2, expense.getSourceUserId());
            preparedStatement.setString(3, expense.getTargetUserId());
            preparedStatement.setString(4, expense.getGroupId());
            preparedStatement.setString(5, expense.getExpenseType().getType());
            preparedStatement.setFloat(6, expense.getExpenseAmt());
            preparedStatement.setString(7, expense.getExpenseDesc());
            preparedStatement.setString(8, expense.getCurrencyType());
            try {
                int count = preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
