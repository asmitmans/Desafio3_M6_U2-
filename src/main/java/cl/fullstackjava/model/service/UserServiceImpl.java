package cl.fullstackjava.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.fullstackjava.model.dto.Book;
import cl.fullstackjava.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	private List<User> users;
	private int userIdCounter = 1;
		
	public UserServiceImpl() {
		users = new ArrayList<User>();
		users.add(new User(userIdCounter++, "Juan", "Perez", "juan.p@mail.com"));
		users.add(new User(userIdCounter++, "Miguel", "Sanchez", "miguelo@mail.com"));
		users.add(new User(userIdCounter++, "Pamela", "Rojo", "projo@mail.com"));
	}

	@Override
	public List<User> getAllUser() {
		LOG.info("Obteniendo todos los usuarios");
		return users;
	}

	@Override
	public User getUserById(int id) {
		LOG.info("Obteniendo el libro con id: {}", id);
		return users.stream()
				.filter(u->Objects.equals(u.getId(), id))
				.findFirst()
				.orElse(null);
	}

	@Override
	public User addUser(User user) {
		user.setId(userIdCounter++);
		users.add(user);
		LOG.info("Añadiendo el usuario: {} {}", user.getFirstName(), user.getLastName());
		return user;
	}

	@Override
	public User updateUser(User userUpdated) {
		User user = getUserById(userUpdated.getId());
		if(user != null) {
			user.setFirstName(userUpdated.getFirstName());
			user.setLastName(userUpdated.getLastName());
			user.setEmail(userUpdated.getEmail());
		}
		LOG.info("Actualizado el usuario: {} {}", user.getFirstName(), user.getLastName());
		return user;
	}
	
	@Override
	public boolean deleteUser(int id) {
		return users.removeIf(u->Objects.equals(u.getId(), id));
	}
	
}
