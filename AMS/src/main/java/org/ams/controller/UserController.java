package org.ams.controller;

import javax.validation.Valid;

import org.ams.model.User;
import org.ams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@Autowired
	private UserService service;

	@RequestMapping("/")
	public String viewHomePage() {
		System.out.println("Entering viewHomePage()");
		return "home";
	}

	@RequestMapping("/new")
	public String showNewUserForm(Model model) {
		System.out.println("Entering showNewUserForm()");
		User user = new User();
		model.addAttribute("user", user);
		return "user";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult bindingResult, Model model) {
		System.out.println("Entering saveUser()");
		System.out.println("bindingResult : " + bindingResult);
		System.out.println("bindingResult : " + bindingResult.hasErrors());
		if(bindingResult.hasErrors()) {
			System.out.println("Binding result has errors");
			return "user";
		}
		System.out.println("Call save()... ");
		service.save(user);
		return "userStatus";
	}

	@RequestMapping("/login")
	public String showLoginForm(Model model, @ModelAttribute("user") User user) {
		System.out.println("Entering showLoginPage()");
		User user1 = new User();
		if (user != null) {
			user1 = user;
		}
		model.addAttribute("user", user1);
		return "login";
	}
	//This is a Post method for validate login
	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	public String validatingUser(Model model, @ModelAttribute("user") User user) {
		System.out.println("Validating the credentials");
		// Check user exists in the database using email
		User currentUser = service.findByEmail(user.getEmail());
		// if user is valid and password entered by user matched the password in the
		// database, then go to employeeList page(which is employee home page)
		if (currentUser != null && user.getPassword().equals(currentUser.getPassword())) {
			model.addAttribute("user", user);
			System.out.println("user authenticated with their valid email and password");
			return "/thanks";
		} else {
			System.out.println("Invalid username or password");
			// else, go to Login page to re-enter the login details
			return this.showLoginForm(model, user);
		}

	}

}

