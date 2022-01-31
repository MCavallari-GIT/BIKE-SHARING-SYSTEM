package domain;

import java.util.*;



import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.Scanner;
/**
 * 
 * 
 */
public class carta_credito {
    private String numero;

    /**
     * 
     */
    private int cod;

    /**
     * 
     */
    private LocalDate scadenza;

    /**
     * 
     */
    private double saldo;

    /**
     * 
     */
    /**
     * Default constructor
     */
    public carta_credito(String numero,int cod,LocalDate scadenza,double saldo) {
    	this.numero=numero;
    	this.cod=cod;
    	this.scadenza=scadenza;
    	this.saldo=saldo;	
    }
    
    public carta_credito() {
    	
    }
    /**
     * 
     */
    public String get_numero() {
    	return numero;
    }
    
    public int get_cod() {
    	return cod;
    }
    
    public LocalDate get_scadenza() {
    	return scadenza;
    }
    
    public double get_saldo() {
    	return saldo;
    }
    public void set_saldo(double saldo) {
    	this.saldo=saldo;
    }
    public void set_numero(String n) {
    	this.numero=n;
    }
    
    public void set_cod(int c) {
    	this.cod=c;
    }
    public void set_scadenza(LocalDate s) {
    	this.scadenza=s;
    }
    public void print_carta() {
    	System.out.println("NUMERO: "+numero+" CODICE: "+cod+" SCADENZA: "+scadenza+" SALDO: "+saldo);;
    }

   // public abbonamento abb;

    /**
     * 
     */
    //public servizio_pagamenti servizio_p;

}