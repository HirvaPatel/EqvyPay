package com.eqvypay.persistence;

public interface IPersonalActivity {

    /**
     * Gets the user's unique id.
     *
     * @return user's unique id.
     */
    String getUserId();

    /**
     * Sets the user's unique id.
     *
     * @param userId unique id of group.
     */
    void setUserId(String userId);

    /**
     * Gets the added amount.
     *
     * @return amount that user add.
     */
    Float getAmount();

    /**
     * Sets the amount that user add.
     *
     * @param amount amount that user add.
     */
    void setAmount(Float amount);

    /**
     * Gets the description of the personal activity.
     *
     * @return description of the personal activity.
     */
    String getDescription();

    /**
     * Sets the description of the personal activity.
     *
     * @param description personal activity's description.
     */
    void setDescription(String description);

    /**
     * Gets the category of the personal activity.
     *
     * @return category of the personal activity.
     */
    String getExpenseCategory();

    /**
     * Sets the category of the personal activity.
     *
     * @param expenseCategory category of the personal activity.
     */
    void setExpenseCategory(String expenseCategory);

    /**
     * Gets the date of the personal activity.
     *
     * @return date of the personal activity.
     */
    String getDate();

    /**
     * Sets the date of the personal activity.
     *
     * @param date personal activity added date.
     */
    void setDate(String date);
}
