package controllers;
import javafx.fxml.FXML;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

import Main.stage;
import javafx.event.*;

public class login_personaleController {

    @FXML
    private TextField cod;

    @FXML
    private PasswordField psw;

    @FXML
    void onIndietro(ActionEvent event) throws IOException {
    	FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/home.fxml"));
        Parent root = loader.load();
        stage.stg.setScene(new Scene(root,600,400));
        stage.stg.setTitle("BIKEMI");
        stage.stg.show();
    }

    @FXML
    void onLogin(ActionEvent event) throws IOException {
    	String codice=cod.getText();
    	String password=psw.getText();
    	if(codice.equals("personale1") && password.equals("psw1")) {
    		FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/personale.fxml"));
            Parent root = loader.load();
            stage.stg.setScene(new Scene(root,600,400));
            stage.stg.setTitle("BIKEMI_PERSONALE");
            stage.stg.show();
    	} else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setHeaderText("Dati errati o non presenti, riprova");
    		alert.showAndWait();
    	}
    }

}
