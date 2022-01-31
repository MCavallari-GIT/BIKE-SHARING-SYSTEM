package domain;

import java.text.ParseException;
import java.time.LocalDate;

import java.util.*;

import utils.cod_generator;
/**
 * 
 */

public class abbonamento {

    /**
     * Default constructor
     */

	

    public abbonamento(durata d,carta_credito carta,boolean is_attivo,int amm) {
    	cod_generator cg=new cod_generator();
    	this.id=cg.genera_codice();
    	this.durata=d;
    	this.carta=carta;
    	if(d.equals(durata.annuale)) {
    		this.costo=36;
    		this.is_attivo=true;
    	}
    	if(d.equals(durata.settimanale)) {
    		this.costo=9;
    		this.is_attivo=false;
    	}
    	if(d.equals(durata.giornaliero)) {
    		this.costo=4.50;
    		this.is_attivo=false;
    	}
    	this.ammonizioni=amm;
    }
    public abbonamento() {
    	
    }
    

    /**
     * 
     */
    private double costo;
    private String id;
    private LocalDate scadenza;
    /**
     * 
     */
 

    /**
     * 
     */
    private boolean is_attivo;
    private durata durata;

    /**
     * 
     */
    private int ammonizioni;
    


    /**
     * 
     */


    /**
     * 
     */
    public carta_credito carta;
    boolean studente;

    /**
     * 
     */

    /**
     * 
     */


    /**
     * 
     */

  
    /**
     * 
     */
    public void ammonisci() {
       this.ammonizioni++;
       if(this.ammonizioni==3) {
    	   this.annulla();
       }
    }

    /**
     * 
     */
    public void annulla() {
        this.is_attivo=false;
    }
    public void set_scadenza(LocalDate s) {
    	this.scadenza=s;
    }

    /**
     * 
     * 
     * 
     */
    public boolean get_attivo() {
    	return this.is_attivo;
    }
    public carta_credito get_carta() {
        return carta;
    }
    public String get_id() {
        return id;
    }
    public int get_ammonizioni() {
        return ammonizioni;
    }
    public double get_costo() {
    	return this.costo;
    }
    
    public durata get_durata() {
        return durata;
    }

    public boolean get_studente() {
        return studente;
    }
    public LocalDate get_scadenza() {
    	return this.scadenza;
    }
    
    public void print_abbonamneto() {
    	System.out.println("ID: "+id+" DURATA: "+durata+" COSTO: "+costo+" AMMONIZIONI: "+ammonizioni+" ATTIVO: "+is_attivo);
    	System.out.println("DATI CARTA ASSOCIATA:");
    	this.carta.print_carta();
    }

    
   public void set_attivo(boolean b) {
	   this.is_attivo=b;
   }
   
   public void set_id(String id) {
	   this.id=id;
   }
   public void set_durata(durata d) {
	   this.durata=d;
   }
   public void set_ammonizioni(int a) {
	   this.ammonizioni=a;
   }
   public void set_carta (carta_credito c) {
	   this.carta=c;
   }
   public void set_costo(double c) {
	   this.costo=c;
   }
}