package com.eqvypay.service.activity;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eqvypay.persistence.Activity;
import com.eqvypay.persistence.IActivity;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.util.DtoUtils;
import com.eqvypay.util.constants.Environment;

@Service
/**
 * {@code FriendService} implements the
 * {@code FriendRepository} to provide a concrete
 * implementation for fetching activity information for a user
 */
public class ActivityService implements ActivityRepository {

	// reference of the database connection service class.
    @Autowired
	private DatabaseConnectionManagementService dcms;
	

    /**
     * @param userId userUUID of the user.
     * @return List<IActivity> Object of the user.
     * @throws Exception if any error occurs while performing
     *                   operation of fetching activity information
     *                   from the Activity table.
     */

	@Override
	public List<IActivity> getUserActivity(String userId) throws Exception {
		Connection connection = dcms.getConnection(dcms.parseEnvironment());
		PreparedStatement statement = connection.prepareStatement("SELECT * from Activity where userId = ?");
		statement.setString(1, userId);
		ResultSet resultSet = statement.executeQuery();
		return DtoUtils.getActivityFromResultSet(resultSet);
	}


}
