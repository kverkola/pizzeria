package ua.opu.dl.pizzeria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/ingredients")
public class IngredientsController {

	
	
	
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public String getCount(
			ModelMap model) {
		
		
		
		
	
	return "redirect:addIngredients";
}}
