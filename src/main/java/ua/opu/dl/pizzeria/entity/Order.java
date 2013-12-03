package ua.opu.dl.pizzeria.entity;

import java.util.Date;
import java.util.Map;

public class Order {

	private int id;
	private Map<Pizza,Integer> pizzas;//название и количество
	private Status status;
	private Date starttime;
	private Date endtime;
	private double price;
	private Map<Additional, Integer> additional;//название и количество
	private String phone;
}
