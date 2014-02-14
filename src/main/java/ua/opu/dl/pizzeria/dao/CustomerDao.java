package ua.opu.dl.pizzeria.dao;

import ua.opu.dl.pizzeria.model.Customer;

public interface CustomerDao {
Customer loadById(long id);
long addCustomer();
}
