package com.eqvypay.persistence;

import java.util.UUID;

public interface IUser {

    /**
     * Gets the user's unique id.
     *
     * @return user's unique id.
     */
    UUID getUuid();

    /**
     * Sets the user's unique id.
     *
     * @param uuid unique id of user.
     */
    void setUuid(UUID uuid);

    /**
     * Gets the user's name.
     *
     * @return user's name.
     */
    String getName();

    /**
     * Sets the user's name.
     *
     * @param name name of the user.
     */
    void setName(String name);

    /**
     * Gets the user's email.
     *
     * @return user's email.
     */
    String getEmail();

    /**
     * Sets the user's email.
     *
     * @param email email of the user.
     */
    void setEmail(String email);

    /**
     * Gets the user's contact.
     *
     * @return user's contact.
     */
    String getContact();

    /**
     * Sets the user's contact.
     *
     * @param contact contact of the user.
     */
    void setContact(String contact);

    /**
     * Gets the user's password.
     *
     * @return user's password.
     */
    String getPassword();

    /**
     * Sets the user's password.
     *
     * @param password password of the user.
     */
    void setPassword(String password);

    /**
     * Gets the user's answer to security question.
     *
     * @return user's answer to security question.
     */
    String getSecurityAnswer();

    /**
     * Sets the user's answer to security question.
     *
     * @param securityAnswer user's answer to security question.
     */
    void setSecurityAnswer(String securityAnswer);
}
