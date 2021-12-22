package com.eqvypay.service.user;

import com.eqvypay.persistence.IUser;

import java.util.UUID;

/**
 * {@code IProfileDataManipulation} provides a contract
 * for performing operation of data manipulation on the
 * Users table in the database.
 */
public interface IUserDataManipulation {
    /**
     * Perform the operation to create
     * Users table in the database.
     *
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */
    void createTable() throws Exception;

    /**
     * Perform the operation to save the user
     * in the Users table in the database.
     *
     * @param user object of the User.
     * @throws Exception if any error occurs while
     *                   saving user object.
     */
    void save(IUser user) throws Exception;

    /**
     * Perform the operation to delete the user
     * from the Users table in the database.
     *
     * @param userId unique id of the user.
     * @throws Exception if any error occurs while
     *                   deleting user object.
     */
    void delete(UUID userId) throws Exception;
}
