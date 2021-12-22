package com.eqvypay.service.profile;

import org.springframework.stereotype.Repository;

import com.eqvypay.persistence.IUser;

/**
 * {@code IProfileDataManipulation} provides a contract
 * for performing operation requesting user information.
 */
@Repository
public interface IProfileDataManipulation {
     /**
      * Perform the operation to create fetch user
      * profile information from the Users table.
      * This is the lazy method
      *
      * @throws Exception if any error occurs while fetching
      *                   records from the User table.
      */
     void getProfile(IUser user) throws Exception;
}
