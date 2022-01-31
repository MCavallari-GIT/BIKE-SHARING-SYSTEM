package domain;


import java.util.*;

import utils.cod_generator;

import java.text.ParseException;
/**
 * 
 */
public class utente {
	
    private String nome;
    private String cognome;
    private abbonamento abb;
    private String cod_utente;
    /**
     * 
     */
    private String password;
    private int num_prelievi;
    private boolean studente;
    /**
     * 
     */
    /**
     * Default constructor
     */
    public utente(String psw,String cod) {
    	this.cod_utente=cod;
    	this.password=psw;
    }
    public utente(String nome,String cognome,String password,boolean s,abbonamento abb) {
    	this.nome=nome;
    	this.cognome=cognome;
    	this.password=password;
    	this.studente=s;
    	this.num_prelievi=0;
    	this.abb=abb;
    	cod_generator cg=new cod_generator();
    	this.cod_utente=cg.genera_codice();
    }
    public utente() {
    	
    }
    


    /**
     * 
     */
 
    public String get_codice() {
        return cod_utente;
    }

    /**
     * 
     */
    public boolean get_studente() {
    	return this.studente;
    }
    public String get_password() {
        return password;
    }
    
    public String get_nome() {
        return nome;
    }
    public String get_cognome() {
        return cognome;
    }
    public String get_psw() {
        return password;
    }
    
    public void set_psw(String psw) {
    	this.password=psw;
    }
    public void set_nome(String nome) {
    	this.nome=nome;
    }
    public void set_cognome(String cognome) {
    	this.cognome=cognome;
    }
    
    public void set_studente(boolean s) {
    	this.studente=s;
    }
    public void set_cod(String cod_utente) {
    	this.cod_utente=cod_utente;
    }
    
    public void set_abbonamento(abbonamento a) {
    	this.abb=a;
    }
    public void add_prelievo() {
    	this.num_prelievi++;
    }
    public int get_prelievi() {
    	return num_prelievi;
    }
    public void print_utente () {
    	System.out.println("NOME: "+nome+" COGNOME: "+cognome+" COD_UTENTE: "+cod_utente+" PASSWORD: "+password+" STUDENTE: "+studente);

    }
    public abbonamento get_abbobamento() {
    	return this.abb;
    }
    public String get_id_abb() {
    	return this.get_abbobamento().get_id();
    }
    
   
    
    public boolean verifica_password(String psw) {
    	int upper_case=0,digit=0;
    	char[] p = psw.toCharArray();
    	for(int i=0;i<psw.length();i++) {
    		if(Character.isDigit(p[i])) {
    			digit++;
    		}
    		if(Character.isUpperCase(p[i])) {
    			upper_case++;
    		}
    	}
    	if(digit>0 && upper_case>0) {
    		return true;
    	}
    	return false;
    }

}