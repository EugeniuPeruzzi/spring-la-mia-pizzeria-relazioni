package org.java.spring.controller;


import org.java.spring.db.pojo.Discount;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.DiscountService;
import org.java.spring.db.serv.PizzaService;
import org.java.spring.dto.DiscountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


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
	
	@PostMapping("/pizzas/{id}/discount")
    public String storeDiscount(@ModelAttribute DiscountDTO discountDTO, @PathVariable int id) {
        
        Pizza pizza = pizzaService.findById(id);
        
        Discount discount = new Discount(discountDTO.getTitolo(), discountDTO.getDataDiInizio(), discountDTO.getDataDiFine(), pizza);
        
        discountService.save(discount);
        
        return "redirect:/";
    }
}
