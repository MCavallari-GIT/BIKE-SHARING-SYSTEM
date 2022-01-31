package controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;

import Main.stage;
import data.biciclettaDao;
import data.prelievoDao;
import data.rastrellieraDao;
import data.totemDao;
import data.utenteDao;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class statsController {

    @FXML
    private Label stazione;

    @FXML
    private Label utente;

    @FXML
    private Label prelievo;

    @FXML
    private Label bici;

    @FXML
    void on_indietro(ActionEvent event) throws IOException {
    	FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/personale.fxml"));
        Parent root = loader.load();
        stage.stg.setScene(new Scene(root,600,400));
        stage.stg.setTitle("BIKEMI_PERSONALE");
        stage.stg.show();
    }
    
    @FXML
    void initialize() {
    	totemDao tD=totemDao.get_instance();
    	stazione.setText(Integer.toString(tD.stat_stazioni(tD.connetti())));
    	
    	utenteDao uD=utenteDao.get_instance();
    	utente.setText(Integer.toString(uD.stat_utenti(uD.connetti())));
    	
    	prelievoDao pD=prelievoDao.get_instance();
    	prelievo.setText(pD.stat_prelievi(pD.connetti()));
    	
    	biciclettaDao bD=biciclettaDao.get_instance();
    	bici.setText(bD.stat_bici(bD.connetti()));
    }


}
