package com.eqvypay.service.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.eqvypay.service.authentication.AuthenticationService;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.service.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eqvypay.persistence.IUser;

/**
 * {@code ProfileService} implements the
 * {@code ProfileRepository} to provide a concrete
 * implementation for updating user information
 * in the Users table.
 *
 */
@Service
public class ProfileService implements ProfileRepository {

	// reference of the database connection service class.
	@Autowired
	DatabaseConnectionManagementService dcms;

    // reference of the user repository class.
    @Autowired
    private UserRepository userRepo;

	/**
	 * @param user object of the user.
	 * @param username name of the user.
	 * @throws Exception if any error occurs while performing
	 *                   operation of update username in the
	 *                   User table of the database.
	 */
	@Override
	public void updateUsername(IUser user, String username) throws Exception {
		Connection connection = dcms.getConnection(dcms.parseEnvironment());
	
		PreparedStatement updateStatement = connection.prepareStatement("update Users set name=? where email=?");
		updateStatement.setString(1, username);
		updateStatement.setString(2, user.getEmail());
		
		boolean update = updateStatement.execute();				
	}

	/**
	 * @param user object of the user.
	 * @param contact contact number of the user.
	 * @throws Exception if any error occurs while performing
	 *                   operation of update contact number in the
	 *                   User table of the database.
	 */
	@Override
	public void updateContact(IUser user, String contact) throws Exception {
		Connection connection = dcms.getConnection(dcms.parseEnvironment());
		
		PreparedStatement updateStatement = connection.prepareStatement("update Users set contact=? where email=?");
		updateStatement.setString(1, contact);
		updateStatement.setString(2, user.getEmail());
		
		boolean update = updateStatement.execute();
	}

	/**
	 * @param user object of the user.
	 * @param password password of the user.
	 * @throws Exception if any error occurs while performing
	 *                   operation of update password in the
	 *                   User table of the database.
	 */
	@Override
	public void updatePassword(IUser user, String password) throws Exception {
		Connection connection = dcms.getConnection(dcms.parseEnvironment());
		String hashedPassword = AuthenticationService.getHashedPassword(password);
		PreparedStatement updateStatement = connection.prepareStatement("update Users set password=? where email=?");
		updateStatement.setString(1, hashedPassword);
		updateStatement.setString(2, user.getEmail());
		
		boolean update = updateStatement.execute();
	}

}
