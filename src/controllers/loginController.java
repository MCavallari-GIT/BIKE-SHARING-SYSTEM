package controllers;
import javafx.fxml.FXML;
import domain.utente;

import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

import Main.stage;
import data.abbonamentoDao;
import data.utenteDao;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;


public class loginController {


    @FXML
    private TextField cod;

    @FXML
    private PasswordField psw;
    
    @FXML
    private Label sid_stazione;

    @FXML
    void onIndietro(ActionEvent event)throws IOException {
    	FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/home.fxml"));
        Parent root = loader.load();
        stage.stg.setScene(new Scene(root,600,400));
        stage.stg.setTitle("BIKEMI");
        stage.stg.show();
    }

    @FXML
    void onLogin(ActionEvent event) throws IOException{
    	String cod_utente=cod.getText();
    	String password=psw.getText();
    	if(cod_utente.equals("") || password.equals("")) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setHeaderText("Codice utente o password mancanti");
    		alert.showAndWait();
    	} else {
    		utenteDao uD=utenteDao.get_instance();
    		abbonamentoDao aD=abbonamentoDao.get_instance();
    		boolean pres_utente=uD.check_presenza(uD.connetti(), cod_utente, password);
    		boolean abbonamento_attivo=aD.check_presenza(aD.connetti(), cod_utente);
    		if(pres_utente==true && abbonamento_attivo==true) {
    			Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setHeaderText("login avvenuto con successo");
        		alert.showAndWait();
        		/*Main m=new Main();
        		m.changeScene("area_privata.fxml");*/
        		FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/area_privata.fxml"));
                Parent root = loader.load();
        
                //The following both lines are the only addition we need to pass the arguments
                area_privataController aC= loader.getController();
                aC.setLabelText(cod_utente);
        
                
                stage.stg.setScene(new Scene(root,600,400));
                stage.stg.setTitle("BIKEMI_AREA_PRIVATA");
                stage.stg.show();
                
    		} else {
    			Alert alert = new Alert(AlertType.WARNING);
        		alert.setHeaderText("utente non presente nel database");
        		alert.showAndWait();
        		if(abbonamento_attivo==false && pres_utente==true) {
        			//annullo abbonamneto
        			utente u=uD.get_utente(uD.connetti(), cod_utente);
        			aD.delete_abbonamento(aD.connetti(), u);
        		}
    		}
    	}
    }


}
