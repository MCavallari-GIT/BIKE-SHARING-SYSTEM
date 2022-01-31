package domain;

import java.util.*;

import utils.cod_generator;

/**
 * 
 */
public class morsa {

    /**
     * Default constructor
     */
    public morsa(String tipo,boolean occupata,String r,String bici) {
    	cod_generator cg=new cod_generator();
    	this.id=cg.genera_codice();
    	this.tipo=tipo;
    	this.occupata=occupata;
    	this.rastrelliera=r;
    	this.bici_inserita=bici;
    }
    public morsa() {
    	
    }

    /**
     * 
     */
    private String id;
    private String rastrelliera;
    private String bici_inserita;
    /**
     * 
     */
    private Boolean occupata;

    /**
     * 
     */

    /**
     * 
     */
    private String tipo;



    /**
     * 
     */

    /**
     * @param bicicletta
     */
    public boolean inserisci_bici(bicicletta b) {
        if(this.occupata==false && (this.tipo.equals(b.get_tipo()) || (this.tipo=="elettrica" && b.get_tipo().equals("elettrica_con_seggiolino")))) {
        	return true;
        } else {
        	return false;
        }
    }

    /**
     * 
     */
    public String get_tipo() {
        return tipo;
    }
    public boolean get_occupata() {
        return occupata;
    }

    /**
     * 
     */
    public void set_tipo(String t) {
        this.tipo=t;
    }
    public void set_id(String id) {
        this.id=id;
    }
    public void set_rastrelliera(String rast) {
        this.rastrelliera=rast;
    }
    public void set_bici_inserita(String bici) {
        this.bici_inserita=bici;
    }
    public void set_occupata(boolean occ) {
        this.occupata=occ;
    }

    /**
     * 
     */
    public void sblocca_morsa() {
        occupata=false;
    }

    /**
     * 
     */
    public void inserisci_bici() {
        // TODO implement here
    }

    /**
     * 
     */
    public void blocca_morsa() {
        this.occupata=true;
    }
    public String get_id() {
    	return this.id;
    }
    
    public String get_rastrelliera() {
    	return this.rastrelliera;
    }
    public String get_bici_inserita() {
    	return this.bici_inserita;
    }
}