package com.eqvypay.service;

import static org.junit.Assert.assertNotNull;

import com.eqvypay.service.authentication.AuthenticationService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthenticationServiceTest {

	@Test
	@Order(1)
	public void shouldCheckIfAuthenticationServiceExists() {
		assertNotNull(new AuthenticationService());
	}
}
