package cl.fullstackjava.model.dto;

import java.time.LocalDate;

public class Loan {
	private int id;
	private Book book;
	private User user;
	private LocalDate loanDate;
	private LocalDate returnDate;
	
	public Loan() {
	}

	public Loan(int id, Book book, User user, LocalDate loanDate, LocalDate returnDate) {
		super();
		this.id = id;
		this.book = book;
		this.user = user;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

}
