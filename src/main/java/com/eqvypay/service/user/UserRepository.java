package com.eqvypay.service.user;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.eqvypay.persistence.IUser;

/**
 * {@code MoneyManagerRepository} provides a contract for
 * performing update query on the Users table.
 */
@Repository
public interface UserRepository {

	/**
	 * @param email emailId of the user.
	 * @param password password of the user.
	 * @return IUser Object of the user.
	 * @throws Exception if any error occurs while performing
	 *                   operation of fetching user information
	 *                   from the Users table.
	 */
	IUser getUserByEmailAndPassword(String email, String password) throws Exception;

	/**
	 * @param email emailId of the user.
	 * @return IUser Object of the user.
	 * @throws Exception if any error occurs while performing
	 *                   operation of fetching user information
	 *                   from the Users table.
	 */
	IUser getByEmail(String email) throws Exception;

	/**
	 * @param uuid unique id of the user.
	 * @return IUser Object of the user.
	 * @throws Exception if any error occurs while performing
	 *                   operation of fetching user information
	 *                   from the Users table.
	 */
	IUser getByUuid(UUID uuid) throws Exception;

	/**
	 * @param userId unique id of the user.
	 * @return List of IUser Object of the user.
	 * @throws Exception if any error occurs while performing
	 *                   operation of fetching information of
	 *                   all the friends from the Users table
	 *                   in the database.
	 */
	List<IUser> findAllFriends(String userId) throws Exception;
}
