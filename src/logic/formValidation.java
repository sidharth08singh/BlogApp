package logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import resources.emailValidationOutcomes;
import resources.passwordValidationOutcomes;

public class formValidation {
	
	/**
	 * 
	 * @param email
	 * @return 0. Email Validation Pass, 1. Email can't be null, 2. Invalid format 
	 */
	public static emailValidationOutcomes emailValidation(String email) {
		// check whether email field is null. 
		if(email.equals("")) {
			return emailValidationOutcomes.NULL;
		}
		
		// check email format using a Regex.
		String pattern = "\\w+@\\w+\\.\\w+";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(email);
		if(!m.matches()) {
			return emailValidationOutcomes.FORMAT_ERROR;
		}
		
		// else pass validation.
		return emailValidationOutcomes.PASS;
	}
	
	public static passwordValidationOutcomes passwordValidation(String password) {
		//check whether password is null.
		if(password.equals("")) {
			return passwordValidationOutcomes.NULL;
		}
		
		//check length - minimum length is 8 and maximum length can be 32.
		if(password.length() < 8 || password.length() > 32) {
			return passwordValidationOutcomes.LENGTH_ERROR;
		}
		
		/*check password format - check that password starts with an alphabet,
		  and contains at least one digit and one special character. */
		
		//ToDo. Add password security strength indicator. 
		if(!(((int)password.charAt(0) >= 65 && (int)password.charAt(0) <= 90) || ((int)password.charAt(0) >= 97 && (int)password.charAt(0) <= 122))) {
			return passwordValidationOutcomes.FORMAT_ERROR;
		}
		
		String pattern = ".*?\\d+.*?";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(password);
		if(!m.matches()) {
			return passwordValidationOutcomes.FORMAT_ERROR;
		}
		
		if(password.contains("\\s+")) {
			return passwordValidationOutcomes.SPACE_ERROR;
		}
		
		pattern = ".*?\\W+.*?";
		r = Pattern.compile(pattern);
		m = r.matcher(password);
		if(!m.matches()) {
			return passwordValidationOutcomes.FORMAT_ERROR;
		}
		
		return passwordValidationOutcomes.PASS;
	}
}
