package com.eqvypay.web;

import java.util.Scanner;

import com.eqvypay.persistence.IUser;
import com.eqvypay.persistence.User;
import com.eqvypay.service.friends.FriendFactory;
import com.eqvypay.service.friends.FriendRepository;
import com.eqvypay.util.validator.AuthenticationValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveFriendOption {
	
	@Autowired
	private FriendFactory friendFactory;
	
	public void friendOptions(IUser user) throws Exception {
        FriendRepository friendRepository = friendFactory.getFriendRepository();
		Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("----------------------------");
            System.out.println("[1] Remove friend by email");
			System.out.println("[2] Remove friend by phone number");
			System.out.println("[3] Exit");
			System.out.print("Select an option: ");

			String option = sc.next();

			if(option.equals("3")) {
				break;
			}
			
			switch(option) {
			case "1":
				System.out.println("Enter your friend's email id");
				String friendEmail = AuthenticationValidator.getAndValidateEmail(sc);
				friendRepository.removeFriendByEmail(user, friendEmail);
				break;
			case "2":
				System.out.println("Enter your friend's contact number");
				String friendContact = AuthenticationValidator.getAndValidateContact(sc);
				friendRepository.removeFriendByContact(user, friendContact);
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
        }
	}
}
