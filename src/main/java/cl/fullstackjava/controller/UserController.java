package cl.fullstackjava.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.fullstackjava.model.dto.Book;
import cl.fullstackjava.model.dto.User;
import cl.fullstackjava.model.service.BookServiceImpl;
import cl.fullstackjava.model.service.LoanService;
import cl.fullstackjava.model.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	private final UserService userService;
	private final LoanService loanService;	
	
	public UserController(UserService userService, LoanService loanService) {
		super();
		this.userService = userService;
		this.loanService = loanService;
	}


	@GetMapping
	public String getAllUser(Model model) {
		model.addAttribute("users", userService.getAllUser());
		LOG.info("Listando todos los user");
		return "users";
	}
	
	
	@GetMapping("/{id}")
	public String getUser(@PathVariable int id, Model model) {
		model.addAttribute("users", userService.getAllUser());
		model.addAttribute("user", userService.getUserById(id));
		LOG.info("Mostrando user con id: {}", id);
		return "users";
	}
	
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute User user) {
		if (Objects.equals(user.getId(), null) || user.getId() == 0) {
			LOG.info("Creando un nuevo user"); 
				userService.addUser(user);
		} else {
			LOG.info("Actualizando user existente");
			userService.updateUser(user);
		}
		return "redirect:/users";		
	}
	
	
	@PostMapping("/delete/{id}")
	public String deleteUser(@PathVariable int id, Model model) {
		LOG.info("Eliminado el user con id: {}", id);
		User user = userService.getUserById(id);
		if (!loanService.hasAnyLoansByUser(user)) {
			userService.deleteUser(id);
		} else {
			model.addAttribute("error", "Cannot delete user with any loans.");
		}
		model.addAttribute("users", userService.getAllUser());
		return "users";
	}
	
}
