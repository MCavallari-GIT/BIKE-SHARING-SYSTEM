package controllers;
import javafx.fxml.FXML;




import javafx.fxml.FXMLLoader;



import java.io.IOException;
import java.time.LocalDate;
import java.time.*;
import java.util.ArrayList;

import Main.stage;
import data.abbonamentoDao;
import data.carta_creditoDao;
import data.utenteDao;
import domain.abbonamento;
import domain.carta_credito;
import domain.durata;
import domain.servizio_pagamenti;
import domain.utente;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.event.*;
//import java.awt.event.MouseEvent;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class regController {
	
	private ArrayList<String> ID_list_rast=new ArrayList<>();
	ObservableList<String> tipoList=FXCollections.observableArrayList("giornaliero","settimanale","annuale");
	
    @FXML
    private Button indietro;

    @FXML
    private TextField nome;

    @FXML
    private TextField cognome;

    @FXML
    private PasswordField password;

    @FXML
    private CheckBox studente;

    @FXML
    private TextField numero;

    @FXML
    private TextField codice;

    @FXML
    private DatePicker scadenza;

    @FXML
    private ChoiceBox<String> tipo;

    @FXML
    private Button invia;

    @FXML
    void onCodice(ActionEvent event) {

    }

    @FXML
    void onCognome(ActionEvent event) {

    }

    @FXML
    void onIndietro(ActionEvent event) throws IOException {
    	FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/home.fxml"));
        Parent root = loader.load();
        stage.stg.setScene(new Scene(root,600,400));
        stage.stg.setTitle("BIKEMI");
        stage.stg.show();
    }

    @FXML
    void onInvia(ActionEvent event) throws IOException {
    	String n=nome.getText();
    	String cog=cognome.getText();
    	String psw=password.getText();
    	Boolean b=studente.isSelected();
    	String num=numero.getText();
    	String cod=codice.getText();
    	LocalDate d=scadenza.getValue();
    	String t=tipo.getSelectionModel().getSelectedItem();
    	if(n.equals("") || cog.equals("") || psw.equals("") || num.equals("") || cod.equals("") || d==null || t.equals("----------")) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setHeaderText("Mancano alcuni campi obbligatori");
    		alert.showAndWait();
    	} else {
    		int cod_int=Integer.parseInt(cod);
    		carta_credito carta=new carta_credito(num,cod_int,d,100);
    		durata dur=durata.giornaliero;
    		boolean attivo=false;
    		if(t.equals("annuale")) {
    			dur=durata.annuale;
    			attivo=true;
    		}
    		if(t.equals("settimanale")) {
    			dur=durata.settimanale;
    			attivo=false;
    		}
    		if(t.equals("giornaliero")) {
    			dur=durata.giornaliero;
    			attivo=false;
    		}
    		abbonamento abb=new abbonamento(dur,carta,attivo,0);
    		if(abb.get_durata()==durata.annuale) {
    			abb.set_scadenza(LocalDate.now().plusYears(1));
    		}
    		servizio_pagamenti s=new servizio_pagamenti();
    		boolean verifica=s.verifica(carta,t);
    		if(!verifica) {
    			Alert alert = new Alert(AlertType.WARNING);
    			alert.setHeaderText("Dati carta non corretti (il numero deve essere di 16 cifre, il codice di 3 e la scadenza deve essere valida");
        		alert.showAndWait();
    		} else {
    			utente u=new utente(n,cog,psw,b,abb);
    			boolean psw_corretta=u.verifica_password(u.get_password());
    			if(!psw_corretta) {
    				Alert alert = new Alert(AlertType.WARNING);
        			alert.setHeaderText("Password non accettabile (almeno un numero,una lettera maiuscola");
            		alert.showAndWait();
    			} else {
    				double x;
        			x=carta.get_saldo();
        			x-=abb.get_costo();
        			carta.set_saldo(x);
        			carta_creditoDao cD=carta_creditoDao.get_instance();
        			if(!cD.check_presenza(cD.connetti(), carta)) {
        				cD.insert(cD.connetti(), carta);
        			}
        			abbonamentoDao aD=abbonamentoDao.get_instance();
        			aD.insert(aD.connetti(), abb);
        			utenteDao uD=utenteDao.get_instance();
        			uD.insert(uD.connetti(), u);
        			Alert alert = new Alert(AlertType.INFORMATION);
        			alert.setHeaderText("Registrazione avvenuta. Ecco il tuo codice utente:		"+u.get_codice());
        			alert.setContentText("Premere OK o X per confermare l'operazione.");
            		alert.showAndWait();
            		FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/home.fxml"));
                    Parent root = loader.load();
                    stage.stg.setScene(new Scene(root,600,400));
                    stage.stg.setTitle("BIKEMI");
                    stage.stg.show();
    			}		
    		}
    	}
    }

    @FXML
    void initialize() {
    	tipo.setItems(tipoList);
    	tipo.setValue("----------");
    }
    
   /* @FXML
    void on_stats(ActionEvent event) {

    }*/
   
    /*public void set_rast(ArrayList<String> rastrelliere_prec) {
		this.ID_list_rast=rastrelliere_prec;
	}*/


}
