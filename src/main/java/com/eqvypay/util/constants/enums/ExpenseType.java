package com.eqvypay.util.constants.enums;

public enum ExpenseType {

	GROUP("GROUP"),
	INDIVIDUAL("FRIEND"),
	FRIEND("FRIEND");
	
	private String type;
	
	private ExpenseType(String type) {
		this.type=type;
	}

	public String getType() {
		return type;
	}

}
