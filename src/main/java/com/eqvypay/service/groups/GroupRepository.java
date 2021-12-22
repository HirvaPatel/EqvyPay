package com.eqvypay.service.groups;

import com.eqvypay.persistence.IGroup;
import com.eqvypay.persistence.IUser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code GroupRepository} provides a contract for
 * creating, joining, leaving, and deleting the group,
 * also, get all the groups and members of the group.
 */
@Repository
public interface GroupRepository {

    /**
     * @param user object of the user.
     * @param group_name name of group.
     * @throws Exception if any error occurs while performing
     *                   operation to delete group of the user.
     */
    void deleteGroup(String group_name, IUser user) throws Exception;

    /**
     * @param user object of the user.
     * @param inputId id of the group.
     * @throws Exception if any error occurs while performing
     *                   operation to join group of this user.
     */
    void joinGroup(IUser user, String inputId) throws Exception;

    /**
     * @param user object of the user.
     * @param group object of the group.
     * @throws Exception if any error occurs while performing
     *                   operation to create group of this user.
     */
    void createGroup(IUser user, IGroup group) throws Exception;

    /**
     * @return List of group name in which user joined.
     * @throws Exception if any error occurs while performing
     *                   operation to get all joined groups.
     */
    List<String> getJoinedGroups(IUser user) throws Exception;

    /**
     * @param user object of the user.
     * @param groupName name of the group.
     * @throws Exception if any error occurs while performing
     *                   operation to leave group of this user.
     */
    void leaveGroup(IUser user, String groupName) throws Exception;

    /**
     * @return list of groups.
     * @throws Exception if any error occurs while performing
     *                   operation to get all the groups from
     *                   the database.
     */
    List<IGroup> getAllGroups() throws Exception;

    /**
     * @return list of all joined friends' group id.
     * @throws Exception if any error occurs while performing
     *                   operation to get group ids of the friends'
     *                   joined groups.
     */
    List<String> getFriendsGroupIds(IUser user) throws Exception;

    /**
     * @return list of all joined groups object.
     * @throws Exception if any error occurs while performing
     *                   operation to get all joined groups.
     */
    ArrayList<IGroup> getAllJoinedGroups(IUser user) throws Exception;

    /**
     * @return list of name of the user who are the member
     *         of the group.
     * @param groupId id of the group.
     * @throws Exception if any error occurs while performing
     *                   operation to get the member of the
     *                   specific group.
     */
    List<String> getMembersOfGroup(String groupId) throws Exception;

}
