package ua.opu.dl.pizzeria.dao;

import java.util.List;

import ua.opu.dl.pizzeria.model.User;

public interface UserDao {

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(User user);

	User loadById(long id);

	List<User> loadAllusers();
}
