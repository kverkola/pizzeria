package ua.opu.dl.pizzeria.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Status;
import ua.opu.dl.pizzeria.service.OrderService;

@Controller
public class AdminController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/adminSearchOrder", method = RequestMethod.GET)
	public String searchOrder(@RequestParam("param") String param,
			@RequestParam("typeSearch") String typeSEarch, HttpSession session,
			ModelMap model) {

		List<Order> orders = new ArrayList<Order>();
		if (typeSEarch.equals("Search Orders By Phone")) {
			orders = orderService.loadByPhone(param);
		} else if (typeSEarch.equals("Search Order By Id")) {
			Order order =null;
			order = orderService.loadById(Long.valueOf(param));
			 orders.add(order);

		} else if (typeSEarch.equals("Search Order By Status")) {
			orders = orderService.loadAllByStatus(Status.valueOf(param));
		}
		
		model.addAttribute("orders", orders);
		return "/admin/updateOrder";
	}

	@RequestMapping(value = "/updateOrder", method = RequestMethod.GET)
	public String updateOrder(ModelMap model) {

		return "/admin/updateOrder";
	}

}
