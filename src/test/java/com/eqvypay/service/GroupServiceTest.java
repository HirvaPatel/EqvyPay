package com.eqvypay.service;

import com.eqvypay.persistence.Group;
import com.eqvypay.persistence.IGroup;
import com.eqvypay.persistence.IUser;
import com.eqvypay.persistence.User;
import com.eqvypay.service.authentication.AuthenticationService;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.service.groups.GroupDataManipulation;
import com.eqvypay.service.groups.GroupRepository;
import com.eqvypay.service.user.UserDataManipulation;
import com.eqvypay.service.user.UserRepository;
import com.eqvypay.util.constants.Environment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.util.List;

@SpringBootTest
public class GroupServiceTest {

    @Autowired
    private DatabaseConnectionManagementService dcms;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupDataManipulation groupDataManipulation;

    @Autowired
    UserDataManipulation userDataManipulation;

    @Autowired
    GroupRepository groupRepository;

    private static Connection connection;

    @Test
    @Order(1)
    public void testCreateGroup() throws Exception {
        IGroup testGroup = new Group();

        IUser user = new User();
        user.setName("ADD_FRIEND_TEST_USER");
        user.setEmail("testUser1@gmail.com");
        user.setContact("1234567891");
        user.setPassword(AuthenticationService.getHashedPassword("Test@123"));
        user.setSecurityAnswer("test");
        userDataManipulation.save(user);

        testGroup.setGroupId("TEST_CREATE_GROUP_ID");
        testGroup.setGroupName("TEST_CREATE_GROUP_NAME");
        testGroup.setGroupDesc("TEST_CREATE_GROUP_DESC");

        groupRepository.createGroup(user, testGroup);
        List<IGroup> allGroups = groupRepository.getAllGroups();

        boolean shouldBeTrue = false;
        for (IGroup eachGroup : allGroups) {
            System.out.println(eachGroup.getGroupId());
            if (eachGroup.getGroupId().equals(testGroup.getGroupId())) {
                shouldBeTrue = true;
                break;
            }
        }
        Assertions.assertTrue(shouldBeTrue);
    }

    @Test
    @Order(2)
    public void testJoinGroup() throws Exception {

        IUser testUser = new User();
        IGroup testGroup = new Group();
//        testGroup.setGroupId("TEST_JOIN_GROUP_ID");
        testGroup.setGroupName("TEST_JOIN_GROUP_NAME");
        testGroup.setGroupDesc("TEST_JOIN_GROUP_DESC");

        groupRepository.createGroup(testUser, testGroup);
        groupRepository.joinGroup(testUser, testGroup.getGroupId());

        List<String> allMembersId = groupRepository.getMembersOfGroup(testGroup.getGroupId());

        boolean shouldBeTrue = false;

        for(String uuid: allMembersId){
            System.out.println(uuid);
            if(uuid.equals(testUser.getUuid().toString())){
                shouldBeTrue = true;
                break;
            }
        }
        Assertions.assertTrue(shouldBeTrue);
    }

    @Test
    @Order(3)
    public void testLeaveGroup() {
        try {
            IUser testUser = new User();
            IGroup testGroup = new Group();

            testGroup.setGroupId("TEST_LEAVE_GROUP_ID");
            testGroup.setGroupName("TEST_LEAVE_GROUP_NAME");
            testGroup.setGroupDesc("TEST_LEAVE_GROUP_DESC");

            groupRepository.createGroup(testUser, testGroup);
            groupRepository.joinGroup(testUser, testGroup.getGroupId());
            groupRepository.leaveGroup(testUser, testGroup.getGroupName());

            boolean shouldBeFalse = groupRepository.getMembersOfGroup(testGroup.getGroupId()).contains(testUser.getUuid());

            Assertions.assertFalse(shouldBeFalse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    public void testDeleteGroup() throws Exception {
        IUser testUser = new User();
        IGroup testGroup = new Group();

        testGroup.setGroupId("TEST_DELETE_GROUP_ID1");
        testGroup.setGroupName("TEST_DELETE_GROUP_NAME1");
        testGroup.setGroupDesc("TEST_DELETE_GROUP_DESC1");

        groupRepository.createGroup(testUser, testGroup);
        groupRepository.joinGroup(testUser, testGroup.getGroupId());

        groupRepository.deleteGroup(testGroup.getGroupName(), testUser);

        boolean shouldRemainTrue = true;

        for(IGroup eachGroup: groupRepository.getAllGroups()) {
            if(eachGroup.getGroupId() != null && eachGroup.getGroupId().equals(testGroup.getGroupId())) {
                shouldRemainTrue = false;
            }
        }

        Assertions.assertTrue(shouldRemainTrue);
    }
}
