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
import ua.opu.dl.pizzeria.service.AdditionalService;
import ua.opu.dl.pizzeria.service.OrderService;
import ua.opu.dl.pizzeria.service.PizzaService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

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

	@RequestMapping(value = "/add-pizza/{name}", method = RequestMethod.GET)
	public String addPizza(@PathVariable("name") String name,
			HttpSession session) {

		Order order = (Order) session.getAttribute("order");

		if (order == null) {
			order = new Order();
			order.setPizzas(new HashMap<Pizza, Integer>());
		} else if (order.getPizzas() == null) {
			order.setPizzas(new HashMap<Pizza, Integer>());
		}

		order.addPizza(pizzaService.loadByName(name));

		session.setAttribute("order", order);
		session.setAttribute("pizzasInOrder", order.getPizzas());

		return "redirect:/menu";
	}

	@RequestMapping(value = "/remove-pizza/{name}", method = RequestMethod.GET)
	public String removePizza(@PathVariable("name") String name,
			HttpSession session) {

		Order order = (Order) session.getAttribute("order");

		for (Pizza pizza : order.getPizzas().keySet()) {
			if (pizza.getName().equals(name)) {
				order.setPrice(order.getPrice() - pizza.getPrice()
						* order.getPizzas().get(pizza));
				order.getPizzas().remove(pizza);
				LOG.info(pizza.getName() + " removed from order");
				break;
			}
		}

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
			order.setAdditional(new HashMap<Additional, Integer>());
		} else if (order.getAdditional() == null) {
			order.setAdditional(new HashMap<Additional, Integer>());
		}

		order.addAdditional(additionalService.loadByName(name));

		session.setAttribute("order", order);
		session.setAttribute("additionalInOrder", order.getAdditional());

		return "redirect:/Additional";
	}

	@RequestMapping(value = "/remove-additional/{name}", method = RequestMethod.GET)
	public String removeAdditional(@PathVariable("name") String name,
			HttpSession session) {

		Order order = (Order) session.getAttribute("order");

		for (Additional additional : order.getAdditional().keySet()) {
			if (additional.getName().equals(name)) {
				order.setPrice(order.getPrice() - additional.getPrice()
						* order.getAdditional().get(additional));
				order.getAdditional().remove(additional);
				LOG.info(additional.getName() + " removed from order");
				break;
			}
		}

		session.setAttribute("order", order);
		session.setAttribute("pizzasInOrder", order.getPizzas());

		return "redirect:/order/make-order";
	}

	@RequestMapping(value = "/make-order", method = RequestMethod.GET)
	public String makeOrder() {

		return "completeOrder";
	}

	@RequestMapping(value = "/change-pizzas-count", method = RequestMethod.POST,
                        params = {"name", "value"})
	public String changePizzasCount(@RequestParam String name, @RequestParam Integer value,
                              HttpSession session) {

        Order order = (Order) session.getAttribute("order");

        for (Pizza pizza : order.getPizzas().keySet()) {
            if (pizza.getName().equals(name)) {
                if (value != 0) {
                    order.getPizzas().put(pizza, value);
                } else {
                    order.getPizzas().remove(pizza);
                }

                orderService.updatePrice(order);
                break;
            }
        }

        session.setAttribute("order", order);
        session.setAttribute("pizzasInOrder", order.getPizzas());

		return "redirect:/order/make-order";
	}

    @RequestMapping(value = "/change-additions-count", method = RequestMethod.POST,
                        params = {"name", "value"})
    public String changeAdditionsCount(@RequestParam String name, @RequestParam Integer value,
                              HttpSession session) {

        Order order = (Order) session.getAttribute("order");

        for (Additional additional : order.getAdditional().keySet()) {
            if (additional.getName().equals(name)) {
                if (value != 0) {
                    order.getAdditional().put(additional, value);
                } else {
                    order.getAdditional().remove(additional);
                }

                orderService.updatePrice(order);
                break;
            }
        }

        session.setAttribute("order", order);
        session.setAttribute("additionalInOrder", order.getAdditional());

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
