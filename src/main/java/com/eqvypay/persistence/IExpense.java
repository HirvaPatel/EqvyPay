package com.eqvypay.persistence;

import com.eqvypay.util.constants.enums.ExpenseType;

public interface IExpense {

	/**
	 * Gets the source user's unique id.
	 *
	 * @return source user's unique id.
	 */
	String getSourceUserId();

	/**
	 * Sets the source user's unique id.
	 *
	 * @param sourceUserId unique id of source user.
	 */
	void setSourceUserId(String sourceUserId);

	/**
	 * Gets the target user's unique id.
	 *
	 * @return target user's unique id.
	 */
	String getTargetUserId();

	/**
	 * Sets the target user's unique id.
	 *
	 * @param targetUserId unique id of target user.
	 */
	void setTargetUserId(String targetUserId);

	/**
	 * Gets the group's unique id.
	 *
	 * @return group's unique id.
	 */
	String getGroupId();

	/**
	 * Sets the unique id of the group.
	 *
	 * @param groupId unique id of group.
	 */
	void setGroupId(String groupId);

	/**
	 * Gets the expense's amount.
	 *
	 * @return expense's amount.
	 */
	float getExpenseAmt();

	/**
	 * Sets the amount of the expense.
	 *
	 * @param expenseAmount amount of expense.
	 */
	void setExpenseAmt(float expenseAmount);

	/**
	 * Gets the expense's description.
	 *
	 * @return expense's description.
	 */
	String getExpenseDesc();

	/**
	 * Sets the target user's unique id.
	 *
	 * @param expenseDesc description of expense.
	 */
	void setExpenseDesc(String expenseDesc);

	/**
	 * Gets the type of currency.
	 *
	 * @return type of currency.
	 */
	String getCurrencyType();

	/**
	 * Sets the type of currency.
	 *
	 * @param currencyType type of currency.
	 */
	void setCurrencyType(String currencyType);

	/**
	 * Gets the expense's unique id.
	 *
	 * @return expense's unique id.
	 */
	String getId();

	/**
	 * Sets the target user's unique id.
	 *
	 * @param id unique id of expense.
	 */
	void setId(String id);

	/**
	 * Gets the type of expense.
	 *
	 * @return type of expense.
	 */
	ExpenseType getExpenseType();

	/**
	 * Sets the type of expense.
	 *
	 * @param expenseType type of expense.
	 */
	void setExpenseType(ExpenseType expenseType);

}
