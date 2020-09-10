package web.core.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import app.core.usermgmt.service.UserRegistrationService;

public class UserRegistrationFormValidator implements Validator {
	
	private UserRegistrationService service;

	public UserRegistrationFormValidator(UserRegistrationService service) {
		this.service = service;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserRegistrationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		UserRegistrationForm form = UserRegistrationForm.class.cast(target);
		
		ValidationUtils.rejectIfEmpty (errors, "person.fullName", "error.required", new Object[]{"Full Name"});
		
		ValidationUtils.rejectIfEmpty (errors, "user.password", "error.required", new Object[]{"Password"});
		ValidationUtils.rejectIfEmpty (errors, "user.confirmPassword", "error.required", new Object[]{"Confirm Password"});
		
		
		// 1. check duplicate ic
		
		
		// 2. check duplicate email
		
		// 3. check confirm password
	
	}


}
