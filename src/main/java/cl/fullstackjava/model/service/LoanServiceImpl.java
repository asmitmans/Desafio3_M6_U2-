package cl.fullstackjava.model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.fullstackjava.model.dto.Book;
import cl.fullstackjava.model.dto.Loan;
import cl.fullstackjava.model.dto.User;
import jakarta.annotation.PostConstruct;

@Service
public class LoanServiceImpl implements LoanService {
	private List<Loan> loans;
	private int loanIdCounter = 1;
	
	private BookService bookService;
	private UserService userService;	
	
	public LoanServiceImpl(BookService bookService, UserService userService) {
		this.loans = new ArrayList<>();
		this.bookService = bookService;
		this.userService = userService;
	}

	@PostConstruct
    public void init() {
        List<Book> books = bookService.getAllBooks();
        List<User> users = userService.getAllUser();

        loans.add(new Loan(loanIdCounter++, books.get(0), users.get(0), LocalDate.now().minusDays(10), LocalDate.now().minusDays(5)));
        loans.add(new Loan(loanIdCounter++, books.get(1), users.get(1), LocalDate.now().minusDays(3), null));
        loans.add(new Loan(loanIdCounter++, books.get(2), users.get(0), LocalDate.now().minusDays(8), LocalDate.now().minusDays(2)));
    }

	@Override
	public List<Loan> getAllLoans() {
		return loans;
	}

	@Override
	public Loan loanBook(Book book, User user) {
		Loan loan = new Loan(loanIdCounter++, book, user, LocalDate.now(), null);
		loans.add(loan);
		book.setAvaible(false);
		return loan;
	}

	@Override
	public Loan returnBook(int bookId) {
		
		Loan loan = getLoanByBook(bookId);
		
		if (loan != null) {
			loan.setReturnDate(LocalDate.now());
			loan.getBook().setAvaible(true);
		} 
		
		return loan;
	}

	@Override
	public List<Loan> getLoansByUser(User user) {
		return loans.stream()
				.filter(l-> l.getUser().getId() == user.getId())
				.toList();
	}

	@Override
	public Loan getLoanByBook(long bookId) {
		return loans.stream().filter(l-> l.getBook().getId()==bookId && l.getReturnDate()==null).findFirst().orElse(null);
	}

	@Override
	public boolean hasAnyLoansByUser(User user) {
		return loans.stream()
				.anyMatch(l-> l.getUser().getId()==user.getId());
		
	}

	@Override
	public boolean hasAnyLoansByBook(Book book) {
		return loans.stream()
				.anyMatch(l-> l.getBook().getId()==book.getId());
	}

}
