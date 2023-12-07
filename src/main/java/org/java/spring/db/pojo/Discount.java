package org.java.spring.db.pojo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Discount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	private String titolo;
    private LocalDate dataDiInizio;
    private LocalDate dataDiFine;

    
    @ManyToOne
    private Pizza pizza;
    
	public Discount() { }
	public Discount(String titolo, LocalDate dataDiInizio, LocalDate dataDiFine, Pizza pizza) {
		setTitolo(titolo);
		setDataDiInizio(dataDiInizio);
		setDataDiFine(dataDiFine);
		setPizza(pizza);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public LocalDate getDataDiInizio() {
		return dataDiInizio;
	}
	public void setDataDiInizio(LocalDate dataDiInizio) {
		this.dataDiInizio = dataDiInizio;
	}
	public LocalDate getDataDiFine() {
		return dataDiFine;
	}
	public void setDataDiFine(LocalDate dataDiFine) {
		this.dataDiFine = dataDiFine;
	}
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	@Override
	public String toString() {
	    return "Discount " 
	    		+ ", titolo=" + getTitolo() 
	    		+ ", dataDiInizio=" + getDataDiInizio()
	            + ", dataDiFine=" + getDataDiFine() 
	    		+ ", pizza=" + getPizza() + "]";
	}
	
	

	
    
}