package org.java.spring.controller;


import org.java.spring.db.pojo.Discount;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.DiscountService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiscountController {
	
	@Autowired 
	private PizzaService pizzaService;
	
	@Autowired 
	private DiscountService discountService;
	
    // Mostra la pagina per creare una nuova offerta
	@GetMapping("/pizzas/{id}/discount")
    public String createDiscount(Model model, @PathVariable int id) {
        
        Pizza pizza = pizzaService.findById(id);
        
        Discount discount = new Discount();
        
        discount.setPizza(pizza);
        
        model.addAttribute("discount", discount);
        
        return "discountCreate";
    }
}
