package cl.fullstackjava.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.fullstackjava.model.dto.Book;

@Service
public class BookServiceImpl implements BookService {
	private static final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);
	private List<Book> books;
	private int bookIdCounter = 1;
	
	public BookServiceImpl() {
		books = new ArrayList<Book>();
		books.add(new Book(bookIdCounter++, "Cien años de soledad", "Gabriel García Márquez", true));
		books.add(new Book(bookIdCounter++, "El señor de los anillos", "J. R. R. Tolkien", false));
		books.add(new Book(bookIdCounter++, "1984", "George Orwell", true));
	}
	
	public List<Book> getAllBooks() {
		LOG.info("Obteniendo todos los libros");
		return books;
	}
	
	public Book getBookById(long id) {
		LOG.info("Obteniendo el libro con id: {}", id);
		return books.stream()
				.filter(b->Objects.equals(b.getId(), id))
				.findFirst()
				.orElse(null);
	}
	
	public Book addBook(Book book) {
		book.setId(bookIdCounter++);
		book.setAvaible(true);
		books.add(book);
		LOG.info("Añadiendo el libro: {}", book.getTitle());
		return book;
	}
	
	public Book updateBook(Book updatedBook) {
		Book book = getBookById(updatedBook.getId());
		if(book!=null) {
			book.setTitle(updatedBook.getTitle());
			book.setAuthor(updatedBook.getAuthor());
			book.setAvaible(updatedBook.isAvaible());
		}
		LOG.info("Actualizando el libro: {}", book.getTitle());
		return book;
	}
	
	public boolean deleteBook(Long id) {
		return books.removeIf(b->Objects.equals(b.getId(),id));
	}

	@Override
	public List<Book> searchBooks(String query) {
		return books.stream()
				.filter(b->b.getTitle().toLowerCase().contains(query.toLowerCase()) ||
						b.getAuthor().toLowerCase().contains(query.toLowerCase()) )
				.collect(Collectors.toList());
	}

}
