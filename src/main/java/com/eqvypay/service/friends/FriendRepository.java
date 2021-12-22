package com.eqvypay.service.friends;

import org.springframework.stereotype.Repository;

import com.eqvypay.persistence.IUser;

/**
 * {@code FriendRepository} provides a contract
 * for add and remove friend of the user.
 */
@Repository
public interface FriendRepository {

    /**
     * @param user object of this user.
     * @param email registered email address of the friend.
     * @throws Exception if any error occurs while performing
     *                   operation to make friend of this user.
     */
    void addFriendByEmail(IUser user, String email) throws Exception;

    /**
     * @param user object of this user.
     * @param contact registered contact number of the friend.
     * @throws Exception if any error occurs while performing
     *                   operation to make friend of this user.
     */
    void addFriendByContact(IUser user, String contact) throws Exception;

    /**
     * @param user object of this user.
     * @param email registered email of the friend.
     * @throws Exception if any error occurs while performing
     *                   operation to remove friend of this user.
     */
    void removeFriendByEmail(IUser user, String email) throws Exception;

    /**
     * @param user object of this user.
     * @param contact registered contact of the friend.
     * @throws Exception if any error occurs while performing
     *                   operation to remove friend of this user.
     */
    void removeFriendByContact(IUser user, String contact) throws Exception;
}
