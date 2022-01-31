package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javafx.event.*;
import java.io.IOException;
import java.util.ArrayList;

import Main.Main;
import Main.stage;
import javafx.event.ActionEvent;
public class homeController {
	
	private ArrayList<String> ID_list_rast=new ArrayList<>();
    @FXML
    private CheckBox reg;

    @FXML
    private CheckBox tot;

    @FXML
    private CheckBox pers;

    @FXML
    private Button send;

    @FXML
    void clickReg(ActionEvent event) {
    	if(reg.isSelected()) {
    		tot.setSelected(false);
    		pers.setSelected(false);
    	}
    }

    @FXML
    void clickpers(ActionEvent event) {
    	if(pers.isSelected()) {
    		tot.setSelected(false);
    		reg.setSelected(false);
    	}
    }

    @FXML
    void clicksend(ActionEvent event) throws IOException {
    	Main m = new Main();
        if(reg.isSelected()) {
        	FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/registrazione.fxml"));
            Parent root = loader.load();
            stage.stg.setScene(new Scene(root,600,400));
            stage.stg.setTitle("BIKEMI_REGISTRAZIONE");
            stage.stg.show();
        }
        if(pers.isSelected()) {
        	FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/login_personale.fxml"));
            Parent root = loader.load();
            stage.stg.setScene(new Scene(root,600,400));
            stage.stg.setTitle("BIKEMI_RPERSONALE");
            stage.stg.show();
        }
        if(tot.isSelected()) {
        	FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/login.fxml"));
            Parent root = loader.load();
            stage.stg.setScene(new Scene(root,600,400));
            stage.stg.setTitle("BIKEMI_LOGIN");
            stage.stg.show();
        }
    }

    @FXML
    void clicktot(ActionEvent event) {
    	if(tot.isSelected()) {
    		reg.setSelected(false);
    		pers.setSelected(false);
    	}
    }
    
    void set_list(ArrayList<String> t) {
    	this.ID_list_rast=t;
    }
   
}