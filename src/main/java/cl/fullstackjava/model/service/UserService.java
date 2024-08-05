package cl.fullstackjava.model.service;

import java.util.List;

import cl.fullstackjava.model.dto.User;

public interface UserService {
	public List<User> getAllUser();
	public User getUserById(int id);
	public User addUser(User user);
	public User updateUser(User user);
	public boolean deleteUser(int id);

}
