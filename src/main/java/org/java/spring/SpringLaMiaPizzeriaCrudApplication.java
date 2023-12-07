package org.java.spring;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

    @Autowired
    private PizzaService pizzaService;

    public static void main(String[] args) {
        SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // In questo metodo, vengono eseguite operazioni una volta che l'applicazione è stata avviata

        // Crea e salva oggetti Pizza nel database al momento dell'avvio dell'applicazione
    	pizzaService.save(new Pizza("Margherita", "Descrizione 1", "https://picsum.photos/400/400", 9.3));
        pizzaService.save(new Pizza("Pepperoni", "Descrizione 2", "https://picsum.photos/400/400", 10.5));
        pizzaService.save(new Pizza("Siciliana", "Descrizione 3", "https://picsum.photos/400/400", 11.7));
        pizzaService.save(new Pizza("Quattro Stagioni", "Descrizione 4", "https://picsum.photos/400/400", 12.9));
        pizzaService.save(new Pizza("Capricciosa", "Descrizione 5", "https://picsum.photos/400/400", 13.1));
        pizzaService.save(new Pizza("Marinara", "Descrizione 6", "https://picsum.photos/400/400", 8.2));
        pizzaService.save(new Pizza("Napoletana", "Descrizione 7", "https://picsum.photos/400/400", 9.4));
    }
}
