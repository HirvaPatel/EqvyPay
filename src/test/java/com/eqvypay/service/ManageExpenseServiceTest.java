package com.eqvypay.service;

import com.eqvypay.persistence.Expense;
import com.eqvypay.persistence.Group;
import com.eqvypay.persistence.IExpense;
import com.eqvypay.persistence.IGroup;
import com.eqvypay.persistence.IUser;
import com.eqvypay.persistence.User;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.service.expense.ExpenseDataManipulation;
import com.eqvypay.service.expense.ExpenseRepository;
import com.eqvypay.util.constants.enums.ExpenseType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class ManageExpenseServiceTest {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    ExpenseDataManipulation expenseDataManipulation;

    @Autowired
    DatabaseConnectionManagementService dcms;

    @Test
    @Order(1)
    public void testCreateTable() throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());

        expenseDataManipulation.createTable();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SHOW tables;");
        ArrayList<String> tables = new ArrayList<>();
        while (resultSet.next()) {
            String table = resultSet.getString("Tables_in_CSCI5308_11_TEST");
            tables.add(table);
        }

        Assertions.assertTrue(tables.contains("Expenses"));
    }

    @Test
    @Order(2)
    public void testExpenseIsNotNull() throws Exception {
        Group testGroup = new Group();
        testGroup.setGroupId("TEST_ADD_EXPENSE_GROUP_ID");
        testGroup.setGroupName("TEST_ADD_EXPENSE_GROUP_NAME");
        testGroup.setGroupDesc("TEST_ADD_EXPENSE_GROUP_DESC");

        User testUser = new User();

        Expense testExpense = new Expense();
        testExpense.setGroupId(testGroup.getGroupId());
        testExpense.setExpenseDesc("testing group");
        testExpense.setExpenseAmt(102F);
        testExpense.setCurrencyType("CAD");
        testExpense.setExpenseType(ExpenseType.GROUP);
        testExpense.setTargetUserId(testUser.getUuid().toString());

        Assertions.assertNotNull(testExpense);
    }

    @Test
    @Order(3)
    public void testGetExpensesByUserId() throws Exception {
        User testUser1 = new User();
        User testUser2 = new User();

        IExpense testExpense1 = new Expense();
        testExpense1.setId(UUID.randomUUID().toString());
        testExpense1.setExpenseDesc("testing expense 1");
        testExpense1.setExpenseAmt(105F);
        testExpense1.setCurrencyType("CAD");
        testExpense1.setExpenseType(ExpenseType.INDIVIDUAL);
        testExpense1.setSourceUserId(testUser2.getUuid().toString());
        testExpense1.setTargetUserId(testUser1.getUuid().toString());

        List<IExpense> expenses = new ArrayList<>();
        expenses.add(testExpense1);

        expenseDataManipulation.saveAll(expenses);

        List<IExpense> expensesResult =  expenseRepository.getExpensesByUserId(testUser1.getUuid().toString());
        boolean hasExpenses = expensesResult != null && expensesResult.size() > 0;
        Assertions.assertTrue(hasExpenses);
    }

    @Test
    @Order(4)
    public void testSaveAllExpenses() throws Exception {
        IGroup testGroup = new Group();
        testGroup.setGroupId("TEST_ADD_EXPENSE_GROUP_ID");
        testGroup.setGroupName("TEST_ADD_EXPENSE_GROUP_NAME");
        testGroup.setGroupDesc("TEST_ADD_EXPENSE_GROUP_DESC");

        IUser testUser = new User();

        IExpense testExpense1 = new Expense();
        testExpense1.setGroupId(testGroup.getGroupId());
        testExpense1.setExpenseDesc("testing expense 1");
        testExpense1.setExpenseAmt(105F);
        testExpense1.setCurrencyType("CAD");
        testExpense1.setExpenseType(ExpenseType.GROUP);
        testExpense1.setTargetUserId(testUser.getUuid().toString());
        testExpense1 = expenseDataManipulation.save(testExpense1);

        IExpense testExpense2 = new Expense();
        testExpense2.setGroupId(testGroup.getGroupId());
        testExpense2.setExpenseDesc("testing expense 2");
        testExpense2.setExpenseAmt(50F);
        testExpense2.setCurrencyType("CAD");
        testExpense2.setExpenseType(ExpenseType.GROUP);
        testExpense2.setTargetUserId(testUser.getUuid().toString());
        testExpense2 = expenseDataManipulation.save(testExpense2);

        List<IExpense> expenses = new ArrayList<>();
        expenses.add(testExpense1);
        expenses.add(testExpense2);

        expenseDataManipulation.saveAll(expenses);
        List<IExpense> expensesResult = expenseRepository.getExpensesByUserId(testUser.getUuid().toString());

        boolean hasExpense1 = false;
        boolean hasExpense2 = false;
        for (IExpense expense : expensesResult) {
            if(expense.getId().equals(testExpense1.getId())){
                hasExpense1 = true;
            }
            if (expense.getId().equals(testExpense2.getId())) {
                hasExpense2 = true;
            }
        }

        Assertions.assertTrue(hasExpense1 && hasExpense2);
    }

    @Test
    @Order(5)
    public void testSettleExpense() throws Exception {
        IUser testUser1 = new User();
        IUser testUser2 = new User();

        IExpense testExpense = new Expense();
        testExpense.setId(UUID.randomUUID().toString());
        testExpense.setExpenseDesc("testing expense 1");
        testExpense.setExpenseAmt(105F);
        testExpense.setCurrencyType("CAD");
        testExpense.setExpenseType(ExpenseType.INDIVIDUAL);
        testExpense.setSourceUserId(testUser2.getUuid().toString());
        testExpense.setTargetUserId(testUser1.getUuid().toString());

        List<IExpense> expenses = new ArrayList<>();
        expenses.add(testExpense);

        expenseDataManipulation.saveAll(expenses);
        boolean hasSettled = expenseRepository.settleExpense(testExpense);

        Assertions.assertTrue(hasSettled);
    }
}
