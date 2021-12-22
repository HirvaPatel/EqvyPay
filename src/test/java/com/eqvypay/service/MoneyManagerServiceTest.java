package com.eqvypay.service;

import com.eqvypay.persistence.IPersonalActivity;
import com.eqvypay.persistence.IUser;
import com.eqvypay.persistence.PersonalActivity;
import com.eqvypay.persistence.User;
import com.eqvypay.service.authentication.AuthenticationService;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.service.moneymanager.MoneyManagerDataManipulation;
import com.eqvypay.service.moneymanager.MoneyManagerRepository;
import com.eqvypay.service.user.UserDataManipulation;
import com.eqvypay.service.user.UserRepository;
import com.eqvypay.util.constants.Environment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@SpringBootTest
public class MoneyManagerServiceTest {

    @Autowired
    MoneyManagerDataManipulation moneyManagerDataManipulation;

    @Autowired
    MoneyManagerRepository moneyManagerRepository;

    @Autowired
    DatabaseConnectionManagementService dcms;

    @Autowired
    UserDataManipulation userDataManipulation;

    @Autowired
    UserRepository userRepository;

    @Test
    @Order(1)
    public void testCreateTable() throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());

        moneyManagerDataManipulation.createTable();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SHOW tables;");
        ArrayList<String> tables = new ArrayList<>();
        while (resultSet.next()) {
            String table = resultSet.getString("Tables_in_CSCI5308_11_TEST");
            tables.add(table);
        }

        Assertions.assertTrue(tables.contains("PersonalActivities"));
    }

    @Test
    @Order(3)
    public void testAddIncomeExpense() throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());

        moneyManagerDataManipulation.createTable();
        IUser user = new User();
        user.setName("ADD_INCOME_TEST_USER");
        user.setEmail("testAddIncome@gmail.com");
        user.setContact("1234567891");
        user.setPassword(AuthenticationService.getHashedPassword("Test@123"));
        user.setSecurityAnswer("test");
        userDataManipulation.save(user);

        userRepository.getByEmail("testAddIncome@gmail.com");

        IPersonalActivity personalActivity = new PersonalActivity();
        personalActivity.setAmount(50F);
        personalActivity.setDate("12/05/2021");
        personalActivity.setExpenseCategory("food");
        personalActivity.setDescription("grocery");
        personalActivity.setUserId(user.getUuid().toString());

        moneyManagerRepository.addIncomeExpense(personalActivity);
        PreparedStatement selectQuery = connection.prepareStatement("select * from PersonalActivities where userId = ?");
        selectQuery.setString(1, user.getUuid().toString());
        ResultSet result = selectQuery.executeQuery();
        while (result.next()) {
            Assertions.assertEquals(result.getFloat("amount"), 50);
            Assertions.assertEquals(result.getString("date"), "12/05/2021");
            Assertions.assertEquals(result.getString("expenseCate"), "food");
            Assertions.assertEquals(result.getString("description"), "grocery");
        }
    }

    @Test
    @Order(4)
    public void testGetActivities() throws Exception {
        IUser user = userRepository.getByEmail("hirva@gmail.com");
        ArrayList<IPersonalActivity> activities = moneyManagerRepository.getActivities(user.getUuid().toString());
        for (IPersonalActivity activity : activities) {
            Assertions.assertEquals(activity.getAmount(), 50);
            Assertions.assertEquals(activity.getDate(), "12/05/2021");
            Assertions.assertEquals(activity.getExpenseCategory(), "food");
            Assertions.assertEquals(activity.getDescription(), "grocery");
        }
    }

}
