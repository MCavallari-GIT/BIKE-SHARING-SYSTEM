package controllers;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

import Main.Main;
import Main.stage;
import javafx.event.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
public class area_privataController {
	private String user;
    @FXML
    private CheckBox danno;

    @FXML
    private CheckBox bici;

    @FXML
    private Label label;
    
    @FXML
    void onBici(ActionEvent event) {
    	if(bici.isSelected()) {
    		danno.setSelected(false);
    	}
    }

    @FXML
    void onDanno(ActionEvent event) {
    	if(danno.isSelected()) {
    		bici.setSelected(false);
    	}
    }
    
    @FXML
    void onIndietro(ActionEvent event) throws IOException {
    	FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/login.fxml"));
        Parent root = loader.load();

        //The following both lines are the only addition we need to pass the arguments
        stage.stg.setScene(new Scene(root));
        stage.stg.setTitle("BIKEMI_LOGIN");
        stage.stg.show();
    }

    @FXML
    void onInvia(ActionEvent event) throws IOException {
    	System.out.print("salve "+user);
    	Main m=new Main();
    	if(danno.isSelected()) {
    		FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/area_danno.fxml"));
            Parent root = loader.load();

            //The following both lines are the only addition we need to pass the arguments
            area_dannoController gC= loader.getController();
            gC.setLabelText(label.getText());

            stage.stg.setScene(new Scene(root,600,400));
            stage.stg.setTitle("BIKEMI_SEGNALA_DANNO");
            stage.stg.show();
    	}
    	if(bici.isSelected()) {
        	//m.changeScene("gestione_bici.fxml");
    		FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/gestione_bici.fxml"));
            Parent root = loader.load();
    
            //The following both lines are the only addition we need to pass the arguments
            gestione_biciController gC= loader.getController();
            gC.setLabelText(label.getText());
    
      
            stage.stg.setScene(new Scene(root,600,400));
            stage.stg.setTitle("BIKEMI_GESTIONE_BICI");
            stage.stg.show();
    	}
    }
    @FXML
    void initliazize() {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("salve "+user);
		alert.showAndWait();
    }

    
    public void setLabelText(String text){
        label.setText(text);
    }

}