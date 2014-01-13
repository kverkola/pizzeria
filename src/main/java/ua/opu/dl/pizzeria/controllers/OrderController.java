package ua.opu.dl.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.service.OrderServise;
import ua.opu.dl.pizzeria.service.PizzaService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private OrderServise orderService;

    @RequestMapping(value = "/add-pizza/{name}", method = RequestMethod.GET)
    public String addPizza(@PathVariable("name") String name,
                           HttpSession session) {

        Order order = (Order) session.getAttribute("order");

        if (order == null) {
            order = new Order();
            order.setPizzas(new HashMap<Pizza, Integer>());
        }

        orderService.addPizza(order, pizzaService.loadByName(name));
        session.setAttribute("order", order);
        session.setAttribute("pizzasInOrder", order.getPizzas());

        return "redirect:/menu";
    }
}
