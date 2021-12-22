package com.eqvypay.service.moneymanager;

/**
 * {@code IMoneyManagerDataManipulation} provides a contract
 * for performing operation related to data manipulation.
 */
public interface IMoneyManagerDataManipulation {
    /**
     * Perform the operation to create
     * PersonalActivities table in the database.
     *
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */
    void createTable() throws Exception;
}
