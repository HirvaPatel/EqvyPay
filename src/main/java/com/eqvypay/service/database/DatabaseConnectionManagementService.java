package com.eqvypay.service.database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eqvypay.util.constants.DatabaseConstants;
import com.eqvypay.util.constants.Environment;

@Service
public class DatabaseConnectionManagementService {

    @Autowired
    private org.springframework.core.env.Environment environment;
    
    private static Connection connection;
    private static Environment currentEnvironment = Environment.DEV;
    
    public Connection getConnection(Environment env) throws Exception {

        switch (env) {
            case TEST:
               if(connection==null || !(env.equals(currentEnvironment))) {
            		connection =  DriverManager.getConnection(environment.getProperty(DatabaseConstants.TEST_URL),
                            environment.getProperty(DatabaseConstants.TEST_USERNAME),
                            environment.getProperty(DatabaseConstants.TEST_PASSWORD));
            		currentEnvironment = Environment.TEST;
               }
               return connection;
            case DEV:
            	if(connection==null || !(env.equals(currentEnvironment))) {
            			connection = DriverManager.getConnection(environment.getProperty(DatabaseConstants.DEV_URL),
                        environment.getProperty(DatabaseConstants.DEV_USERNAME),
                        environment.getProperty(DatabaseConstants.DEV_PASSWORD));
            			currentEnvironment = Environment.DEV;
            	}
            	return connection;
            case PROD:
            	if(connection==null || (!env.equals(currentEnvironment))) {
            	   connection = DriverManager.getConnection(environment.getProperty(DatabaseConstants.PROD_URL),
                            environment.getProperty(DatabaseConstants.PROD_USERNAME),
                            environment.getProperty(DatabaseConstants.PROD_PASSWORD));
                	currentEnvironment = Environment.PROD;
            	}
            	return connection;
            default:
                throw new Exception("Unable to get a connection object for env :" + env.getEnvironment());
        }
    }

    public Environment parseEnvironment() {
        boolean test = Arrays.stream(environment.getActiveProfiles()).anyMatch(profile -> profile.equals("test"));
        boolean dev =  Arrays.stream(environment.getActiveProfiles()).anyMatch(profile -> profile.equals("dev"));
        if(test) {
            return Environment.TEST;
        }else if(dev) {
            return Environment.DEV;
        }
        else {
            return Environment.PROD;
        }
    }
}
