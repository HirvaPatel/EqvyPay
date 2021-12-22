package com.eqvypay.persistence;

import com.eqvypay.util.constants.enums.ExpenseType;

/**
 * {@code Expense} implements the
 * {@code IExpense} and Expense class stores
 * the information related to all the expenses.
 * This class acts as a single object/entry/model.
 */
public class Expense implements IExpense {

    // Unique id of the expense
    private String id;

    // Unique id of the user who need to pay
    private String targetUserId;

    // Unique id of the group
    private String groupId;

    // type of expense i.e. GROUP, FRIEND
    private ExpenseType expenseType;

    // Amount of the money
    private float expenseAmount;

    // Description of the expense
    private String expenseDesc;

    // Type of currency i.e. CAD, USD
    private String currencyType;

    // Unique id of the user who added the expense
    private String sourceUserId;


    /**
     * Gets the source user's unique id.
     *
     * @return source user's unique id.
     */
    @Override
    public String getSourceUserId() {
        return sourceUserId;
    }

    /**
     * Sets the source user's unique id.
     *
     * @param sourceUserId unique id of source user.
     */
    @Override
    public void setSourceUserId(String sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    /**
     * Gets the target user's unique id.
     *
     * @return target user's unique id.
     */
    @Override
    public String getTargetUserId() {
        return targetUserId;
    }

    /**
     * Sets the target user's unique id.
     *
     * @param targetUserId unique id of target user.
     */
    @Override
    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    /**
     * Gets the group's unique id.
     *
     * @return group's unique id.
     */
    @Override
    public String getGroupId() {
        return groupId;
    }

    /**
     * Sets the unique id of the group.
     *
     * @param groupId unique id of group.
     */
    @Override
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets the expense's amount.
     *
     * @return expense's amount.
     */
    @Override
    public float getExpenseAmt() {
        return expenseAmount;
    }

    /**
     * Sets the amount of the expense.
     *
     * @param expenseAmount amount of expense.
     */
    @Override
    public void setExpenseAmt(float expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    /**
     * Gets the expense's description.
     *
     * @return expense's description.
     */
    @Override
    public String getExpenseDesc() {
        return expenseDesc;
    }

    /**
     * Sets the target user's unique id.
     *
     * @param expenseDesc description of expense.
     */
    @Override
    public void setExpenseDesc(String expenseDesc) {
        this.expenseDesc = expenseDesc;
    }

    /**
     * Gets the expense's unique id.
     *
     * @return expense's unique id.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Sets the target user's unique id.
     *
     * @param id unique id of expense.
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the type of currency.
     *
     * @return type of currency.
     */
    @Override
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * Sets the type of currency.
     *
     * @param currencyType type of currency.
     */
    @Override
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    /**
     * Gets the type of expense.
     *
     * @return type of expense.
     */
    @Override
    public ExpenseType getExpenseType() {
        return expenseType;
    }

    /**
     * Sets the type of expense.
     *
     * @param expenseType type of expense.
     */
    @Override
    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }
}
