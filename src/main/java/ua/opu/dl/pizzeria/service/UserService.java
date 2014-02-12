package ua.opu.dl.pizzeria.service;

import java.util.List;

import ua.opu.dl.pizzeria.model.User;
import ua.opu.dl.pizzeria.model.UserRole;

public interface UserService {

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(User user);

	User loadById(long id);

	User loadByLogin(String login);

	List<User> loadAlluser();

	List<User> loadByRole(UserRole role);
}
