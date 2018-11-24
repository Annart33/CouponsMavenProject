package com.anna.coupons.enums;

public enum ErrorType {

	INVALID_EMAIL(501),
	INVALID_PARMETER(502),
	SYSTEM_ERROR(503),
	INVALID_PASSWROD(504),
	ALREADY_EXISTS(505);

	private int errorNumber;

	private ErrorType(int number) {
		this.errorNumber = number;
	}

	public int getErrorNumber() {
		return errorNumber;
	}

	public static ErrorType fromString(final String s) {
		return ErrorType.valueOf(s);
	}

}
