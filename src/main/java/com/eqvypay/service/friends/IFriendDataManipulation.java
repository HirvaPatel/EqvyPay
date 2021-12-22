package com.eqvypay.service.friends;

/**
 * {@code IFriendDataManipulation} provides a contract
 * for performing operation related to data manipulation.
 */
public interface IFriendDataManipulation {

    /**
     * Perform the operation to create
     * Friend table in the database.
     *
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */
    void createTable() throws Exception;
}