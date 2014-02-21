package ua.opu.dl.pizzeria.dao;

import java.util.List;

import ua.opu.dl.pizzeria.model.Users;
import ua.opu.dl.pizzeria.model.UserRole;

public interface UserDao {

	void addUser(Users user);

	void updateUser(Users user);

	void deleteUser(Users user);

	Users loadById(long id);

	Users loadByLogin(String name);

	List<Users> loadByRole(UserRole role);

	List<Users> loadAllusers();

	Users loadCookByPizzaId(long pizzaId);
}
