package ua.opu.dl.pizzeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.opu.dl.pizzeria.dao.UserDao;
import ua.opu.dl.pizzeria.model.Customer;
import ua.opu.dl.pizzeria.model.Users;
import ua.opu.dl.pizzeria.model.UserRole;
import ua.opu.dl.pizzeria.service.CustomerService;
import ua.opu.dl.pizzeria.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private CustomerService customerService;
	private Users user;
	private List<Users> users;
	private Customer customer;

	@Override
	public void addUser(Users user) {
		userDao.addUser(user);

	}

	@Override
	public void updateUser(Users user) {
		userDao.updateUser(user);

	}

	@Override
	public void deleteUser(Users user) {

	}

	@Override
	public Users loadById(long id) {
		user = userDao.loadById(id);
		customer = customerService.loadByUserId(user.getId());
		user.setCustomer(customer);
		return user;
	}

	@Override
	public Users loadByLogin(String login) {
		user = userDao.loadByLogin(login);
		customer = customerService.loadByUserId(user.getId());
		user.setCustomer(customer);
		return user;
	}

	@Override
	public List<Users> loadAlluser() {

		return null;
	}

	@Override
	public List<Users> loadByRole(UserRole role) {

		users = userDao.loadByRole(role);
		for (Users user : users) {
			customer = customerService.loadByUserId(user.getId());
			user.setCustomer(customer);
		}
		return users;

	}

}
