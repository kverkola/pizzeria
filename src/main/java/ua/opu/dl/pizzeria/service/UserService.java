package ua.opu.dl.pizzeria.service;

import java.util.List;

import ua.opu.dl.pizzeria.model.Users;
import ua.opu.dl.pizzeria.model.UserRole;

public interface UserService {

	void addUser(Users user);

	void updateUser(Users user);

	void deleteUser(Users user);

	Users loadById(long id);

	Users loadByLogin(String login);

	List<Users> loadAlluser();

	List<Users> loadByRole(UserRole role);
	Users loadCookByPizzaId(long pizzaId);
}
