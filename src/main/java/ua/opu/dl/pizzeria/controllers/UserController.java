package ua.opu.dl.pizzeria.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.opu.dl.pizzeria.model.*;
import ua.opu.dl.pizzeria.service.OrderService;
import ua.opu.dl.pizzeria.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    /**
     * Show registration page
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model) {

        model.addAttribute("user", new User());

        return "user/register";
    }

    /**
     * Add new user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result,
                          ModelMap model, HttpSession session) {

        if (result.hasErrors()) {

            LOG.error("Wrong registration data!");
            model.addAttribute("user", user);

            return "user/register";

        } else {

            LOG.info("Added user with first name: " + user.getFirstName()
                    + ", last name: " + user.getLastName()  );

            //userService.addUser(user);
            session.setAttribute("showResult", "registerSuccess");

            return "redirect:/";
        }
    }

    /**
     * Send order from registered user
     * @param session
     * @param principal
     * @return
     */
    @RequestMapping(value = "/user/send-order", method = RequestMethod.POST)
    public String send(HttpSession session, Principal principal) {

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

        session.setAttribute("showResult", "sendOrderSuccess");

        return "redirect:/";
    }
}