package com.eqvypay.web;

import com.eqvypay.persistence.IGroup;
import com.eqvypay.persistence.IUser;
import com.eqvypay.service.groups.GroupFactory;
import com.eqvypay.service.groups.GroupRepository;
import com.eqvypay.service.groups.IGroupDataManipulation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class ManageGroupOption {

	@Autowired
	private GroupFactory groupFactory;

    public void groupOptions(IUser user) throws Exception {

    	IGroupDataManipulation groupDataManipulation = groupFactory.getGroupDataManipulation();
    	GroupRepository groupRepository = groupFactory.getGroupRepository();

        Scanner sc = new Scanner(System.in);
        String option;

        while (true) {
            System.out.println("----------------------------");
            System.out.println("\tManage Group");
            System.out.println("----------------------------");
            System.out.println("[1] Create group");
            System.out.println("[2] Join group");
            System.out.println("[3] Leave group");
            System.out.println("[4] Delete group");
            System.out.println("[5] Exit");
            System.out.println("Select an option: ");

            option = sc.next();

            if(option.equals("5")) {
            	break;
            }
            switch (option){
                case "1":
                    IGroup group = groupFactory.getGroup();
                    System.out.println("Enter group name");
                    sc.nextLine();
                    String groupName = sc.nextLine();
                    group.setGroupName(groupName);
                    System.out.println("Enter group description");
                    group.setGroupDesc(sc.nextLine());
                    try {
                        groupRepository.createGroup(user,group);
                        groupRepository.joinGroup(user, group.getGroupId());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.toString());
                    }
                    break;

                case "2":
                    try {
                        List<String> groupIds = groupRepository.getFriendsGroupIds(user);
                        List<IGroup> all_groups = groupRepository.getAllGroups();

                        if (groupIds != null && groupIds.size() > 0) {
                            if (all_groups.size() != 0) {
                                System.out.println("List of groups that your friends are member of:");
                                for (IGroup each_group : all_groups) {
                                    if (groupIds.contains(each_group.getGroupId())) {
                                        System.out.println("Group ID: " + each_group.getGroupId() +
                                                "\tGroup Name: " + each_group.getGroupName());
                                    }
                                }

                                System.out.println("Enter group ID to join: ");
                                String groupId = sc.next().toUpperCase();
                                if (groupIds.contains(groupId)) {
                                    groupRepository.joinGroup(user, groupId);
                                } else {
                                    System.out.println("Enter group id from list given only. " +
                                            "Please try again.");
                                }
                            }
                        }
                        else {
                            System.out.println("Your friends are not part of any group.");
                        }
                    } catch (Exception e) {
                        System.out.println("Exception occurred: " + e.toString());
                    }
                    break;

                case "3":
                    try {
                        System.out.println("Enter group name that you want to leave: ");
                        List<String> joinedGroups = groupRepository.getJoinedGroups(user);

                        if (joinedGroups != null && joinedGroups.size() > 0) {
                            System.out.println(Arrays.toString(joinedGroups.toArray()));
                            System.out.println("Enter group name that you want to leave: ");
                            groupName = sc.next();
                            groupRepository.leaveGroup(user, groupName);
                        }
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    break;

                case "4":
                    System.out.println("Enter group name");
                    String group_name = sc.next();
                    try {
                        groupRepository.deleteGroup(group_name, user);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.toString());
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please try again");
                    break;
            }
        }
    }
}
