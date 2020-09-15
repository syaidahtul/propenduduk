package web.module.setup.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import web.module.setup.model.GenericEntityCodeForm;

public class StateValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return GenericEntityCodeForm.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "state.code", "error.required", new Object[] { "Code" });
		ValidationUtils.rejectIfEmpty(errors, "state.name", "error.required", new Object[] { "Name" });
	}

}
