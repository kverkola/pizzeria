package ua.opu.dl.pizzeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.opu.dl.pizzeria.dao.UserDao;
import ua.opu.dl.pizzeria.model.User;
import ua.opu.dl.pizzeria.service.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
	private UserDao userDao;

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User loadById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> loadAlluser() {
		// TODO Auto-generated method stub
		return null;
	}

}
