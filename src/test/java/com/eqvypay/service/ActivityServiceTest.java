package com.eqvypay.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eqvypay.persistence.IActivity;
import com.eqvypay.service.activity.ActivityDataManipulation;
import com.eqvypay.service.activity.ActivityFactory;
import com.eqvypay.service.activity.ActivityService;

@SpringBootTest
public class ActivityServiceTest {

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ActivityDataManipulation activityDataManipulation;
	
	
	@Test
	@Order(1)
	void shouldAddActivity() throws Exception {
		IActivity activity = ActivityFactory.getInstance().getActivity();
		String userId = "c80e82c4-da32-4aaf-bd7f-97b78fe5910f";
		String uuid = "85df2df1-29a9-42d0-a12a-696ce6b62d11";
		activity.setMessage("test-message");
		activity.setUserId(userId);
		activity.setUuid(uuid);
		assertTrue(activityDataManipulation.addActivity(activity));
	}
	
	@Test
	@Order(2)
	void shouldGetActivity() throws Exception {
		String userId = "c80e82c4-da32-4aaf-bd7f-97b78fe5910f";
		List<IActivity> activites = activityService.getUserActivity(userId);
		assertThat(activites.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void shouldDeleteActivity() throws Exception {	
		String uuid = "85df2df1-29a9-42d0-a12a-696ce6b62d11";
		assertTrue(activityDataManipulation.deleteActivity(uuid));
	}
	
}

