package ua.opu.dl.pizzeria.service;

import java.util.List;

import ua.opu.dl.pizzeria.model.Customer;

public interface CustomerService {

	Customer loadById(long id);
	long addCustomer(Customer customer);
	Customer loadByOrderId(long id);
	Customer loadByUserId(long id);
	List<Customer> loadByPhone(String phone);
}
