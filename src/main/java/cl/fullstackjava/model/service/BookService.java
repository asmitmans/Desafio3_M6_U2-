package cl.fullstackjava.model.service;

import java.util.List;

import cl.fullstackjava.model.dto.Book;

public interface BookService {
	public List<Book> getAllBooks();
	public Book getBookById(long id);
	public Book addBook(Book book);
	public Book updateBook(Book updatedBook);
	public boolean deleteBook(Long id);
	List<Book> searchBooks(String query);
}
