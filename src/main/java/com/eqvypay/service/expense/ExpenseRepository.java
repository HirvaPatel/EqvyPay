package com.eqvypay.service.expense;


import java.util.List;

import com.eqvypay.persistence.Expense;
import com.eqvypay.persistence.IExpense;

import org.springframework.stereotype.Repository;

/**
 * {@code ExpenseRepository} provides a contract
 * for getting all the expenses and to settle up expenses.
 */
@Repository
public interface ExpenseRepository{
    /**
     * @param userId of this user.
     * @return List<IExpense> of expenses of this user.
     * @throws Exception if any error occurs while getting all the
     *                   expenses records of the user from the database.
     *
     */
    List<IExpense> getExpensesByUserId(String userId) throws Exception;

    /**
     * @param expense of this user.
     * @return boolean status of the operation.
     * @throws Exception if any error occurs while settle
     *                   up the expenses' of this user.
     *
     */
    boolean settleExpense(IExpense expense) throws Exception;

}
