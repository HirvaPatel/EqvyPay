package com.eqvypay.service.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eqvypay.persistence.Expense;
import com.eqvypay.persistence.IExpense;

@Service
public class ExpenseFactory {

    // static instance of ExpenseFactory class
    private static ExpenseFactory expenseFactory = null;

    // reference of the expense data manipulation.
    @Autowired
    IExpenseDataManipulation expenseDataManipulation;

    // reference of the expense repository.
    @Autowired
    ExpenseRepository expenseRepository;

    // reference of the expense.
    IExpense expense;

    public ExpenseFactory() {
        expense = new Expense();
    }

    // returns the instance of the expense factory.
    public static ExpenseFactory getInstance() {
        if (expenseFactory == null) {
            expenseFactory = new ExpenseFactory();
        }
        return expenseFactory;
    }

    // returns the reference of the expense data manipulation.
    public IExpenseDataManipulation getExpenseDataManipulation() {
        return expenseDataManipulation;
    }

    // returns the reference of the expense repository.
    public ExpenseRepository getExpenseRepository() {
        return expenseRepository;
    }

    // returns the reference of the expense.
    public IExpense getExpense() {
        expense = new Expense();
        return expense;
    }
}
