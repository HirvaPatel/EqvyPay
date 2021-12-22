package com.eqvypay.util.constants;

public class DatabaseConstants {

    // test environment

    public static final String TEST_URL = "db.test_url";
    public static final String TEST_USERNAME = "db.test_username";
    public static final String TEST_PASSWORD = "db.test_password";

    // development environment

    public static final String DEV_URL = "db.dev_url";
    public static final String DEV_USERNAME = "db.dev_username";
    public static final String DEV_PASSWORD = "db.dev_password";

    // deployment environment

    public static final String PROD_URL = "db.prod_url";
    public static final String PROD_USERNAME = "db.prod_username";
    public static final String PROD_PASSWORD = "db.prod_password";

    // USER SQL STATEMENTS
    public static final String INSERT_USER = "INSERT INTO Users (uuid,name,email,contact,password,security_answer) VALUES (?,?,?,?,?,?)";

	// GROUP SQL STATEMENTS
	public static final String INSERT_GROUP = "INSERT INTO Groups (group_id,group_name,group_desc) VALUES (?,?,?)";

	// GROUP SQL STATEMENTS
	public static final String DELETE_GROUP = "DELETE FROM Groups WHERE group_id=?";
    
    // EXPENSE SQL STATEMENTS
    public static final String INSERT_EXPENSE = "INSERT INTO Expenses (id,sourceUserId,targetUserId,groupId,expenseType,expenseAmt,expenseDesc,currencyType) VALUES (?,?,?,?,?,?,?,?)";

    // PERSONAL ACTIVITY SQL STATEMENTS
    public static final String INSERT_PERSONAL_ACTIVITY = "INSERT INTO PersonalActivities (userId,amount,description,expenseCate,date) VALUES (?,?,?,?,?)";

    // GENERAL SQL
    public static final String SELECT = "SELECT * FROM %s ";


}
