package com.eqvypay.persistence;

public interface IGroup {

	/**
	 * Gets the group's unique id.
	 *
	 * @return group's unique id.
	 */
	String getGroupId();

	/**
	 * Sets the groups's unique id.
	 *
	 * @param groupId unique id of the group.
	 */
	void setGroupId(String groupId);

	/**
	 * Gets the group's name.
	 *
	 * @return group's name.
	 */
	String getGroupName();

	/**
	 * Sets the groups's name.
	 *
	 * @param groupName name of the group.
	 */
	void setGroupName(String groupName);

	/**
	 * Gets the group's description.
	 *
	 * @return group's description.
	 */
	String getGroupDesc();

	/**
	 * Sets the groups's description.
	 *
	 * @param groupDesc description of the group.
	 */
	void setGroupDesc(String groupDesc);
}
