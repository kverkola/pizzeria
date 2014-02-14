package ua.opu.dl.pizzeria.dao;

import java.util.List;

import ua.opu.dl.pizzeria.model.Customer;

public interface CustomerDao {
Customer loadById(long id);
long addCustomer(Customer customer);
Customer loadByOrderId(long id);
Customer loadByUserId(long id);
List<Customer> loadByPhone(String phone);
}
