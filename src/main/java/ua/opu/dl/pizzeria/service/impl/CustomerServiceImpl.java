package ua.opu.dl.pizzeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.opu.dl.pizzeria.dao.CustomerDao;
import ua.opu.dl.pizzeria.model.Customer;
import ua.opu.dl.pizzeria.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerDao	customerDao;
	@Override
	public Customer loadById(long id) {
	
		return customerDao.loadById(id);
	}
	@Override
	public long addCustomer(Customer customer) {
		
		return customerDao.addCustomer(customer);
	}
	@Override
	public Customer loadByOrderId(long id) {
	
		return customerDao.loadByOrderId(id);
	}
	@Override
	public Customer loadByUserId(long id) {
		
		return customerDao.loadByUserId(id);
	}
	@Override
	public List<Customer> loadByPhone(String phone) {
		
		return customerDao.loadByPhone(phone);
	}

}
