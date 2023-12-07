package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Discount;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class MainController {

	@Autowired
	private PizzaService pizzaService;

	// Questo metodo gestisce le richieste GET sulla radice dell'applicazione
	@GetMapping
	public String getPizza(Model model,
			@RequestParam(required = false) String p) {
		
		List<Pizza> pizzas = p == null  
					? pizzaService.findAll()
					: pizzaService.findByName(p);
		
		model.addAttribute("pizze", pizzas);
		model.addAttribute("p", p == null ? "" : p);
		
		return "index";
	}
	
	// Questo metodo gestisce le richieste GET per visualizzare i dettagli di una pizza specifica
	@GetMapping("/pizza/{id}")
	public String getPizzaId(Model model,
			@PathVariable int id) {
		
		// Recupera una pizza specifica dal servizio utilizzando l'ID fornito
		Pizza pizzaId = pizzaService.findById(id);
		
	    // Recupera gli sconti applicati a questa pizza
	    List<Discount> discounts = pizzaId.getDiscount();
		
		// Aggiunge la pizza al modello per renderla disponibile nella vista
		model.addAttribute("pizza", pizzaId);
		
		model.addAttribute("discounts" , discounts);
		
	
		
		// Restituisce il nome della vista da visualizzare ("pizzaShow" in questo caso)
		return "pizzaShow";
	}
	
	
	// Questo metodo gestisce le richieste GET a "/pizza/create"
	// Crea un nuovo oggetto Pizza e lo aggiunge al modello
	@GetMapping("/pizza/create")
	public String createPizza(Model model) {
	    Pizza pizza = new Pizza();
	    model.addAttribute("pizza", pizza);
	    return "pizzaCreate";
	}

	// Questo metodo gestisce le richieste POST a "/pizza/create"
	// Valida l'oggetto Pizza, lo salva se non ci sono errori, altrimenti ritorna alla pagina di creazione
	@PostMapping("/pizza/create")
	public String storePizza(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        System.out.println(bindingResult);
	        model.addAttribute("pizza", pizza);
	        return "pizzaCreate";
	    }
	    pizzaService.save(pizza);
	    return "redirect:/";
	}

	// Questo metodo gestisce le richieste GET a "/pizza/edit/{id}"
	// Recupera la pizza con l'ID specificato e la aggiunge al modello
	@GetMapping("/pizza/edit/{id}")
	public String editPizza (Model model, @PathVariable int id) {
	    Pizza pizza = pizzaService.findById(id);
	    model.addAttribute("pizza", pizza);
	    return "pizzaCreate";
	    
	}

	// Questo metodo gestisce le richieste POST a "/pizza/edit/{id}"
	// Valida l'oggetto Pizza e lo passa al metodo storePizza per il salvataggio
	@PostMapping("/pizza/edit/{id}")
	public String updatePizza(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {
	    // Il metodo storePizza viene riutilizzato per salvare la pizza modificata
	    // Se ci sono errori di validazione, l'utente verrà reindirizzato alla pagina di creazione con i dettagli dell'errore
	    return storePizza(model, pizza, bindingResult);
	}

	// Questo metodo gestisce le richieste POST a "/pizza/delete/{id}"
	// Trova la pizza con l'ID specificato e la elimina
	@PostMapping("/pizza/delete/{id}")
	public String deletePizza(@PathVariable int id) {
	    // Trova la pizza con l'ID specificato
	    Pizza pizza = pizzaService.findById(id);
	    // Elimina la pizza trovata
	    pizzaService.delete(pizza);
	    // Reindirizza l'utente alla pagina principale
	    return "redirect:/";
	}
}
