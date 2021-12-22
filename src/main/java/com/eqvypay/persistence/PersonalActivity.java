package com.eqvypay.persistence;

/**
 * {@code PersonalActivity} implements the
 * {@code IPersonalActivity} and PersonalActivity
 * class stores the information related to all
 * the personal activity.
 * This class acts as a single object/entry/model.
 */
public class PersonalActivity implements IPersonalActivity {

    // Unique id of the user.
    private String userId;

    // Amount that user add.
    private Float amount;

    // Description of the personal activity.
    private String description;

    // Category of the personal activity
    private String expenseCategory;

    // date of the personal activity.
    private String date;

    /**
     * Gets the user's unique id.
     *
     * @return user's unique id.
     */
    @Override
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user's unique id.
     *
     * @param userId unique id of group.
     */
    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the added amount.
     *
     * @return amount that user add.
     */
    @Override
    public Float getAmount() {
        return amount;
    }

    /**
     * Sets the amount that user add.
     *
     * @param amount amount that user add.
     */
    @Override
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    /**
     * Gets the description of the personal activity.
     *
     * @return description of the personal activity.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the personal activity.
     *
     * @param description personal activity's description.
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the category of the personal activity.
     *
     * @return category of the personal activity.
     */
    @Override
    public String getExpenseCategory() {
        return expenseCategory;
    }

    /**
     * Sets the category of the personal activity.
     *
     * @param expenseCategory category of the personal activity.
     */
    @Override
    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    /**
     * Gets the date of the personal activity.
     *
     * @return date of the personal activity.
     */
    @Override
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the personal activity.
     *
     * @param date personal activity added date.
     */
    @Override
    public void setDate(String date) {
        this.date = date;
    }
}
