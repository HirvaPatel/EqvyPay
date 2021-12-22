package com.eqvypay.web;

import com.eqvypay.persistence.IExpense;
import com.eqvypay.persistence.IGroup;
import com.eqvypay.persistence.IUser;
import com.eqvypay.service.activity.ActivityHelper;
import com.eqvypay.service.expense.ExpenseFactory;
import com.eqvypay.service.groups.GroupFactory;
import com.eqvypay.service.expense.ExpenseRepository;
import com.eqvypay.service.expense.IExpenseDataManipulation;
import com.eqvypay.service.groups.GroupRepository;
import com.eqvypay.service.groups.IGroupDataManipulation;
import com.eqvypay.service.user.UserFactory;
import com.eqvypay.service.user.UserRepository;
import com.eqvypay.util.constants.Constants;
import com.eqvypay.util.constants.enums.ExpenseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ManageExpenseOption {

    @Autowired
    private ExpenseFactory expenseFactory;

    @Autowired
    private GroupFactory groupFactory;

    @Autowired
    private UserFactory userFactory;

    public void options(IUser user) throws Exception {

        IExpenseDataManipulation dataManipulation =
                expenseFactory.getExpenseDataManipulation();
        ExpenseRepository expenseRepository =
                expenseFactory.getExpenseRepository();
        IGroupDataManipulation groupDataManipulation =
                groupFactory.getGroupDataManipulation();
        GroupRepository groupRepository =
                groupFactory.getGroupRepository();
        UserRepository userRepository =
                userFactory.getUserRepository();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("----------------------------");
            System.out.println("\tManage Expenses");
            System.out.println("----------------------------\n");
            System.out.println("[1] Add expense");
            System.out.println("[2] Settle expense");
            System.out.println("[3] Exit");

            String expenseOptions = sc.nextLine();

            if (expenseOptions.equals("3")) {
                break;
            }

            if (expenseOptions.equals("1")) {
                System.out.println("[1] Add in the group");
                System.out.println("[2] Add to the friend");
                System.out.println("[3] Exit");

                String payOption = sc.nextLine();

                if (payOption.equals("3")) {
                    break;
                }

                if (payOption.equals("1")) {
                    System.out.println("[1] Add in the existing group");
                    System.out.println("[2] Create a new group");
                    System.out.print("Select an option: ");

                    String groupOption = sc.nextLine();

                    if (groupOption.equals("1")) {
                        System.out.println("List of available groups");
                        ArrayList<IGroup> groups = groupRepository.getAllJoinedGroups(user);

                        if (groupOption != null && groups.size() > 0) {
                            for (int i = 0; i < groups.size(); i++) {
                                System.out.println((i + 1) + ". " + groups.get(i).getGroupName());
                            }

                            System.out.println("Enter a group name: ");
                            String groupNameInput = sc.nextLine();
                            boolean validGroup = false;
                            for (IGroup group : groups) {
                                if (group.getGroupName().equals(groupNameInput)) {
                                    validGroup = true;
                                    System.out.println("Enter expense description: ");
                                    String expenseDesc = sc.nextLine();
                                    System.out.println("Enter amount");
                                    String expenseString = sc.nextLine();
                                    float expenseAmt = Float.valueOf(expenseString);
                                    System.out.println("Enter currency type");
                                    String currencyType = sc.nextLine();

                                    IExpense newExpense = ExpenseFactory.getInstance().getExpense();
                                    newExpense.setGroupId(group.getGroupId());
                                    newExpense.setExpenseDesc(expenseDesc);
                                    newExpense.setExpenseAmt(expenseAmt);
                                    newExpense.setCurrencyType(currencyType);
                                    newExpense.setExpenseType(ExpenseType.GROUP);
                                    newExpense.setTargetUserId(user.getUuid().toString());

                                    IExpense expense = dataManipulation.save(newExpense);

                                    System.out.println("Expense target user is " +
                                            expense.getTargetUserId());
                                    System.out.println("Expense " + expense.getExpenseAmt());
                                    System.out.println("[1] Split equally");
                                    System.out.println("[2] Split unequally");

                                    String divideTypeString = sc.nextLine();
                                    int expenseDivideType = Integer.valueOf(divideTypeString);

                                    if (expenseDivideType == 1) {
                                        List<IExpense> expenses = new ArrayList<IExpense>();

                                        List<String> members = groupRepository
                                                .getMembersOfGroup(newExpense.getGroupId());
                                        if (members.size() <= 1) {
                                            System.out.println("There are no members in the group, " +
                                                    "add them first");
                                            break;
                                        }
                                        float share = expense.getExpenseAmt() / (members.size());

                                        System.out.println("PerShare " + share);

                                        for (String member : members) {
                                            if (!member.equalsIgnoreCase(expense.getTargetUserId())) {

                                                IUser groupMember = userRepository
                                                        .getByUuid(UUID.fromString(member));
                                                IExpense memberExpense = ExpenseFactory
                                                        .getInstance().getExpense();

                                                memberExpense.setId(UUID.randomUUID().toString());
                                                memberExpense.setCurrencyType(expense.getCurrencyType());
                                                memberExpense.setExpenseAmt(share);
                                                memberExpense.setExpenseDesc(expense.getExpenseDesc());
                                                memberExpense.setExpenseType(expense.getExpenseType());
                                                memberExpense.setGroupId(expense.getGroupId());
                                                memberExpense.setSourceUserId(member);
                                                memberExpense.setTargetUserId(user.getUuid().toString());
                                                expenses.add(memberExpense);

                                                ActivityHelper.addActivity
                                                        (user.getUuid().toString(),
                                                                String.format(Constants.settleTarget,
                                                                        groupMember.getName(),
                                                                        String.valueOf(expense.getExpenseAmt())
                                                                                .concat(expense.getCurrencyType())));

                                                ActivityHelper.addActivity(
                                                        member, String.format(
                                                                Constants.settleSource, (user.getName()),
                                                                String.valueOf(expense.getExpenseAmt())
                                                                        .concat(expense.getCurrencyType())));
                                            }
                                        }
                                        dataManipulation.saveAll(expenses);

                                    } else if (expenseDivideType == 2) {
                                        List<IExpense> expenses = new ArrayList<IExpense>();
                                        List<String> groupMembers = groupRepository
                                                .getMembersOfGroup(newExpense.getGroupId());

                                        for (String member : groupMembers) {
                                            if (!member.equalsIgnoreCase(expense.getTargetUserId())) {

                                                IUser groupMember = userRepository
                                                        .getByUuid(UUID.fromString(member));
                                                IExpense memberExpense = ExpenseFactory
                                                        .getInstance().getExpense();

                                                memberExpense.setId(UUID.randomUUID().toString());
                                                memberExpense.setCurrencyType(expense.getCurrencyType());

                                                System.out.println("Please add share of user: " + member);
                                                String shareString = sc.nextLine();
                                                float share = Float.valueOf(shareString);

                                                memberExpense.setExpenseAmt(share);
                                                memberExpense.setExpenseDesc(expense.getExpenseDesc());
                                                memberExpense.setExpenseType(expense.getExpenseType());
                                                memberExpense.setGroupId(expense.getGroupId());
                                                memberExpense.setSourceUserId(member);
                                                memberExpense.setTargetUserId(user.getUuid().toString());

                                                ActivityHelper.addActivity(
                                                        user.getUuid().toString(),
                                                        String.format(Constants.settleTarget,
                                                                groupMember.getName(),
                                                                String.valueOf(expense.getExpenseAmt())
                                                                        .concat(expense.getCurrencyType())));

                                                ActivityHelper.addActivity(
                                                        member, String.format(
                                                                Constants.settleSource,
                                                                user.getName(),
                                                                String.valueOf(expense.getExpenseAmt())
                                                                        .concat(expense.getCurrencyType())));
                                                expenses.add(memberExpense);
                                            }
                                        }
                                        dataManipulation.saveAll(expenses);
                                    }
                                    System.out.println("Succeed! Expenses added");
                                }
                            }
                            if (!validGroup) {
                                System.out.println("Failed! Invalid entry! " +
                                        "please enter valid group name");
                            }
                        } else {
                            System.out.println("You're not join in any group");
                        }
                    } else if (groupOption.equals("2")) {
                        IGroup group = GroupFactory.getInstance().getGroup();

                        System.out.println("Enter group name");
                        String groupName = sc.nextLine();
                        group.setGroupName(groupName);
                        System.out.println("Enter group description");
                        group.setGroupDesc(sc.nextLine());

                        try {
                            groupRepository.createGroup(user, group);
                            groupRepository.joinGroup(user, group.getGroupId());
                            ActivityHelper.addActivity
                                    (user.getUuid().toString(),
                                            String.format(Constants.joinGroup, groupName));

                        } catch (Exception e) {
                            System.out.println("Error: " + e.toString());
                        }
                        continue;
                    }

                } else if (payOption.equals("2")) {
                    List<IExpense> friendExpenseList = new ArrayList<>();
                    List<IUser> friends = userRepository
                            .findAllFriends(user.getUuid().toString());
                    int count = 1;

                    if (friends.size() > 0) {
                        System.out.println("Enter expense description: ");
                        String expenseDesc = sc.nextLine();
                        System.out.println("Enter amount");
                        String expenseAmtString = sc.nextLine();

                        float expenseAmt = Float.valueOf(expenseAmtString);

                        System.out.println("Enter currency type");
                        String currencyType = sc.nextLine();
                        System.out.println("Friend option selected");

                        System.out.printf("%s%n", "List of friends");
                        System.out.format("%-15s%-15s%-15s%n", "sr.no", "name", "email");

                        for (IUser friend : friends) {
                            System.out.format("%-15s%-15s%-15s%n",
                                    count, friend.getName(), friend.getEmail());
                            count++;
                        }
                        System.out.println("Enter friend index numbers with spaces \n " +
                                "e.g. 1 2 (for friend 1 and 2)");

                        String requestedFriendsString = sc.nextLine();
                        String[] requestedFriends = requestedFriendsString.split(" ");

                        List<Integer> selectedFriends =
                                Arrays.stream(requestedFriends)
                                        .map(p -> Integer.parseInt(p) - 1)
                                        .collect(Collectors.toList());

                        List<IUser> selectedFriendIds =
                                selectedFriends.stream()
                                        .map(p -> friends.get(p))
                                        .collect(Collectors.toList());

                        System.out.println("1. Split Equally");
                        System.out.println("2. Split Unequally");

                        String moneyDivideOption = sc.nextLine();
                        int selectedOption = Integer.parseInt(moneyDivideOption);

                        if (selectedOption == 1) {
                            float share = expenseAmt / (selectedFriendIds.size() + 1);

                            for (IUser friend : selectedFriendIds) {
                                IExpense expense = ExpenseFactory.getInstance().getExpense();
                                expense.setCurrencyType(currencyType);
                                expense.setExpenseAmt(share);
                                expense.setExpenseDesc(expenseDesc);
                                expense.setExpenseType(ExpenseType.INDIVIDUAL);
                                expense.setId(UUID.randomUUID().toString());
                                expense.setSourceUserId(friend.getUuid().toString());
                                expense.setTargetUserId(user.getUuid().toString());
                                friendExpenseList.add(expense);
                            }
                            dataManipulation.saveAll(friendExpenseList);

                        } else if (selectedOption == 2) {
                            for (IUser friend : selectedFriendIds) {
                                IExpense expense = ExpenseFactory.getInstance().getExpense();
                                expense.setCurrencyType(currencyType);
                                System.out.println("Enter " + currencyType +
                                        " amount share for friend :" + friend.getEmail());

                                String shareString = sc.nextLine();
                                float share = Float.valueOf(shareString);

                                expense.setExpenseAmt(share);
                                expense.setExpenseDesc(expenseDesc);
                                expense.setExpenseType(ExpenseType.INDIVIDUAL);
                                expense.setId(UUID.randomUUID().toString());
                                expense.setSourceUserId(friend.getUuid().toString());
                                expense.setTargetUserId(user.getUuid().toString());
                                friendExpenseList.add(expense);

                            }
                            dataManipulation.saveAll(friendExpenseList);
                            System.out.println("Succeed! Expenses added");
                        }
                    } else {
                        System.out.println("Your friend list is empty! " +
                                "Please add a friend first.");
                    }
                }
            } else if (expenseOptions.equals("2")) {
                System.out.println("Your outstanding are:");
                List<IExpense> expenses =
                        expenseRepository.getExpensesByUserId(user.getUuid().toString());

                if (expenses != null && expenses.size() > 0) {
                    for (IExpense expense : expenses) {
                        Currency currency =
                                Currency.getInstance(expense.getCurrencyType().toUpperCase());

                        boolean hasSourceId = expense.getSourceUserId()
                                .equals(user.getUuid().toString());

                        if (hasSourceId) {
                            IUser targetUser = userRepository
                                    .getByUuid(UUID.fromString(expense.getTargetUserId()));
                            System.out.format(Constants.settleSource,
                                    targetUser.getName().concat("( " + targetUser.getEmail() + " )"),
                                    String.valueOf(expense.getExpenseAmt()).concat(currency.getSymbol()));
                        } else {
                            IUser sourceUser = userRepository
                                    .getByUuid(UUID.fromString(expense.getSourceUserId()));
                            System.out.format(Constants.settleTarget,
                                    sourceUser.getName().concat("( " + sourceUser.getEmail() + " )"),
                                    String.valueOf(expense.getExpenseAmt()).concat(currency.getSymbol()));
                        }
                        System.out.println();
                    }

                    System.out.println("\nEnter expense to settle, " +
                            "for multiple settlements, enter by spaces \n " +
                            "e.g. 1 2 (to select first and second expenses)");

                    String settlementString = sc.nextLine();
                    String[] settlements = settlementString.trim().split(" ");
                    List<Integer> settlementIndexes =
                            Arrays.stream(settlements)
                                    .map(p -> Integer.parseInt(p) - 1)
                                    .collect(Collectors.toList());

                    for (int i = 0; i < settlementIndexes.size(); i++) {
                        IExpense expenseToBeSettled = expenses.get(settlementIndexes.get(i));
                        IUser targetUser = userRepository
                                .getByUuid(UUID.fromString(expenseToBeSettled.getSourceUserId()));
                        Currency currency = Currency
                                .getInstance(expenseToBeSettled.getCurrencyType().toUpperCase());
                        boolean settled = expenseRepository.settleExpense(expenseToBeSettled);

                        if (settled) {
                            System.out.println("Expense between :" + user.getName() + " and "
                                    + targetUser.getName() + " of "
                                    + expenseToBeSettled.getExpenseAmt() + " amount is settled!");
                            ActivityHelper.addActivity(
                                    user.getUuid().toString(),
                                    String.format(Constants.expenseSettlement, user.getName(),
                                            targetUser.getName(), expenseToBeSettled.getExpenseAmt()));
                        }
                    }
                } else {
                    System.out.println("You don't have any expense to settle");
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
