package com.eqvypay.service.activity;

import java.util.List;

import com.eqvypay.persistence.IActivity;

/**
 * {@code IActivityDataManipulation} provides a contract
 * for performing operation of data manipulation on the
 * Activity table in the database.
 */

public interface IActivityDataManipulation {
    /**
     * Perform the operation to create
     * activity in the database.
     *
     * @param activity pojo containing all the activity details
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */

	boolean addActivity(IActivity activity) throws Exception;
	
    /**
     * Perform the operation to delete
     * activity from table in the database.
     * 
     * 
     * @param uuid unique id for activity
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */

	boolean deleteActivity(String uuid) throws Exception;

	/**
     * Perform the operation to create
     * Activity table in the database.
     *
     * @throws Exception if any error occurs while creating
     *                   the table in the database.
     */
	void createTable() throws Exception;

}
