package ua.opu.dl.pizzeria.service;

import ua.opu.dl.pizzeria.model.Customer;

public interface CustomerService {

	Customer loadById(long id);
	long addCustomer();
}
