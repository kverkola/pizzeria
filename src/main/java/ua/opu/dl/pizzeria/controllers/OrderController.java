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
			//order.setPizzas(new ArrayList<Pizza>());
            order.setProducts(new ArrayList<Product>());
		} else if (order.getPizzas() == null) {
			//order.setPizzas(new ArrayList<Pizza>());
            order.setProducts(new ArrayList<Product>());
		}

        //order.addPizza(pizzaService.loadById(id));
        order.addProduct(pizzaService.loadById(id));

		session.setAttribute("order", order);
		session.setAttribute("pizzasInOrder", order.getPizzas());

		return "redirect:/menu";
	}

    @RequestMapping(value = "/add-custom-pizza", method = RequestMethod.GET)
    public String addCustomPizza(HttpSession session) {

        Order order = (Order) session.getAttribute("order");

        if (order == null) {
            order = new Order();
            order.setProducts(new ArrayList<Product>());
            //order.setPizzas(new ArrayList<Pizza>());
        } else if (order.getPizzas() == null) {
            order.setProducts(new ArrayList<Product>());
            //order.setPizzas(new ArrayList<Pizza>());
        }

        Pizza customPizza = (Pizza) session.getAttribute("customPizza");

       // order.addPizza(customPizza);
        order.addProduct(customPizza);

        session.setAttribute("order", order);
        session.setAttribute("pizzasInOrder", order.getPizzas());

        return "redirect:/menu";
    }

	@RequestMapping(value = "/remove-pizza/{id}", method = RequestMethod.GET)
	public String removePizza(@PathVariable("id") Integer id,
			HttpSession session) {

		Order order = (Order) session.getAttribute("order");

        order.removeProduct(id);

		session.setAttribute("order", order);
		session.setAttribute("pizzasInOrder", order.getPizzas());

		return "redirect:/order/make-order";
	}

	@RequestMapping(value = "/add-additional/{name}", method = RequestMethod.GET)
	public String addAdditional(@PathVariable("name") String name,
			HttpSession session) {

		Order order = (Order) session.getAttribute("order");

		if (order == null) {
			order = new Order();
            order.setProducts(new ArrayList<Product>());
			//order.setAdditions(new ArrayList<Additional>());
		} else if (order.getAdditions() == null) {
            order.setProducts(new ArrayList<Product>());
			//order.setAdditions(new ArrayList<Additional>());
		}

		//order.addAdditional(additionalService.loadByName(name));
        order.addProduct(additionalService.loadByName(name));

		session.setAttribute("order", order);
		session.setAttribute("additionalInOrder", order.getAdditions());

		return "redirect:/Additional";
	}

	@RequestMapping(value = "/remove-additional/{id}", method = RequestMethod.GET)
	public String removeAdditional(@PathVariable("id") Integer id,
			HttpSession session) {

		Order order = (Order) session.getAttribute("order");

        order.removeProduct(id);

		session.setAttribute("order", order);
		session.setAttribute("additionalInOrder", order.getAdditions());

		return "redirect:/order/make-order";
	}

	@RequestMapping(value = "/make-order", method = RequestMethod.GET)
	public String makeOrder() {

		return "completeOrder";
	}

	@RequestMapping(value = "/change-pizzas-count", method = RequestMethod.POST,
            params = {"id", "value"})
	public String changePizzasCount(@RequestParam Integer id,
			@RequestParam Integer value, HttpSession session) {

		Order order = (Order) session.getAttribute("order");

/*		for (Pizza pizza : order.getPizzas().keySet()) {
			if (pizza.getId().equals(id)) {
				if (value != 0) {
					order.getPizzas().put(pizza, value);
				} else {
					order.getPizzas().remove(pizza);
				}

				orderService.updatePrice(order);
				break;
			}
		}
        */
		session.setAttribute("order", order);
		session.setAttribute("pizzasInOrder", order.getPizzas());

		return "redirect:/order/make-order";
	}

	@RequestMapping(value = "/change-additions-count", method = RequestMethod.POST,
            params = {"id", "value" })
	public String changeAdditionsCount(@RequestParam Integer id,
			@RequestParam Integer value, HttpSession session) {

		Order order = (Order) session.getAttribute("order");
        order.changeProductQuantity(id, value);

		session.setAttribute("order", order);
		session.setAttribute("additionalInOrder", order.getAdditions());

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
