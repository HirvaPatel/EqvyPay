package com.eqvypay.persistence;

public interface IActivity {
    /**
     * Gets the activity's unique id.
     *
     * @return activity's unique id.
     */
    String getUuid();

    /**
     * Sets the activity's unique id.
     *
     * @param uuid unique id of the activity.
     */
    void setUuid(String uuid);

    /**
     * Gets the user's unique id.
     *
     * @return user's unique id.
     */
    String getUserId();

    /**
     * Sets the user's unique id.
     *
     * @param userId unique id of the user.
     */
    void setUserId(String userId);

    /**
     * Gets the activity message.
     *
     * @return message of the activity.
     */
    String getMessage();

    /**
     * Sets the message of the activity.
     *
     * @param message activity's message.
     */
    void setMessage(String message);
}
