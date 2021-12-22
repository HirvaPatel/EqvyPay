package com.eqvypay.persistence;

import java.util.UUID;

/**
 * {@code User} implements the
 * {@code IUser} and User class stores the
 * information related to the User.
 * This class acts as a single object/entry/model.
 */
public class User implements IUser {

    // Unique id of the user
    private UUID uuid;

    // Name of the user
    private String name;

    // Email of the user
    private String email;

    // Contact number of the user
    private String contact;

    // Password of the user
    private String password;

    // Answer to the security question of the user
    private String securityAnswer;

    /**
     * Sets the random id to the user.
     */
    public User() {
        this.uuid = UUID.randomUUID();
    }

    /**
     * Gets the user's unique id.
     *
     * @return user's unique id.
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Sets the user's unique id.
     *
     * @param uuid unique id of user.
     */
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Gets the user's name.
     *
     * @return user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's email.
     *
     * @return user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's contact.
     *
     * @return user's contact.
     */
    public String getContact() {
        return contact;
    }

    /**
     * Sets the user's contact.
     *
     * @param contact contact of the user.
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Gets the user's password.
     *
     * @return user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's answer to security question.
     *
     * @return user's answer to security question.
     */
    public String getSecurityAnswer() {
        return securityAnswer;
    }

    /**
     * Sets the user's answer to security question.
     *
     * @param securityAnswer user's answer to security question.
     */
    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

}
