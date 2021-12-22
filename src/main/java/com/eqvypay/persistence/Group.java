package com.eqvypay.persistence;

import java.util.Random;

/**
 * {@code Group} implements the
 * {@code IGroup} and Group class stores the
 * information related to the group.
 * This class acts as a single object/entry/model.
 */
public class Group implements IGroup {

    // Unique id of the group
    private String groupId;

    // Unique id of the group
    private String groupName;

    // Description of the group
    private String groupDesc;

    public Group() {
        // generate random group id
        Random random = new Random();
        this.groupId = String.valueOf(random.nextInt(10)) +
                String.valueOf(random.nextInt(10)) +
                (char) (random.nextInt(26) + 'a') +
                (char) (random.nextInt(26) + 'a') +
                String.valueOf(random.nextInt(10) +
                        String.valueOf(random.nextInt(10)));
        this.groupId = this.groupId.toUpperCase();
    }

    /**
     * Gets the group's unique id.
     *
     * @return group's unique id.
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Sets the groups's unique id.
     *
     * @param groupId unique id of the group.
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets the group's name.
     *
     * @return group's name.
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the groups's name.
     *
     * @param groupName name of the group.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Gets the group's description.
     *
     * @return group's description.
     */
    public String getGroupDesc() {
        return groupDesc;
    }

    /**
     * Sets the groups's description.
     *
     * @param groupDesc description of the group.
     */
    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }
}
