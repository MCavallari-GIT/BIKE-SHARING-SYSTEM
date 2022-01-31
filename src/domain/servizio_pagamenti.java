package domain;

import java.util.*;
import java.util.Date;
import java.time.LocalDate;
/**
 * 
 */
public class servizio_pagamenti {

    /**
     * Default constructor
     */
    public servizio_pagamenti() {
    }

    /**
     * 
     */
    public carta_credito carta;



    /**
     * @param carta
     */
    public boolean verifica(carta_credito carta,String durata) {
        int cod=carta.get_cod();
        if(cod<100 || cod>999) {
        	return false;
        }
        String num=carta.get_numero();
        if(num.length()!=16) {
        	return false;
        }
        if (num == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(num);
        } catch (NumberFormatException nfe) {
            return false;
        }
        LocalDate scadenza=carta.get_scadenza();//scadenza della carta
        LocalDate now=LocalDate.now();
        LocalDate min=null;
        if(durata.equals("giornaliero")) {
                	min=now.plusDays(1);
        }
        if(durata.equals("settimanale")) {
        	min=now.plusDays(7);
        }
        if(durata.equals("annuale")) {
        	min=now.plusDays(365);
        }
        boolean result = scadenza.isAfter(min);
        if (result==false) {
        	return false;
        }
        return true;
    }

    /**
     * 
     */
    public void scala_importo(carta_credito carta,abbonamento abb) {
        double tot=carta.get_saldo();
        if(abb.get_durata().equals(durata.annuale)) {
        	tot-=36;
        }
        if(abb.get_durata().equals(durata.settimanale)) {
        	tot-=9;
        }
        if(abb.get_durata().equals(durata.giornaliero)) {
        	tot-=4.5;
        }
        carta.set_saldo(tot);
    }

}