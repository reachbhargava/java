package spring.springmvc;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("nameOnWelcome")
public class LoginController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = ((User) auth.getPrincipal()).getUsername();
		model.put("nameOnWelcome", userName);
		return "welcome";
	}

}
