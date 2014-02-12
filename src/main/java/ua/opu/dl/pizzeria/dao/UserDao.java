package ua.opu.dl.pizzeria.dao;

import java.util.List;

import ua.opu.dl.pizzeria.model.User;
import ua.opu.dl.pizzeria.model.UserRole;

public interface UserDao {

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(User user);

	User loadById(long id);

	User loadByLogin(String name);

	List<User> loadByRole(UserRole role);

	List<User> loadAllusers();
}
