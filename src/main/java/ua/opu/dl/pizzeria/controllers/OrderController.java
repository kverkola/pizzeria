package ua.opu.dl.pizzeria.controllers;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.opu.dl.pizzeria.model.Additional;
import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.model.Product;
import ua.opu.dl.pizzeria.service.AdditionalService;
import ua.opu.dl.pizzeria.service.OrderService;
import ua.opu.dl.pizzeria.service.PizzaService;

import javax.servlet.http.HttpSession;
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
	public String addPizza(@PathVariable("id") Integer id, HttpSession session) {

		Order order = (Order) session.getAttribute("order");

		if (order == null) {
			order = new Order();
            order.setProducts(new ArrayList<Product>());
		} else if (order.getProducts(Pizza.class) == null) {
            order.setProducts(new ArrayList<Product>());
		}

        order.addProduct(pizzaService.loadById(id));

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
        } else if (order.getProducts(Pizza.class) == null) {
            order.setProducts(new ArrayList<Product>());
        }

        Pizza customPizza = (Pizza) session.getAttribute("customPizza");

        order.addProduct(customPizza);

        session.setAttribute("order", order);
        session.setAttribute("pizzasInOrder", order.getProducts(Pizza.class));

        return "redirect:/menu";
    }

	@RequestMapping(value = "/add-additional/{id}", method = RequestMethod.GET)
	public String addAdditional(@PathVariable("id") Integer id,
			HttpSession session) {

		Order order = (Order) session.getAttribute("order");

		if (order == null) {
			order = new Order();
            order.setProducts(new ArrayList<Product>());
		} else if (order.getProducts(Additional.class) == null) {
            order.setProducts(new ArrayList<Product>());
		}

        order.addProduct(additionalService.loadByName("pepsi"));

		session.setAttribute("order", order);
		session.setAttribute("additionalInOrder", order.getProducts(Additional.class));

		return "redirect:/Additional";
	}

    @RequestMapping(value = "/remove-product/{id}", method = RequestMethod.GET)
    public String removePizza(@PathVariable("id") Integer id,
                              HttpSession session) {

        Order order = (Order) session.getAttribute("order");

        order.removeProduct(id);

        session.setAttribute("order", order);
        session.setAttribute("pizzasInOrder", order.getProducts(Pizza.class));
        session.setAttribute("additionalInOrder", order.getProducts(Additional.class));

        return "redirect:/order/make-order";
    }

	@RequestMapping(value = "/make-order", method = RequestMethod.GET)
	public String makeOrder() {

		return "completeOrder";
	}

	@RequestMapping(value = "/change-product-count", method = RequestMethod.POST,
            params = {"id", "value"})
	public String changePizzasCount(@RequestParam Integer id,
			@RequestParam Integer value, HttpSession session) {

		Order order = (Order) session.getAttribute("order");
        order.changeProductQuantity(id, value);

		session.setAttribute("order", order);
		session.setAttribute("pizzasInOrder", order.getProducts(Pizza.class));
        session.setAttribute("additionalInOrder", order.getProducts(Additional.class));

		return "redirect:/order/make-order";
	}

	@RequestMapping(value = "/searchOrder", method = RequestMethod.GET)
	public String order(@RequestParam("orderId") Integer orderId,
			HttpSession session, ModelMap model) {

		Order order = orderService.loadById(orderId);

		session.setAttribute("orderById", order);
		model.addAttribute("order", order);

		return "searchOrder";
	}

	@RequestMapping(value = "/showIngredient/{id}", method = RequestMethod.GET)
	public String showIngredient(@PathVariable("id") Integer id,
			ModelMap model, HttpSession session) {

		Pizza pizza = pizzaService.loadById(id);
		Order order = (Order) session.getAttribute("orderById");
		model.addAttribute("order", order);

		model.addAttribute("pizza", pizza);

		return "showIngredient";
	}
}
