package ua.opu.dl.pizzeria.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.opu.dl.pizzeria.model.*;
import ua.opu.dl.pizzeria.service.OrderService;
import ua.opu.dl.pizzeria.service.UserService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;

@Controller
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    /**
     * Show login
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        return "user/login";
    }

    /**
     * Login failed
     */
    @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    public String loginError(ModelMap model) {

        model.addAttribute("error", "true");
        return "user/login";
    }

    @RequestMapping(value = "/user/send-order", method = RequestMethod.POST)
    public String send(ModelMap model, HttpSession session, Principal principal) {


        Order order = (Order) session.getAttribute("order");

        User user = userService.loadByLogin(principal.getName());

        LOG.info(principal.getName());
        //order.setPhone(user.getPhone());
        //orderService.addOrder(order);

        order = new Order();
        order.setProducts(new ArrayList<Product>());

        session.setAttribute("order", order);
        session.setAttribute("pizzasInOrder", order.getProducts(Pizza.class));
        session.setAttribute("additionalInOrder", order.getProducts(Additional.class));

        model.addAttribute("showResult", "success");

        return "index";
    }
}
