# Desafío - Sistema de Gestión de Biblioteca (II)
  
**URL**  
http://localhost:8080
  
#### Datos simulados localmente
**User : UserServiceImpl**
  
```java
	public UserServiceImpl() {
		users = new ArrayList<User>();
		users.add(new User(userIdCounter++, "Juan", "Perez", "juan.p@mail.com"));
		users.add(new User(userIdCounter++, "Miguel", "Sanchez", "miguelo@mail.com"));
		users.add(new User(userIdCounter++, "Pamela", "Rojo", "projo@mail.com"));
	}
```

**Book : BookServiceImpl**
  
```java
	public BookServiceImpl() {
		books = new ArrayList<Book>();
		books.add(new Book(bookIdCounter++, "Cien años de soledad", "Gabriel García Márquez", true));
		books.add(new Book(bookIdCounter++, "El señor de los anillos", "J. R. R. Tolkien", false));
		books.add(new Book(bookIdCounter++, "1984", "George Orwell", true));
	}
```

**Loan : LoanServiceImpl**
  
```java
	public void init() {
		List<Book> books = bookService.getAllBooks();
		List<User> users = userService.getAllUser();
		
		loans.add(new Loan(loanIdCounter++, books.get(0), users.get(0), LocalDate.now().minusDays(10), LocalDate.now().minusDays(5)));
		loans.add(new Loan(loanIdCounter++, books.get(1), users.get(1), LocalDate.now().minusDays(3), null));
		loans.add(new Loan(loanIdCounter++, books.get(2), users.get(0), LocalDate.now().minusDays(8), LocalDate.now().minusDays(2)));
	}
```

NOTA: los ids parten en 1.
 