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
import org.springframework.web.servlet.support.RequestContext;

import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Status;
import ua.opu.dl.pizzeria.model.Users;
import ua.opu.dl.pizzeria.model.UserRole;
import ua.opu.dl.pizzeria.service.OrderService;
import ua.opu.dl.pizzeria.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	private List<Order> orders;
	private List<Users> users;
	private Users user;
	private Order order;

	@RequestMapping(value = "/adminSearchOrder", method = RequestMethod.GET)
	public String searchOrder(@RequestParam("param") String param,
			@RequestParam("typeSearch") String typeSEarch, HttpSession session,
			ModelMap model) {
		orders = new ArrayList<Order>();

		if (typeSEarch.equals("Search Orders By Phone")) {
			orders.addAll(orderService.loadByPhone(param));
		} else if (typeSEarch.equals("Search Order By Id")) {

			try {
				order = orderService.loadById(Long.valueOf(param));
				if (order != null) {
					orders.add(order);
				}
			} catch (NumberFormatException e) {
				model.addAttribute("illegalArgument", "Input number only");
			}
			;
		}

		else if (typeSEarch.equals("Search Order By Status")) {
			try {
				orders.addAll(orderService.loadAllByStatus(Status
						.valueOf(param)));
			} catch (IllegalArgumentException e) {
				model.addAttribute("illegalArgument",
						"Input data should only be : PRE_ORDER, DELIVERY, IN_WORK, CLOSE");
			}
		}
		if (orders.isEmpty()) {
			model.addAttribute("nothing", "Nothing search!");
		}
		model.addAttribute("orders", orders);
		session.setAttribute("ordersUpdate", orders);
		return "/admin/updateOrder";
	}

	@RequestMapping(value = "/updateOrder", method = RequestMethod.GET)
	public String updateOrder(@RequestParam("id") long id,
			@RequestParam("newStarttime") String newStarttime,
			@RequestParam("newEndtime") String newEndtime,
			@RequestParam("newStatus") Status newStatus,
			@RequestParam("newPrice") String newPrice,
			@RequestParam("newCustomerName") String newCustomerName,
			@RequestParam("newCustomerAddress") String newCustomerAddress,
			@RequestParam("newPhone") String newPhone, ModelMap model,
			HttpSession session) {

		orders = (List<Order>) session.getAttribute("ordersUpdate");

		for (Order order : orders) {
			if (order.getId() == id) {
				if (newStarttime != "") {
					order.setStarttime(newStarttime);
				}
				if (newEndtime != "") {
					order.setEndtime(newEndtime);
				}
				if (order.getStatus() != newStatus) {
					order.setStatus(newStatus);
				}
				if (newPrice != "") {
					order.setPrice(Double.valueOf(newPrice));
				}
				if (newCustomerName != "") {
					order.getCustomer().setName(newCustomerName);
				}
				if (newCustomerAddress != "") {
					order.getCustomer().setAddress(newCustomerAddress);
				}
				if (newPhone != "") {
					order.getCustomer().setPhone(newPhone);
				}
				orderService.updateOrder(order);
				break;
			}
		}

		return "/admin/updateOrder";
	}

	@RequestMapping(value = "/updateOrderView", method = RequestMethod.GET)
	public String updateOrderView(ModelMap model) {

		return "/admin/updateOrder";
	}

	@RequestMapping(value = "/updateUserView", method = RequestMethod.GET)
	public String update(ModelMap model) {

		return "/admin/updateUser";
	}

	@RequestMapping(value = "/adminSearchUser", method = RequestMethod.GET)
	public String searchUser(@RequestParam("param") String param,
			@RequestParam("typeSearch") String typeSEarch, HttpSession session,
			ModelMap model) {
		users = new ArrayList<Users>();
		if (typeSEarch.equals("Search User By login")) {
			user = userService.loadByLogin(param);
			if (user != null) {
				users.add(user);
			}
		} else if (typeSEarch.equals("Search User By Id")) {
			try {
				user = userService.loadById(Long.valueOf(param));
				if (user != null) {
					users.add(user);
				}
			} catch (NumberFormatException e) {
				model.addAttribute("illegalArgument", "Input number only");
			}

		} else if (typeSEarch.equals("Search Users By Role")) {
			try {
				users.addAll(userService.loadByRole(UserRole.valueOf(param)));
			} catch (IllegalArgumentException e) {
				model.addAttribute("illegalArgument",
						"Input data should only be : ROLE_COOK,ROLE_MANAGER, ROLE_ADMIN, ROLE_USER");
			}
		}
		if (users.isEmpty()) {
			model.addAttribute("nothing", "Nothing search!");
		}
		model.addAttribute("users", users);
		session.setAttribute("usersUpdate", users);
		return "/admin/updateUser";
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	public String updateUser(@RequestParam("id") long id,
			@RequestParam("newfirstName") String newfirstName,
			@RequestParam("newlastName") String newlastName,
			@RequestParam("newRole") UserRole newRole,
			@RequestParam("newLogin") String newLogin,
			@RequestParam("newpassword") String newpassword,
			@RequestParam("newphone") String newphone,
			@RequestParam("newaddress") String newaddress,

			HttpSession session) {

		users = (List<Users>) session.getAttribute("usersUpdate");
		for (Users user : users) {
			if (user.getId() == id) {
				if (newfirstName != "") {
					user.setFirstName(newfirstName);
				}
				if (newlastName != "") {
					user.setLastName(newlastName);
				}
				if (user.getRole() != (newRole)) {
					user.setRole(newRole);
				}
				if (newLogin != "") {
					user.setLogin(newLogin);
				}
				if (newpassword != "") {
					user.setPassword(newpassword);
				}
				if (newphone != "") {
					user.getCustomer().setPhone(newphone);
				}
				if (newaddress != "") {
					user.getCustomer().setAddress(newaddress);
				}

				userService.updateUser(user);
				break;
			}
		}

		return "/admin/updateUser";
	}
}
