package com.anna.coupons.utils;

import java.util.regex.Pattern;

public class validationUtils {

	public static boolean isEmailAddressValid(String emailAddress) {
		String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\." + "[a-zA-Z0-9_+&-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (emailAddress == null) {
			return false;
		}

		return pat.matcher(emailAddress).matches();
	}

	public static boolean ispasswordValid(String password) {
		if (password.equals(password.toUpperCase()) && password.length() >= 8) {
			return true;
		}
		return false;

	}
}