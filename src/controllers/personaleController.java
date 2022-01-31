package controllers;
import javafx.fxml.FXML;




import javafx.fxml.FXMLLoader;

import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Main.stage;
import data.biciclettaDao;
import data.morsaDao;
import data.rastrellieraDao;
import data.totemDao;
import domain.bici_elettrica;
import domain.bici_elettrica_con_seggiolino;
import domain.bici_normale;
import domain.bicicletta;
import domain.morsa;
import domain.rastrelliera;
import domain.totem;

import java.text.ParseException;
import java.time.LocalTime;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
public class personaleController {
	

	
	
	ObservableList<String> tipoBici=FXCollections.observableArrayList("normale","elettrica","elettrica_con_seggiolino");
	//private ArrayList<totem> list_totem=new ArrayList<>();
	private ArrayList<String> ID_list_rast=new ArrayList<>();
	ObservableList<String> elenco_stazioni=FXCollections.observableArrayList(ID_list_rast);
	
	private ArrayList<String> ID_list_bici=new ArrayList<>();
	ObservableList<String> elenco_bici=FXCollections.observableArrayList(ID_list_bici);
    @FXML
    private Button indietro;
    
    @FXML
    private Button stats;

    @FXML
    private TextField capacita;

    @FXML
    private ChoiceBox<String> id_stazione_r;

    @FXML
    private ChoiceBox<String> tipo_bici;

    @FXML
    private ChoiceBox<String> id_stazione_a;

    @FXML
    private ChoiceBox<String> id_bici_r;

    @FXML
    private ChoiceBox<String> id_bici_s;


    @FXML
    private ChoiceBox<String> id_stazione_s2;

    @FXML
    private Button aggiungi_stazione;

    @FXML
    private Button aggiungi_bici;

    @FXML
    private Button rimuovi_stazione;

    @FXML
    private Button sposta;

    @FXML
    void on_aggiungi_bici(ActionEvent event) {
    	String t=tipo_bici.getSelectionModel().getSelectedItem();
    	String staz=id_stazione_a.getSelectionModel().getSelectedItem();
    	if(t.equals("----------") || staz.equals("----------")) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setHeaderText("ID stazione o tipo bici non validi");
    		alert.showAndWait();
    	} else {
    		rastrellieraDao rD=rastrellieraDao.get_instance();
    		rastrelliera r=rD.find_rast(rD.connetti(), staz);
    		morsaDao mD=morsaDao.get_instance();
    		biciclettaDao bD=biciclettaDao.get_instance();
    		ArrayList<morsa> morse=mD.select_morse(mD.connetti(), r.get_id());
    		r.set_morse(morse);
    		int x=r.slot_libero(t);
    		if(t.equals("normale")) {
    			bici_normale b=new bici_normale(false);
    			if(!r.inserisci_bici(b)) {
    				Alert alert = new Alert(AlertType.WARNING);
    	    		alert.setHeaderText("Spazi esauriti per bici normali");
    	    		alert.showAndWait();
    			} else {
    				System.out.println(morse.get(x).get_occupata()+b.get_id());
    				bD.insert(bD.connetti(), b);
    				mD.update_morsa(mD.connetti(), morse.get(x),b.get_id());
    				Alert alert = new Alert(AlertType.INFORMATION);
    	    		alert.setHeaderText("La bici di tipo NORMALE con id "+b.get_id()+" è stata inserita consuccesso");
    	    		alert.showAndWait();
    			}
    		}
    		if(t.equals("elettrica")) {
    			bici_elettrica b=new bici_elettrica(false);
    			if(!r.inserisci_bici(b)) {
    				Alert alert = new Alert(AlertType.WARNING);
    	    		alert.setHeaderText("Spazi esauriti per bici elettriche");
    	    		alert.showAndWait();
    			}else {
    				bD.insert(bD.connetti(), b);
    				mD.update_morsa(mD.connetti(), morse.get(x),b.get_id());
     				Alert alert = new Alert(AlertType.INFORMATION);
    	    		alert.setHeaderText("La bici di tipo ELETTRICA con id "+b.get_id()+" è stata inserita consuccesso");
    	    		alert.showAndWait();
    			}
    		}
    		if(t.equals("elettrica_con_seggiolino")) {
    			bici_elettrica_con_seggiolino b=new bici_elettrica_con_seggiolino(false);
    			if(!r.inserisci_bici(b)) {
    				Alert alert = new Alert(AlertType.WARNING);
    	    		alert.setHeaderText("Spazi esauriti per bici elettriche con seggiolino");
    	    		alert.showAndWait();
    			}else {
    				bD.insert(bD.connetti(), b);
    				mD.update_morsa(mD.connetti(), morse.get(x),b.get_id());
    				Alert alert = new Alert(AlertType.INFORMATION);
    	    		alert.setHeaderText("La bici di tipo ELETTRICA CON SEGGIOLINO con id "+b.get_id()+" è stata inserita consuccesso");
    	    		alert.showAndWait();
    			}
    		}
			r.print_rastrelliera();
			ArrayList<String> set=null;
    		set=bD.select_id(bD.connetti());
    		elenco_bici=FXCollections.observableArrayList(set);
        	id_bici_r.setItems(elenco_bici);
        	id_bici_s.setItems(elenco_bici);
    	}
    }

    @FXML
    void on_aggiungi_stazione(ActionEvent event) {
    	String cap=capacita.getText();
    	if(cap.equals("")) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setHeaderText("Inserire capacità");
    		alert.showAndWait();
    	} else {
    		try {
    		    int intValue = Integer.parseInt(cap);
    		    rastrelliera r=new rastrelliera(Integer.parseInt(cap)); 
        		totem tot=new totem(r);
        		rastrellieraDao rd=rastrellieraDao.get_instance();
        		rd.insert(rd.connetti(), r);
        		morsaDao mD=morsaDao.get_instance();
        		for(int i=0;i<Integer.parseInt(cap);i++) {
        			mD.insert(mD.connetti(), r.get_morse().get(i));
        		}
        		totemDao td=totemDao.get_instance();
        		td.insert(td.connetti(), tot);
        		ArrayList<String> set=null;
        		set=rd.select_id(rd.connetti());
        		elenco_stazioni=FXCollections.observableArrayList(set);
            	id_stazione_a.setItems(elenco_stazioni);
            	id_stazione_r.setItems(elenco_stazioni);
            	id_stazione_s2.setItems(elenco_stazioni);
            	Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("Stazione con id: "+r.get_id()+" inserita nel db con successo (inserite anche totem associato e morse che la compongono)");
        		alert.showAndWait();
    		} catch (NumberFormatException e) {
    		    Alert alert = new Alert(AlertType.WARNING);
        		alert.setHeaderText("Insisci un numero");
        		alert.showAndWait();
    		}
    		capacita.clear();
    	}
    }

    @FXML
    void on_rimuovi_bici(ActionEvent event) {
    	String bici=id_bici_r.getSelectionModel().getSelectedItem();
    	biciclettaDao bD=biciclettaDao.get_instance();
    	boolean x=bD.delete_bici(bD.connetti(),bici);
    	id_bici_r.setValue("----------");
    	if(x==false) {
    		if(!bici.equals("----------")){
            	Alert alert = new Alert(AlertType.WARNING);
            	alert.setHeaderText("bicicletta con id "+bici+" NON rimossa dal db. AL momento è in utilizzo da un cliente");
        		alert.showAndWait();
        	}
    	} else {
    		ArrayList<String> set=null;
    		set=bD.select_id(bD.connetti());
    		elenco_bici=FXCollections.observableArrayList(set);
        	id_bici_r.setItems(elenco_bici);
        	id_bici_s.setItems(elenco_bici);
    		if(!bici.equals("----------")){
            	Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setHeaderText("bicicletta con id "+bici+"rimossa dal db");
        		alert.showAndWait();
        	}
    	}
    	
    }

    @FXML
    void on_rimuovi_stazione(ActionEvent event) {
    	String stazione=id_stazione_r.getSelectionModel().getSelectedItem();
    	totemDao tD=totemDao.get_instance();
    	tD.delete_stazione(tD.connetti(),stazione);
    	
    	ArrayList<String> set=null;
    	rastrellieraDao rd=rastrellieraDao.get_instance();
		set=rd.select_id(rd.connetti());
		elenco_stazioni=FXCollections.observableArrayList(set);
    	id_stazione_r.setItems(elenco_stazioni);
    	id_stazione_a.setItems(elenco_stazioni);
    	id_stazione_s2.setItems(elenco_stazioni);
    	id_stazione_r.setValue("----------");
    	id_stazione_a.setValue("----------");
    	id_stazione_s2.setValue("----------");
    	if(!stazione.equals("----------")){
        	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("Stazione con id: "+stazione+" rimossa dal db con successo (rimossi anche totem associato e morse che la compongono)");
    		alert.showAndWait();
    	}
    }

    @FXML
    void on_sposta(ActionEvent event) {
    	String bici=id_bici_s.getSelectionModel().getSelectedItem();
    	String nuova_stazione=id_stazione_s2.getSelectionModel().getSelectedItem();
    	if(!bici.equals("----------") && !nuova_stazione.equals("----------")) {
    		biciclettaDao bD=biciclettaDao.get_instance();
        	if(!bD.is_presente(bD.connetti(), bici, nuova_stazione)) {
        		bicicletta b=bD.get_bici(bD.connetti(),bici);
        		if(b!=null) {
        			b.print_info_bici();
            		rastrellieraDao rD=rastrellieraDao.get_instance();
                	rastrelliera r=rD.find_rast(rD.connetti(), nuova_stazione);
                	morsaDao mD=morsaDao.get_instance();
            		ArrayList<morsa> morse=mD.select_morse(mD.connetti(), r.get_id());
            		r.set_morse(morse);
            		System.out.println("-------------------------------------------------------");
                	r.print_rastrelliera();
                	//INSERISCO BICI NELLA NUOVA POSTAZIONE
                	int x=r.slot_libero(b.get_tipo());
            		if(!r.inserisci_bici(b)) {
            			Alert alert = new Alert(AlertType.WARNING);
                		alert.setHeaderText("Spazi esauriti per queste tipologie di biciclette");
                		alert.showAndWait();
            		} else {
            			
            			if(x!=-1) {
            				morse.get(x).set_occupata(true);
            				mD.update_morsa(mD.connetti(), morse.get(x),b.get_id());
                    		//ELIMINO BICI DALLA POSTAZIONE VECCHIA
                    		bD.delete_bici_from_rast(bD.connetti(), bici, nuova_stazione);
            			} else {
            				System.out.println("NON CE SLOT LIBERO");
            			}
            			
            		}

        		} else {
        			Alert alert2 = new Alert(AlertType.WARNING);
            		alert2.setHeaderText("Le bici danneggiate non possono essere spostate");
            		alert2.showAndWait();
        		}
            	
        	
        	}
    	}
    	
    	
    	
    }

    @FXML
    void on_stats(ActionEvent event) throws IOException {
    	FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/stats.fxml"));
        Parent root = loader.load();
        stage.stg.setScene(new Scene(root,600,400));
        stage.stg.setTitle("BIKEMI_PERSONALE");
        stage.stg.show();
    }
    @FXML
    void initialize() {
    	tipo_bici.setItems(tipoBici);
    	tipo_bici.setValue("----------");
    	
    	id_stazione_a.setValue("----------");
    	ArrayList<String> set=null;
    	rastrellieraDao rd=rastrellieraDao.get_instance();
		set=rd.select_id(rd.connetti());
		elenco_stazioni=FXCollections.observableArrayList(set);
    	id_stazione_a.setItems(elenco_stazioni);
    	
    	
    	id_stazione_s2.setValue("----------");
		set=rd.select_id(rd.connetti());
		elenco_stazioni=FXCollections.observableArrayList(set);
		id_stazione_s2.setItems(elenco_stazioni);
    	
    	id_bici_r.setValue("----------");
    	biciclettaDao bd=biciclettaDao.get_instance();
		set=bd.select_id(bd.connetti());
		elenco_bici=FXCollections.observableArrayList(set);
		id_bici_r.setItems(elenco_bici);
		
		id_bici_s.setValue("----------");
		set=bd.select_id(bd.connetti());
		elenco_bici=FXCollections.observableArrayList(set);
		id_bici_s.setItems(elenco_bici);
		
		id_stazione_r.setValue("----------");
		set=rd.select_id(rd.connetti());
		elenco_stazioni=FXCollections.observableArrayList(set);
    	id_stazione_r.setItems(elenco_stazioni);
    }


    @FXML
    void on_indietro(ActionEvent event) throws IOException {
    	FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/login_personale.fxml"));
        Parent root = loader.load();
        stage.stg.setScene(new Scene(root,600,400));
        stage.stg.setTitle("BIKEMI_PERSONALE");
        stage.stg.show();
    }
    
    void set_list(ArrayList<String> t) {
    	this.ID_list_rast=t;
    }
}
