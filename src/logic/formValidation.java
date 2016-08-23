package logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import resources.emailValidationOutcomes;

public class formValidation {
	
	/**
	 * 
	 * @param email
	 * @return 0. Email Validation Pass, 1. Email can't be null, 2. Invalid format 
	 */
	public static emailValidationOutcomes emailValidation(String email) {
		// check whether email field is null
		if(email.equals("")) {
			return emailValidationOutcomes.NULL;
		}
		
		// check email format using a regex
		String pattern = "\\w+@\\w+\\.\\w+";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(email);
		if(!m.matches()) {
			return emailValidationOutcomes.FORMAT_ERROR;
		}
		
		// else pass validation
		return emailValidationOutcomes.PASS;
	}
	
	public static int passwordValidation(String password) {
		return 1;
	}
}
