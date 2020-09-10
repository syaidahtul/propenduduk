package web.core.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import app.core.usermgmt.service.UserRegistrationService;
import web.core.model.UserRegistrationForm;
import web.core.model.UserRegistrationFormValidator;

@Controller
@SessionAttributes({ "userRegistrationForm" })
public class UserRegistrationController extends BaseController {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationController.class);

	@Autowired
	private UserRegistrationService registrationService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Locale locale, HttpServletRequest req, Model model) {
		UserRegistrationForm userRegistrationForm = new UserRegistrationForm();
		userRegistrationForm.setIdentityTypes(registrationService.findAllIdentityType());
		model.addAttribute("userRegistrationForm", userRegistrationForm);
		return "user.registration";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Locale locale, HttpServletRequest req, HttpServletResponse resp, Model model,
			@ModelAttribute("userRegistrationForm") UserRegistrationForm userRegistrationForm, BindingResult result) {
		LOGGER.info("Register confirm");
		
		UserRegistrationFormValidator validator = new UserRegistrationFormValidator(registrationService);
		validator.validate(userRegistrationForm, result);

		if (result.hasErrors()) {
			return "user.registration";
		} else {
			registrationService.registerPerson(userRegistrationForm.getPerson(), userRegistrationForm.getUser());
		}
		
		return "redirect:/";
	}

}
