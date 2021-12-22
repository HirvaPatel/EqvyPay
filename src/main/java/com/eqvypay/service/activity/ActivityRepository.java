package com.eqvypay.service.activity;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.eqvypay.persistence.Activity;
import com.eqvypay.persistence.IActivity;

@Repository
@Service
/**
 * {@code ActivityRepository} provides a contract
 * for get detailed activity of a user.
 */
public interface ActivityRepository {

    /**
     * @param userId userUUID of the user.
     * @return List<IActivity> Object of the user.
     * @throws Exception if any error occurs while performing
     *                   operation of fetching activity information
     *                   from the Activity table.
     */

	List<IActivity> getUserActivity(String userId) throws Exception;
}
