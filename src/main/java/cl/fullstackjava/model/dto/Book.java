package cl.fullstackjava.model.dto;

public class Book {
	private long id;
	private String title;
	private String author;
	private boolean isAvaible;
	
	public Book() {
	}

	public Book(long id, String title, String author, boolean isAvaible) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.isAvaible = isAvaible;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isAvaible() {
		return isAvaible;
	}

	public void setAvaible(boolean isAvaible) {
		this.isAvaible = isAvaible;
	}
	
}
