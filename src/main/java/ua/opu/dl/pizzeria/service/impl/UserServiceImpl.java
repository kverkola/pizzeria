package ua.opu.dl.pizzeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.opu.dl.pizzeria.dao.UserDao;
import ua.opu.dl.pizzeria.model.User;
import ua.opu.dl.pizzeria.model.UserRole;
import ua.opu.dl.pizzeria.service.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
	private UserDao userDao;

	@Override
	public void addUser(User user) {
		userDao.addUser(user);

	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);

	}

	@Override
	public void deleteUser(User user) {
		

	}

	@Override
	public User loadById(long id) {
		
		return userDao.loadById(id);
	}

    @Override
    public User loadByLogin(String login) {
        return userDao.loadByLogin(login);
    }

    @Override
	public List<User> loadAlluser() {
		
		return null;
	}

	@Override
	public List<User> loadByRole(UserRole role) {
		
		return userDao.loadByRole(role);
	}

}
