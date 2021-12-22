package com.eqvypay.service.activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.eqvypay.persistence.IActivity;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import org.springframework.stereotype.Service;

@Service
public class ActivityDataManipulation implements IActivityDataManipulation{

	// reference of the database connection service class.
    
	@Autowired
	private DatabaseConnectionManagementService dcms;
	
	 /**
     * Perform the operation to create
     * activity of a user in the database.
     *
     * @param activity pojo containing activity details
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */
  

	@Override
	public boolean addActivity(IActivity activity) throws Exception {
		Connection connection = dcms.getConnection(dcms.parseEnvironment());
		PreparedStatement statement = connection.prepareStatement("INSERT INTO Activity (uuid,userId,message) VALUES (?,?,?)");
		statement.setString(1, UUID.randomUUID().toString());
		statement.setString(2, activity.getUserId());
		statement.setString(3, activity.getMessage());
		statement.executeUpdate();
		return true;
	}
	
    /**
     * Perform the operation to delete the activity
     * from the Activity table in the database.
     *
     * @param uuid unique id of the activity.
     * @throws Exception if any error occurs while
     *                   deleting user object.
     */

	@Override
	public boolean deleteActivity(String uuid) throws Exception {
		Connection connection = dcms.getConnection(dcms.parseEnvironment());
		PreparedStatement statement = connection.prepareStatement("DELETE FROM Activity where uuid = ?");
		statement.setString(1, uuid);
		int result = statement.executeUpdate();
		return true;
	}

	
	@Override
	public void createTable() throws Exception {
		Connection connection = dcms.getConnection(dcms.parseEnvironment());
		PreparedStatement statement = connection.prepareStatement("CREATE TABLE Activity(uuid varchar(255) primary key,userId varchar(255),message varchar(255));");
		statement.executeUpdate();
	}

}
