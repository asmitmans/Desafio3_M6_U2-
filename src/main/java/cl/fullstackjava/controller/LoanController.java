package cl.fullstackjava.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.fullstackjava.model.dto.Book;
import cl.fullstackjava.model.dto.Loan;
import cl.fullstackjava.model.dto.User;
import cl.fullstackjava.model.service.BookService;
import cl.fullstackjava.model.service.LoanService;
import cl.fullstackjava.model.service.UserService;

@Controller
@RequestMapping("/loans")
public class LoanController {
	private LoanService loanService;
	private BookService bookService;
	private UserService userService;
	
	public LoanController(LoanService loanService, BookService bookService, UserService userService) {
		this.loanService = loanService;
		this.bookService = bookService;
		this.userService = userService;
	}
	
	@GetMapping("/catalog")
	public String getCatalog(@RequestParam(value = "selectedBookId", required = false) Integer selectedBookId, Model model) {
		model.addAttribute("books", bookService.getAllBooks());
		model.addAttribute("users", userService.getAllUser());
		if (selectedBookId != null) {
			Book selectedBook = bookService.getBookById(selectedBookId);
			model.addAttribute("selectedBook", selectedBook);
		}
		return "catalog";
	}
	
	@GetMapping("/catalog/search")
	public String searchAndSelectBook(@RequestParam(required = false) String query, @RequestParam(required = false) Integer selectedBookId, Model model) {
		List<Book> books;
		if (query != null && !query.isEmpty()) {
			books = bookService.searchBooks(query);
		} else {
			books = bookService.getAllBooks();
		}
		
		Book selectedBook = null;
		if (selectedBookId != null && selectedBookId != 0) {
			selectedBook = bookService.getBookById(selectedBookId);
		}
		
		model.addAttribute("books", books);
		model.addAttribute("selectedBook", selectedBook);
		model.addAttribute("users", userService.getAllUser());
		model.addAttribute("query", query);
		return "catalog";
	}
	
	@PostMapping("/catalog/loan")
	public String loanBook(@RequestParam int bookId, @RequestParam int userId, Model model) {
		Book book = bookService.getBookById(bookId);
		User user = userService.getUserById(userId);
		if (user == null) {
			model.addAttribute("errorMessage", "User ID is invalid.");
			model.addAttribute("books", bookService.getAllBooks());
			model.addAttribute("users", userService.getAllUser());
			model.addAttribute("selectedBook", book);
			return "catalog";
		}
		loanService.loanBook(book, user);
		return "redirect:/loans/catalog";
	}
	
	@PostMapping("/catalog/return")
	public String returnBook(@RequestParam int bookId) {
		loanService.returnBook(bookId);
		return "redirect:/loans/catalog";
	}
	
		
	@GetMapping("/history")
	public String getLoanHistory(@RequestParam(value = "userId", required = false) Integer userId, Model model) {
		List<Loan> loans;
		String errorMessage = null;
		
		if (userId != null) {
			User user = userService.getUserById(userId);
			if (user != null) {
				loans = loanService.getLoansByUser(user);
				model.addAttribute("selectedUserId", userId);
			} else {
				loans = loanService.getAllLoans();
				errorMessage = "El ID de usuario no existe o no está registrado.";
			}
		} else {
			loans = loanService.getAllLoans();
		}
		
		model.addAttribute("loans", loans);
		model.addAttribute("userId", userId);
		model.addAttribute("errorMessage", errorMessage);
		return "loan_history";
	}
				
}
