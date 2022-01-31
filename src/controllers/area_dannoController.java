package controllers;



import javafx.fxml.FXML;



import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.util.ArrayList;

import Main.stage;
import data.biciclettaDao;
import data.dannoDao;
import domain.danno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;



public class area_dannoController {
	private ArrayList<String> ID_list_bici=new ArrayList<>();
	ObservableList<String> elenco_bici=FXCollections.observableArrayList(ID_list_bici);
	
    @FXML
    private Label label;
    
    @FXML
    private ChoiceBox<String> id_bici;

    @FXML
    private TextArea danno;
    
    @FXML
    private ChoiceBox<?> stazione;

    @FXML
    void onIndietro(ActionEvent event) throws IOException {
    	FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/area_privata.fxml"));
        Parent root = loader.load();

        //The following both lines are the only addition we need to pass the arguments
        area_privataController gC= loader.getController();
        gC.setLabelText(label.getText());
        stage.stg.setScene(new Scene(root,600,400));
        stage.stg.setTitle("BIKEMI_AREA_PRIVATA");
        stage.stg.show();
    }

    @FXML
    void onInvia(ActionEvent event) {
    	String bici=id_bici.getSelectionModel().getSelectedItem();
    	String d="";
    	if(!bici.equals("----------")){
    		d=danno.getText();
        	dannoDao dD=dannoDao.get_instance();
            danno dannoObj=new danno(bici,d);
            dD.insert(dD.connetti(), dannoObj);   
            biciclettaDao bD =biciclettaDao.get_instance();
            bD.update(bD.connetti(), true, bici);
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("Danno inserito correttamento nel db");
    		alert.showAndWait();
    		id_bici.setValue("----------");
    		danno.clear();
    	}
    	
    }
    
    @FXML
    void initialize() {
    	id_bici.setValue("----------");
    	biciclettaDao bD =biciclettaDao.get_instance();
    	ArrayList<String> set=null;
		set=bD.select_id(bD.connetti());
		elenco_bici=FXCollections.observableArrayList(set);
		id_bici.setItems(elenco_bici);
    }
    
    public void setLabelText(String text){
        label.setText(text);
    }

}