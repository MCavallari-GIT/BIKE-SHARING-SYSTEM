package domain;

import java.util.*;

import utils.cod_generator;

/**
 * 
 */
public class rastrelliera {

    /**
     * Default constructor
     */
	
	
	
    public rastrelliera(int capacita) {
    	cod_generator cg=new cod_generator();
    	this.id=cg.genera_codice();
    	this.capacita=capacita;
    	for(int i=0;i<capacita;i++) {
    		if(i<capacita/2) {
    			morse.add(new morsa("normale",false,this.id,""));
    		} else if(i>=capacita/2 && i<=capacita*3/4){
    			morse.add(new morsa("elettrica",false,this.id,""));
    		} else {
    			morse.add(new morsa("elettrica_con_seggiolino",false,this.id,""));
    		}
    	}
    }
    
    public rastrelliera() {
    	
    }

    /**
     * 
     */
    private  String id;

    /**
     * 
     */
    private int capacita;
    
    private ArrayList<morsa> morse=new ArrayList<>();







    /**
     * 
     */
    public int aggiorna_morse_disponibili() {
    	int c=0;
        for(int i=0;i<capacita;i++) {
        	if(morse.get(i).get_occupata()==true) {
        		continue;
        	} else {
        		c++;
        	}
        }
        return c;
    }

    /**
     * 
     */
    public int check_morse_disponibili() {
    	int c=0;
        for(int i=0;i<capacita;i++) {
        	if(morse.get(i).get_occupata()) {
        		continue;
        	} else {
        		c++;
        	}
        }
        return c;
    }

    /**
     * @param tipo
     */
    public boolean verifica_disponibilita_bici(String tipo) {
    	for(int i=0;i<capacita;i++) {
    		if(morse.get(i).get_occupata()==true && morse.get(i).get_tipo().equals(tipo)) {
        		return true;
        	}
        }
    	return false;
    }
    


    /**
     * @param tipo
     */
    public int verifica_disponibilità_spazio(String tipo) {
    	int c=0;
        for(int i=0;i<capacita;i++) {
        	if(morse.get(i).get_occupata()==false && morse.get(i).get_tipo().equals(tipo)) {
        		c++;
        	}
        }
        return c;
    }
    
    public int slot_libero (String tipo) {
    	for(int i=0;i<capacita;i++) {
    		if(morse.get(i).get_occupata()==false && morse.get(i).get_tipo().equals(tipo)) {
        		return i;
        	}
        }
    	return -1;
    }
    
    //restituisce indice rastrelliera con bici del tipo passato come parametro
    public int slot_occupato (String tipo) {
    	for(int i=0;i<capacita;i++) {
    		if(morse.get(i).get_occupata()==true && morse.get(i).get_tipo().equals(tipo)) {
        		return i;
        	}
        }
    	return -1;
    }
    
    public void print_rastrelliera() {
    	System.out.println(this.id);
    	for(int i=0;i<capacita;i++) {
    		if(morse.get(i).get_occupata()) {
    			System.out.println("OCCUPATA");
    		}else {
    			System.out.println("LIBERA");
    		}
    		System.out.println("	"+morse.get(i).get_tipo());
    	}
    	
    	System.out.println("MORSE DISPONIBILI: "+this.aggiorna_morse_disponibili());
    }
    
    public boolean inserisci_bici(bicicletta b) {
    	int x=this.slot_libero(b.get_tipo());
    	if(x!=-1) {
    		this.morse.get(x).blocca_morsa();
    		this.aggiorna_morse_disponibili();
    		return true;
    	}
    	return false;
    }
    
    public void preleva_bici(int num_morsa) {
    	morsa x= morse.get(num_morsa);
    	x.sblocca_morsa();
    }
    
    public String get_id() {
    	return this.id;
    }
    public int get_capacita() {
    	return this.capacita;
    }
    
    public ArrayList<morsa> get_morse(){
    	return this.morse;
    }
    
    
    public void set_id(String id) {
    	this.id=id;
    }
    public void set_capacita(int c) {
    	this.capacita=c;
    }
    public void set_morse(ArrayList <morsa> lista_morse) {
    	for(int i=0;i<lista_morse.size();i++) {
    		morse.add(lista_morse.get(i));
    	}
     }
}