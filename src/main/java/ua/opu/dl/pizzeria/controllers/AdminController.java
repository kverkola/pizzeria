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
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private OrderService orderService;
	List <Order>orders;

	@RequestMapping(value = "/adminSearchOrder", method = RequestMethod.GET)
	public String searchOrder(@RequestParam("param") String param,
			@RequestParam("typeSearch") String typeSEarch, HttpSession session,
			ModelMap model) {

		
		if (typeSEarch.equals("Search Orders By Phone")) {
			orders = orderService.loadByPhone(param);
		} else if (typeSEarch.equals("Search Order By Id")) {
		
			Order	order = orderService.loadById(Long.valueOf(param));
			orders.add(order);

		} else if (typeSEarch.equals("Search Order By Status")) {
			orders = orderService.loadAllByStatus(Status.valueOf(param));
		}

		model.addAttribute("orders", orders);
		session.setAttribute("ordersUpdate", orders);
		return "/admin/updateOrder";
	}

	@RequestMapping(value = "/updateOrder", method = RequestMethod.GET)
	public String updateOrder(
			@RequestParam("id") long id,
			@RequestParam("newStarttime") String newStarttime,
			@RequestParam("newEndtime") String newEndtime,
			@RequestParam("newStatus") Status newStatus,
			@RequestParam("newPrice") Double newPrice,
			@RequestParam("newCustomerName") String newCustomerName,
			@RequestParam("newCustomerAddress") String newCustomerAddress,
			@RequestParam("newPhone") String newPhone,ModelMap model,HttpSession session) {
		
     orders=(List<Order>)session.getAttribute("ordersUpdate");
	
	for (Order order : orders) {
		if(order.getId()==id){
			if(newStarttime!=""){
		order.setStarttime(newStarttime);}
			if(newEndtime!=""){
		order.setEndtime(newEndtime);}			
		order.setStatus(newStatus);
			if(newPrice!=null){
		order.setPrice(newPrice);}
			if(newCustomerName!=""){
		order.getCustomer().setName(newCustomerName);}
			if(newCustomerAddress!=""){
		order.getCustomer().setAddress(newCustomerAddress);}
			if(newPhone!=""){
		order.getCustomer().setPhone(newPhone);}
		orderService.updateOrder(order);break;
		}
	}
		
		return "/admin/updateOrder";
	}
	@RequestMapping(value = "/updateOrderView", method = RequestMethod.GET)
	public String update(
		ModelMap model) {
		
			
		return "/admin/updateOrder";
	}
}
