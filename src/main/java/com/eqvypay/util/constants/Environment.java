package com.eqvypay.util.constants;

public enum Environment {

	TEST("TEST"),
	DEV("DEV"),
	PROD("PROD");
	
	public String environment;
	
	Environment (String environment) {
		this.environment=environment;
	}

	public String getEnvironment() {
		return this.environment;
	}
	
}
