package com.eqvypay.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.eqvypay.persistence.Activity;
import com.eqvypay.persistence.Expense;
import com.eqvypay.persistence.Group;
import com.eqvypay.persistence.IActivity;
import com.eqvypay.persistence.IExpense;
import com.eqvypay.persistence.IGroup;
import com.eqvypay.persistence.IPersonalActivity;
import com.eqvypay.persistence.IUser;
import com.eqvypay.persistence.PersonalActivity;
import com.eqvypay.persistence.User;
import com.eqvypay.service.activity.ActivityFactory;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.service.expense.ExpenseFactory;
import com.eqvypay.service.groups.GroupFactory;
import com.eqvypay.service.moneymanager.MoneyManagerFactory;
import com.eqvypay.service.user.UserFactory;
import com.eqvypay.util.constants.Environment;
import com.eqvypay.util.constants.enums.ExpenseType;
import org.springframework.stereotype.Component;

@Component
public class DtoUtils {

    public boolean tableExist(DatabaseConnectionManagementService dcms, String tableName) throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        boolean tableExists = false;
        try (ResultSet rs = connection.getMetaData().getTables(null, null, tableName, null)) {
            while (rs.next()) {
                String tName = rs.getString("TABLE_NAME");
                if (tName != null && tName.equals(tableName)) {
                    tableExists = true;
                    break;
                }
            }
        }
        return tableExists;

    }

    public int getCountOfRecords(ResultSet rs){
        int count = 0;
        try {
            while (rs.next())
                count++;
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return count;

    }

    public List<String> getIdFromResultSet(ResultSet resultSet) {
        List<String> ids = new ArrayList<>();
        try {
            while (resultSet.next()){
                ids.add(resultSet.getString("group_id"));
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return ids;
    }
    public IUser getUserFromResultSet(ResultSet resultSet) throws SQLException {
        IUser user = UserFactory.getInstance().getUser();
        while (resultSet.next()) {
            String id = resultSet.getString("uuid");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String contact = resultSet.getString("contact");
            String securityAnswer = resultSet.getString("security_answer");
            String password = resultSet.getString("password");
            user.setUuid(UUID.fromString(id));
            user.setName(name);
            user.setEmail(email);
            user.setContact(contact);
            user.setSecurityAnswer(securityAnswer);
            user.setPassword(password);
        }
        return user;

    }

    public List<IExpense> getExpenseFromResultSet(ResultSet resultSet) throws Exception {

        List<IExpense> expenses = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String targetUserId = resultSet.getString("targetUserId");
            String groupId = resultSet.getString("groupId");
            String expenseType = resultSet.getString("expenseType");
            float expenseAmt = resultSet.getFloat("expenseAmt");
            String expenseDesc = resultSet.getString("expenseDesc");
            String currencyType = resultSet.getString("currencyType");
            String sourceUserId = resultSet.getString("sourceUserId");

            IExpense expense = ExpenseFactory.getInstance().getExpense();
            expense.setId(id);
            expense.setCurrencyType(currencyType);
            expense.setExpenseAmt(expenseAmt);
            expense.setExpenseType(ExpenseType.valueOf(expenseType));
            expense.setGroupId(groupId);
            expense.setSourceUserId(sourceUserId);
            expense.setTargetUserId(targetUserId);
            expense.setExpenseDesc(expenseDesc);

            expenses.add(expense);
        }
        return expenses;
    }

    public ArrayList<IGroup> getGroupsFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<IGroup> groups = new ArrayList<IGroup>();
        IGroup group;
        while (resultSet.next()) {
            group = GroupFactory.getInstance().getGroup();
            String groupId = resultSet.getString("group_id");
            String groupName = resultSet.getString("group_name");
            String groupDesc = resultSet.getString("group_desc");
            group.setGroupId(groupId);
            group.setGroupName(groupName);
            group.setGroupDesc(groupDesc);
            groups.add(group);
        }
        return groups;
    }

    public ArrayList<IPersonalActivity> getAllActivities(ResultSet resultSet) throws SQLException {
        ArrayList<IPersonalActivity> activities = new ArrayList<IPersonalActivity>();
        IPersonalActivity activity;
        while (resultSet.next()) {
            activity = MoneyManagerFactory.getInstance().getPersonalActivity();
            String userId = resultSet.getString("userId");
            String amount = resultSet.getString("amount");
            String description = resultSet.getString("description");
            String expenseCategory = resultSet.getString("expenseCate");
            String date = resultSet.getString("date");

            activity.setUserId(userId);
            activity.setAmount(Float.parseFloat(amount));
            activity.setDescription(description);
            activity.setExpenseCategory(expenseCategory);
            activity.setDate(date);
            activities.add(activity);
        }
        return activities;
    }

    public ArrayList<IUser> getAllFriendsFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<IUser> friends = new ArrayList<IUser>();
        IUser friend;
        while (resultSet.next()) {
            friend = UserFactory.getInstance().getUser();
            String friendId = resultSet.getString("friend_id");
            String friendName = resultSet.getString("name");
            String friendEmail = resultSet.getString("email");

            friend.setUuid(UUID.fromString(friendId));
            friend.setName(friendName);
            friend.setEmail(friendEmail);

            friends.add(friend);
        }
        return friends;
    }
    
	public static List<IActivity> getActivityFromResultSet(ResultSet resultSet) throws SQLException {
		List<IActivity> activities = new ArrayList<>();
		while(resultSet.next()) {
			String uuid = resultSet.getString("uuid");
			String userId = resultSet.getString("userId");
			String message = resultSet.getString("message");
			IActivity activity = ActivityFactory.getInstance().getActivity();
			activity.setMessage(message);
			activity.setUserId(userId);
			activity.setUuid(uuid);
			activities.add(activity);
		}
		return activities;
	}

}
