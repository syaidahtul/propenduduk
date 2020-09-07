package web.module.setup.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class InsurerEditFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// return InsurerForm.class.equals(arg0);
		return true;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "insurer.name", "setup.insurer.required.name");
		ValidationUtils.rejectIfEmpty(errors, "insurer.contact.email", "error.required", new Object[] { "Email" });
//
//		InsurerForm insurerForm = InsurerForm.class.cast(obj);
//		if (insurerForm.getInsurer() != null && insurerForm.getInsurer().getContact() != null
//				&& StringUtils.isNotEmpty(insurerForm.getInsurer().getContact().getEmail())) {
//			Matcher matcher = Formatter.VALID_EMAIL_ADDRESS_REGEX
//					.matcher(insurerForm.getInsurer().getContact().getEmail());
//			if (!matcher.find()) {
//				errors.reject("setup.insurer.invalidEmailFormat");
//			}
//		}
	}
}
