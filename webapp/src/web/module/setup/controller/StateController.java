package web.module.setup.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import module.setup.service.StateService;
import web.core.controller.BaseController;
import web.core.helper.WebConstant;
import web.module.setup.model.GenericEntityCodeForm;

@Controller
@RequestMapping("/secured/setup")
@SessionAttributes({ "stateForm" })
public class StateController extends BaseController {
	
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(StateController.class);
	
	@Autowired
	private StateService stateService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}

	@RequestMapping(value = "/state", method = RequestMethod.GET)
	public String getStatePage(Locale locale, HttpServletRequest req, Model model) {
		model.addAttribute("stateForm", new GenericEntityCodeForm());
		return "secured.setup.state";
	}
	
	@RequestMapping(value = "/state", method = RequestMethod.POST)
	
	public String formPost(Locale locale, HttpServletRequest req, HttpServletResponse resp, Model model,
			@ModelAttribute("stateForm") GenericEntityCodeForm form, BindingResult result) {
		LOGGER.debug("User action: " + form.getAction());
		
		String nextView = "secured.setup.state";
		
		switch (form.getAction()) {
			case WebConstant.ACTION_NEW:
				nextView = newRecord(form, result);
				break;
			case WebConstant.ACTION_EDIT:
				nextView = editRecord(form, result);
				break;
			case WebConstant.ACTION_DELETE:
				nextView = deleteRecord(form, result);
				break;
			default:
				break;
		}
		
		return nextView;
	}

	private String newRecord(GenericEntityCodeForm form, BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

	private String editRecord(GenericEntityCodeForm form, BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

	private String deleteRecord(GenericEntityCodeForm form, BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

}
