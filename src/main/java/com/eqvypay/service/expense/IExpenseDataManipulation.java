package com.eqvypay.service.expense;

import com.eqvypay.persistence.IExpense;

import java.util.List;

/**
 * {@code IExpenseDataManipulation} provides a contract
 * for performing operation related to data manipulation.
 */
public interface IExpenseDataManipulation {

    /**
     * Perform the operation to create
     * Expense table in the database.
     *
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */
    void createTable() throws Exception;

    /**
     * Perform the operation to save expense
     * in the expense database table.
     *
     * @param expense object of the expense.
     * @return saved object of the IExpense.
     * @throws Exception if any error occurs while
     *                   saving expense object.
     */
    IExpense save(IExpense expense) throws Exception;

    /**
     * Perform the operation to save all the
     * expense in the expense database table.
     *
     * @param expenses List of the expenses.
     * @throws Exception if any error occurs while
     *                   saving all the expenses in
     *                   the database
     */
    void saveAll(List<IExpense> expenses) throws Exception;
}
