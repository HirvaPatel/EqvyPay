package com.eqvypay.service.profile;

import org.springframework.stereotype.Repository;

import com.eqvypay.persistence.IUser;

/**
 * {@code MoneyManagerRepository} provides a contract for
 * performing update query on the Users table.
 */
@Repository
public interface ProfileRepository {

    /**
     * @param user object of the user.
     * @param username name of the user.
     * @throws Exception if any error occurs while performing
     *                   operation of update username in the
     *                   User table of the database.
     */
    void updateUsername(IUser user, String username) throws Exception;

    /**
     * @param user object of the user.
     * @param contact contact number of the user.
     * @throws Exception if any error occurs while performing
     *                   operation of update contact number in the
     *                   User table of the database.
     */
    void updateContact(IUser user, String contact) throws Exception;

    /**
     * @param user object of the user.
     * @param password password of the user.
     * @throws Exception if any error occurs while performing
     *                   operation of update password in the
     *                   User table of the database.
     */
    void updatePassword(IUser user, String password) throws Exception;
}
