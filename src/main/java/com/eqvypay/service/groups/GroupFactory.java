package com.eqvypay.service.groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eqvypay.persistence.Group;
import com.eqvypay.persistence.IGroup;

@Service
public class GroupFactory {

	// static instance of GroupFactory class.
	private static GroupFactory groupFactory;

	// reference of the Group data manipulation.
	@Autowired
	private IGroupDataManipulation groupDataManipulation;

	// reference of the group repository.
	@Autowired
	private GroupRepository groupRepository;

	// reference of the IGroup.
	private IGroup group;

    // default constructor of this class
	public GroupFactory() {
		if(group == null) {
			group = new Group();
		}
	}

	// returns the instance of the GroupFactory class.
	public static GroupFactory getInstance() {
		if(groupFactory == null) {
			groupFactory = new GroupFactory();
		}
		return groupFactory;
	}

	// returns the reference of the group data manipulation class.
	public IGroupDataManipulation getGroupDataManipulation() {
		return groupDataManipulation;
	}

	// returns the reference of the group repository.
	public IGroup getGroup() {
		group = new Group();
		return group;
	}

	// returns the reference of the GroupRepository.
	public GroupRepository getGroupRepository() {
		return groupRepository;
	}

}
