package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	
//	DELETE
	@PostMapping("/ingredient/delete/{id}")
	public String deletePizza(@PathVariable int id) {
	    // Trova la pizza con l'ID specificato
	    Ingredient ingredients = ingredientService.findById(id);
	    // Elimina la pizza trovata
	    ingredientService.delete(ingredients);
	    // Reindirizza l'utente alla pagina principale
	    return "redirect:/ingredients";
	}
}
