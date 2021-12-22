package com.eqvypay.persistence;

/**
 * {@code Activity} implements the
 * {@code IActivity} and Activity class stores the
 *  information of all the transaction are
 *  made by user.
 * This class acts as a single object/entry/model.
 */
public class Activity implements IActivity {

	// Unique id of the activity.
	private String uuid;

	// Unique id of the user.
	private String userId;

	// Message describes the information of the transaction.
	private String message;

	/**
	 * Gets the activity's unique id.
	 *
	 * @return activity's unique id.
	 */
	@Override
	public String getUuid() {
		return uuid;
	}

	/**
	 * Sets the activity's unique id.
	 *
	 * @param uuid unique id of the activity.
	 */
	@Override
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

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
	 * @param userId unique id of the user.
	 */
	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the activity message.
	 *
	 * @return message of the activity.
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message of the activity.
	 *
	 * @param message activity's message.
	 */
	@Override
	public void setMessage(String message) {
		this.message = message;
	}
}
