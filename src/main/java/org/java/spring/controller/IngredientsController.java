package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.serv.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IngredientsController {

	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping("/ingredients")
    public String createDiscount(Model model) {
        

        List<Ingredient> ingredients = ingredientService.findAll();
        
        model.addAttribute("ingredients", ingredients);
        
        return "ingredientShow";
    }
}
