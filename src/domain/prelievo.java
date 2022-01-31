package domain;

import java.util.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.lang.Math;
/**
 * 
 * 
 */
public class prelievo {

    /**
     * Default constructor
     */
	public prelievo(utente u, bicicletta bici,LocalDateTime inizio) {
    	Objects.nonNull(u);
        Objects.nonNull(bici);
        this.user = u;
        this.bici = bici;   
        this.inizio = inizio;
    }

	public prelievo() {
    }
    /**
     * 
     */
    private LocalDateTime inizio;

    /**
     * 
     */
    private LocalDateTime fine;
    private utente user;
    private bicicletta bici;



    /**
     * 
     */

    /**
     * 
     */
    public long get_durata(LocalDateTime inizio,LocalDateTime fine) {
    	long durata =inizio.until(fine, ChronoUnit.MINUTES);
    	if(durata>120) {
    		this.user.get_abbobamento().ammonisci();
    	}
    	if(durata>1440) {//durata>un giorno tolgo 150 euro
    		this.user.get_abbobamento().get_carta().set_saldo(this.user.get_abbobamento().get_carta().get_saldo()-150);
    	}
    	return durata;
    }
    
    
    public LocalDateTime get_inizio() {
    	return this.inizio;
    }
    public LocalDateTime get_fine() {
    	return this.fine;
    }
    public String get_cod_utente() {
    	return this.user.get_codice();
    }
    public String get_bici() {
    	return this.bici.get_id();
    }
    
    public void set_utente(utente u) {
    	this.user=u;
    }
    public void set_bicicletta(bicicletta b) {
    	this.bici=b;
    }
    public void set_inizio(LocalDateTime i) {
    	this.inizio=i;
    }
    public void set_fine(LocalDateTime f) {
    	this.fine=f;
    }
    /**
     * @param durata
     */
    public double calcola_spesa(long durata) {
    	double costo=0;
        String tipo=this.bici.get_tipo();
        if(tipo.equals("normale") && this.user.get_studente()==true) {
        	return 0;
        }
        if(tipo.equals("normale") && durata<=30) {
        	costo= 0;
        }
        if(	(tipo.equals("elettrica") || (tipo.equals("elettrica_con_seggiolino"))) && durata<=30) {
        	costo= 0.25;
        }
        if(tipo.equals("normale") && durata>30 && durata<=60) {
        	costo= 0.50;
        }
        if(	( tipo.equals("elettrica") || (tipo.equals("elettrica_con_seggiolino"))) && durata>30 && durata<=60) {
        	costo= 0.75;
        }
        if(tipo.equals("normale") && durata>=60  & durata<=90) {
        	costo= 1;
        }
        if(	( tipo.equals("elettrica") || (tipo.equals("elettrica_con_seggiolino"))) && durata>=60  & durata<=90) {
        	costo =1.75;
        }
        if(tipo.equals("normale") && durata>=90  & durata<=120) {
        	costo= 1.50;
        }
        if(	( tipo.equals("elettrica") || (tipo.equals("elettrica_con_seggiolino"))) && durata>=90  & durata<=120) {
        	costo= 3.75;
        }
        if(durata>120) {
        	double ore=(double)durata/60;
        	ore-=2;
        	double x=Math.ceil(ore);
        	if(tipo.equals("normale")) {
            	costo= 1.50+(2*x);
            }
        	if(tipo.equals("elettrica") || (tipo.equals("elettrica_con_seggiolino"))) {
            	costo= 3.75+(4*x);
            }
        }
        return costo;
    }
    
    public void print_prelievo() {
    	System.out.println("Inizio: "+inizio+" FINE: "+fine);
    }

}