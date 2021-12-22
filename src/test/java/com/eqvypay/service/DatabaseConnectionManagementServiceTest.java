package com.eqvypay.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import com.eqvypay.service.database.DatabaseConnectionManagementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eqvypay.util.constants.Environment;

@SpringBootTest
public class DatabaseConnectionManagementServiceTest {
	
	@Autowired
	private DatabaseConnectionManagementService dcms;

	@Test
	@Order(1)
	public void shouldInstantiateDatabaseConnection() {
		assertNotNull(new DatabaseConnectionManagementService());
	}
	
	@Test
	@Order(2)
	public void shouldCheckConnectionForTestDb() throws Exception {
		Connection connection = dcms.getConnection(Environment.TEST);
		Assertions.assertFalse(connection.isClosed());
		connection.close();
	}
	
	@Test
	@Order(3)
	public void shouldCheckConnectionForDevDb() throws Exception {
		Connection connection = dcms.getConnection(Environment.DEV);
		Assertions.assertFalse(connection.isClosed());
		connection.close();
	}

	@Test
	@Order(4)
	public void shouldCheckConnectionForProdDb() throws Exception {
		Connection connection = dcms.getConnection(Environment.PROD);
		Assertions.assertFalse(connection.isClosed());
		connection.close();
	}
	
}