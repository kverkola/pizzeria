package ua.opu.dl.pizzeria.controllers;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ua.opu.dl.pizzeria.model.*;
import ua.opu.dl.pizzeria.service.AdditionalService;
import ua.opu.dl.pizzeria.service.OrderService;
import ua.opu.dl.pizzeria.service.PizzaService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

	private static final Logger LOG = LoggerFactory
			.getLogger(OrderController.class);

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private AdditionalService additionalService;

	@RequestMapping(value = "/add-pizza/{id}", method = RequestMethod.GET)
	public String addPizza(@PathVariable("id") long id, HttpSession session) {

		Order order = (Order) session.getAttribute("order");

		if (order == null) {
			order = new Order();
			order.setProducts(new ArrayList<Product>());
		}

		order.addProduct(pizzaService.loadById(id));
		order.setStarttime(new Date().toString());
		session.setAttribute("order", order);
		session.setAttribute("pizzasInOrder", order.getProducts(Pizza.class));

		return "redirect:/menu";
	}

	@RequestMapping(value = "/add-custom-pizza", method = RequestMethod.GET)
	public String addCustomPizza(HttpSession session) {

		Order order = (Order) session.getAttribute("order");

		if (order == null) {
			order = new Order();
			order.setProducts(new ArrayList<Product>());
		}

		Pizza customPizza = (Pizza) session.getAttribute("customPizza");

		order.addProduct(customPizza);

		session.setAttribute("order", order);
		session.setAttribute("pizzasInOrder", order.getProducts(Pizza.class));

		return "redirect:/menu";
	}

	@RequestMapping(value = "/send-order", method = RequestMethod.POST)
	public String send(@Valid Customer customer, BindingResult result,
			ModelMap model, HttpSession session) {

		if (result.hasErrors()) {

			LOG.error("Wrong input data!");

			model.addAttribute("errorMessage", "Wrong input data!");
			model.addAttribute("guestUser", customer);

			return "completeOrder";

		} else {

			LOG.info("Name: " + customer.getName() + ", address: "
					+ customer.getAddress() + ", phone: " + customer.getPhone());

			Order order = (Order) session.getAttribute("order");
			order.setStarttime(new Date().toString());
			order.setCustomer(customer);
            order.setStatus(Status.IN_WORK);

            orderService.addOrder(order);

			order = new Order();
			order.setProducts(new ArrayList<Product>());

			session.setAttribute("order", order);
			session.setAttribute("pizzasInOrder",
					order.getProducts(Pizza.class));
			session.setAttribute("additionalInOrder",
					order.getProducts(Additional.class));

			session.setAttribute("showResult", "sendOrderSuccess");

			return "redirect:/";
		}
	}

	@RequestMapping(value = "/add-additional/{id}", method = RequestMethod.GET)
	public String addAdditional(@PathVariable("id") long id, HttpSession session) {

		Order order = (Order) session.getAttribute("order");

		if (order == null) {
			order = new Order();
			order.setProducts(new ArrayList<Product>());
		}

		order.addProduct(additionalService.loadById(id));

		session.setAttribute("order", order);
		session.setAttribute("additionalInOrder",
				order.getProducts(Additional.class));

		return "redirect:/additional";
	}

	@RequestMapping(value = "/remove-product/{productId}", method = RequestMethod.GET)
	public String removePizza(@PathVariable("productId") long productId,
			HttpSession session) {

		Order order = (Order) session.getAttribute("order");

		order.removeProduct(productId);

		session.setAttribute("order", order);
		session.setAttribute("pizzasInOrder", order.getProducts(Pizza.class));
		session.setAttribute("additionalInOrder",
				order.getProducts(Additional.class));

		return "redirect:/order/make-order";
	}

	@RequestMapping(value = "/make-order", method = RequestMethod.GET)
	public String makeOrder() {

		return "completeOrder";
	}

	@RequestMapping(value = "/change-product-count", method = RequestMethod.POST, params = {
			"productId", "value" })
	public String changePizzasCount(@RequestParam long productId,
			@RequestParam Integer value, HttpSession session) {

		Order order = (Order) session.getAttribute("order");

		if (value > 0) {
			order.changeProductQuantity(productId, value);
			session.setAttribute("order", order);
			session.setAttribute("pizzasInOrder",
					order.getProducts(Pizza.class));
			session.setAttribute("additionalInOrder",
					order.getProducts(Additional.class));
		}

		return "redirect:/order/make-order";
	}

	@RequestMapping(value = "/searchOrder", method = RequestMethod.GET)
	public String order(@RequestParam("phone") String phone,
			HttpSession session, ModelMap model) {
		List<Order> orders = new ArrayList<Order>();
		orders = orderService.loadByPhone(phone);
		session.setAttribute("orderById", orders);
		model.addAttribute("orders", orders);
		return "searchOrder";
	}

	@RequestMapping(value = "/showIngredient/{id}", method = RequestMethod.GET)
	public String showIngredient(@PathVariable("id") long id, ModelMap model,
			HttpSession session) {

		Pizza pizza = pizzaService.loadById(id);
		Order order = (Order) session.getAttribute("orderById");
		model.addAttribute("order", order);

		model.addAttribute("pizza", pizza);

		return "showIngredient";
	}
}
