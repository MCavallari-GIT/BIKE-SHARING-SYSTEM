package domain;

import java.util.*;

import utils.cod_generator;

/**
 * 
 */
public abstract class bicicletta {

    /**
     * Default constructor
     */
    public bicicletta() {
    }

    /**
     * 
     */
    private String id;
    private boolean danneggiata;
    private static String tipo;
    
    public bicicletta(boolean danneggiata) {
    	cod_generator cg=new cod_generator();
    	this.id=cg.genera_codice();
		this.danneggiata = danneggiata;
		this.tipo=tipo;
		
	}
    public bicicletta(String id,boolean danneggiata) {
    	cod_generator cg=new cod_generator();
    	this.id=id;
		this.danneggiata = danneggiata;
		this.tipo=tipo;
		
	}

	public String getNumero() {
		return id;
	}

	public void setNumero(String numero) {
		this.id = numero;
	}

	public void setDanneggiata(boolean val) {
		danneggiata = val;
	}

	public boolean getDanneggiata() {
		return danneggiata;
	}
	public void print_info_bici() {
		System.out.println("ID: "+id);
	}
	public String get_id () {
		return this.id;
	}
	public String get_tipo() {
		return this.tipo;
	}
	public boolean get_danneggiata() {
		return this.danneggiata;
	}






}