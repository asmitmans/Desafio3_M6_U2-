package cl.fullstackjava.model.service;

import java.util.List;

import cl.fullstackjava.model.dto.Book;
import cl.fullstackjava.model.dto.Loan;
import cl.fullstackjava.model.dto.User;

public interface LoanService {
	List<Loan> getAllLoans();
	Loan loanBook(Book book, User user);
	Loan returnBook(int loanId);
	List<Loan> getLoansByUser(User user);
	Loan getLoanByBook(long bookId);
	boolean hasAnyLoansByUser(User user);
	boolean hasAnyLoansByBook(Book book);
}
