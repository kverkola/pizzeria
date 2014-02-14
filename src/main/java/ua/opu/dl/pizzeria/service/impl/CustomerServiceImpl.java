package ua.opu.dl.pizzeria.service.impl;

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
	public long addCustomer() {
		
		return customerDao.addCustomer();
	}

}
