package com.eqvypay.service.friends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FriendFactory {

    // static instance of FriendFactory class
    private static FriendFactory friendFactory = null;

    // reference of the friend repository.
    private FriendRepository friendRepository;

    // reference of the friend data manipulation.
    private IFriendDataManipulation friendDataManipulation;

    // set reference of the friend data manipulation
    @Autowired
    public void setFriendDataManipulation(IFriendDataManipulation friendDataManipulation) {
        this.friendDataManipulation = friendDataManipulation;
    }

    public FriendFactory() {

    }

    // returns the instance of the friend factory.
    public static FriendFactory getInstance() {
        if (friendFactory == null) {
            friendFactory = new FriendFactory();
        }
        return friendFactory;
    }

    // returns the reference of the friend repository.
    public FriendRepository getFriendRepository() {
        return friendRepository;
    }

    // set the reference of the friend repository.
    @Autowired
    public void setFriendRepository(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }
}
