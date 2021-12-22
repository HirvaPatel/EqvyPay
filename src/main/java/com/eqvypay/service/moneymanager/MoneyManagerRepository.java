package com.eqvypay.service.moneymanager;

import com.eqvypay.persistence.IPersonalActivity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * {@code MoneyManagerRepository} provides a contract for
 * adding personal income,expenses and get all the personal
 * activities.
 */
@Repository
public interface MoneyManagerRepository {

    /**
     * @param activity object of the PersonalActivity.
     * @throws Exception if any error occurs while performing
     *                   operation of add income and expenses
     *                   in the personal activity table of the database.
     */
    void addIncomeExpense(IPersonalActivity activity) throws Exception;

    /**
     * @param userId unique id of the user.
     * @throws Exception if any error occurs while performing
     *                   operation to get all the personal activities
     *                   of this user.
     */
    ArrayList<IPersonalActivity> getActivities(String userId) throws Exception;

}
