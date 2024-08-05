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
import cl.fullstackjava.model.service.BookService;
import cl.fullstackjava.model.service.LoanService;


@Controller
@RequestMapping("/books")
public class BookController {
	private static final Logger LOG = LoggerFactory.getLogger(BookController.class);
	private final BookService bookService;
	private final LoanService loanService;
	
	public BookController(BookService bookService, LoanService loanService) {
		super();
		this.bookService = bookService;
		this.loanService = loanService;
	}


	@GetMapping
	public String getAllBooks(Model model) {
		model.addAttribute("books",bookService.getAllBooks());
		LOG.info("Listando todos los libros");
		return "books";
	}
	
	
	@GetMapping("/{id}")
	public String editBook(@PathVariable Long id, Model model) {
		model.addAttribute("books",bookService.getAllBooks());
		model.addAttribute("book",bookService.getBookById(id));
		LOG.info("Mostrando libro con id: {}", id);
		return "books";
	}
	
	
	@PostMapping("/save")
	public String saveBook(@ModelAttribute Book book) {
		if (Objects.equals(book.getId(), null) || book.getId() == 0) {
			LOG.info("Creando un nuevo libro"); 
			bookService.addBook(book);
		} else {
			LOG.info("Actualizando libro existente");
			bookService.updateBook(book);
		}
		return "redirect:/books";		
	}
	
	
	@PostMapping("/delete/{id}")
	public String deleteBook(@PathVariable Long id, Model model) {
		LOG.info("Eliminado el libro con id: {}", id);
		Book book = bookService.getBookById(id);
		if (!loanService.hasAnyLoansByBook(book)) {
			bookService.deleteBook(id);
		} else {
			model.addAttribute("error", "Cannot delete book with any loans.");
		}
		model.addAttribute("books", bookService.getAllBooks());
		return "books";		 
	}
	
}
