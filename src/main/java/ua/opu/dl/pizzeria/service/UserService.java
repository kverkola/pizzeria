package ua.opu.dl.pizzeria.service;

import java.util.List;

import ua.opu.dl.pizzeria.model.User;

public interface UserService {

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(User user);

	User loadById(Integer id);

	List<User> loadAlluser();
}
