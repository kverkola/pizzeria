package ua.opu.dl.pizzeria.dao.impl;


import java.util.List;

import ua.opu.dl.pizzeria.dao.UserDao;
import ua.opu.dl.pizzeria.model.Status;
import ua.opu.dl.pizzeria.model.User;



public class UserDaoImpl implements UserDao{

	@Override
	public void addUser(User user) {
		
		
	}

	@Override
	public void updateUser(User user) {
		
		
	}

	@Override
	public void deleteUser(User user) {
	
		
	}

	@Override
	public User loadById(long id) {
		
		return new User("pup","pupkin","login","root",Status.COOK);
	}

	@Override
	public List<User> loadAllusers() {
		
		return null;
	}

}
