package com.eqvypay.service.groups;

/**
 * {@code IGroupDataManipulation} provides a contract
 * for performing operation related to data manipulation.
 */
public interface IGroupDataManipulation {

    /**
     * Perform the operation to create
     * Groups table in the database.
     *
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */
    void createTable() throws Exception;

    /**
     * Perform the operation to create
     * GroupMembers table in the database.
     *
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */
    void createGroupMembersTable() throws Exception;
}
