package controllers;

import javafx.fxml.FXML;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.util.Objects;

import Main.stage;
import data.abbonamentoDao;
import data.biciclettaDao;
import data.carta_creditoDao;
import data.morsaDao;
import data.prelievoDao;
import data.rastrellieraDao;
import data.totemDao;
import data.utenteDao;
import domain.abbonamento;
import domain.carta_credito;
import domain.durata;
import domain.morsa;
import domain.prelievo;
import domain.utente;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
public class gestione_biciController {
	private ArrayList<String> ID_list_rast=new ArrayList<>();
	ObservableList<String> elenco_stazioni=FXCollections.observableArrayList(ID_list_rast);
	ObservableList<String> tipoBici=FXCollections.observableArrayList("normale","elettrica","elettrica_con_seggiolino");
	@FXML
	private ChoiceBox<String> stazione;
	
    @FXML
    private ChoiceBox<String> bici;

    @FXML
    private Label label;

    @FXML
    void onDeposita(ActionEvent event) throws IOException {
    	//prenedere da prelievo la tupla con fine=null
    	//inserirla nella rastrelliera
    	//chiudere il prelievo
    	String station=stazione.getSelectionModel().getSelectedItem();
    	String user=label.getText();
    	prelievoDao pD=prelievoDao.get_instance();
    	utenteDao uD=utenteDao.get_instance();
    	biciclettaDao bD =biciclettaDao.get_instance();
    	int count=0;
    	boolean occ=false;
    	boolean annulla=false;
    	if(!pD.select_bici_prelevata(pD.connetti(), user).equals("")) {
    		String bici=pD.select_bici_prelevata(pD.connetti(), user);
        	morsaDao mD=morsaDao.get_instance();
        	ArrayList<morsa> morse=mD.select_morse(mD.connetti(), station);
        	double spesa=0;
        	for(int i=0;i<morse.size();i++) {
        		if((morse.get(i).get_tipo().equals(bD.get_bici(bD.connetti(), bici).get_tipo()))  && (morse.get(i).get_occupata()==false ) ) {
        			System.out.print("------------------------------------------------------------");
        			//inserisco nella i-esima morsa la bici
        			morse.get(i).set_bici_inserita(bici);
        			morse.get(i).set_occupata(true);
        			mD.update_morsa(mD.connetti(), morse.get(i), bici);
        			//bisogna calcolare la spesa prima di chiudere prelievo
            		prelievo p=pD.get_prelievo(pD.connetti(), user, bici);
            		p.set_fine(LocalDateTime.now());
            		spesa=p.calcola_spesa(p.get_durata(p.get_inizio(), p.get_fine()));
        			pD.update(pD.connetti(), bici, user);
        			if(p.get_durata(p.get_inizio(), p.get_fine())>120) {
        				annulla=true;
        			}
        			break;
        		} else {
        			count++;
        		}
        	}
        	if(count==morse.size()) {
        		occ=true;
    			Alert alert = new Alert(AlertType.WARNING);
    			alert.setHeaderText("Non c'è spazio per questo tipo in questa stazione");
        		alert.showAndWait();
        	}
        	if(occ==false) {
        		Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("Prelievo concluso. Verranno scalati dalla carta di credito associata al tuo abbonamento "+spesa+" euro");
    			alert.setContentText("Premere OK o X per confermare l'operazione.");
        		alert.showAndWait();
        		//scalo soldi da carta
        		utente u=uD.get_utente(uD.connetti(), user);
        		carta_credito c=u.get_abbobamento().get_carta();
        		System.out.println("------------------------------------"+spesa);
        		System.out.println("------------------------------------"+c.get_saldo());
        		u.get_abbobamento().print_abbonamneto();
        		u.get_abbobamento().get_carta().print_carta();
        		c.set_saldo(c.get_saldo()-spesa);
        		if(c.get_saldo()<0 || annulla==true) {
        			//annullo abbonamneto
        			abbonamentoDao aD=abbonamentoDao.get_instance();
        			aD.delete_abbonamento(aD.connetti(), u);
        			Alert alert2 = new Alert(AlertType.WARNING);
        			alert2.setHeaderText("Il tuo abbonamento è stato eliminato perchè la carta associata non aveva un saldo tale da riuscire a coprire il pagamento");
            		alert2.showAndWait();
            		FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/home.fxml"));
                    Parent root = loader.load();
                    stage.stg.setScene(new Scene(root,600,400));
                    stage.stg.setTitle("BIKEMI");
                    stage.stg.show();
        		}else {
        			carta_creditoDao cD=carta_creditoDao.get_instance();
            		cD.update_saldo(cD.connetti(), c);
        		}
        	}
        	
    	} else {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Non hai alcun prelievo attivo, perciò non hai una bici da depositare");
    		alert.showAndWait();
    	}
    	
    }

    @FXML
    void onIndietro(ActionEvent event) throws IOException {
    	FXMLLoader loader = new	FXMLLoader(getClass().getResource("/views/area_privata.fxml"));
        Parent root = loader.load();

        //The following both lines are the only addition we need to pass the arguments
        area_privataController gC= loader.getController();
        gC.setLabelText(label.getText());


        stage.stg.setScene(new Scene(root,600,400));
        stage.stg.setTitle("Layout2 + Controller2");
        stage.stg.show();
    }

    @FXML
    void onRitira(ActionEvent event) {
    	String user=label.getText();
    	String station=stazione.getSelectionModel().getSelectedItem();
    	String tipo=bici.getSelectionModel().getSelectedItem();
    	//1. selezionare bici di ritirare a schermo
    	//2. salvare il prelievo
    	//3. eliminare la bici dalla morsa
    	
    	//1.
    	totemDao tD=totemDao.get_instance();
    	morsa m=tD.preleva_bici(tD.connetti(), station, tipo);
    	if(Objects.isNull(m)) {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Non sono presenti bici del tipo selezionato alla stazione "+station+" (oppure è presente ma è danneggiata, perciò imprelevabile)");
    		alert.showAndWait();
    	} else {
    		prelievoDao pD=prelievoDao.get_instance();
    		//CONSENTIRE IL PRELIEVO SOLO SE NON NE ESITE UN ALTRO NEL DB NON ANCORA TERMINATO (fine=null) o esiste ma la fine è troppo presto
    		if(!pD.esiste_prelievo_aperto(pD.connetti(), user)	&& pD.check_ultimo_prelievo(pD.connetti(), user)==false) {
    			String bici_da_prelevare=m.get_bici_inserita();
    			biciclettaDao bD =biciclettaDao.get_instance();
    			if(bD.get_bici(bD.connetti(), bici_da_prelevare).getDanneggiata()==false) {
    				System.out.print(pD.check_ultimo_prelievo(pD.connetti(), user));
        			Alert alert = new Alert(AlertType.INFORMATION);
        			alert.setHeaderText("Prelevare bicicletta alla morsa con id "+m.get_id());
            		alert.showAndWait();
            		//prendere utente e bicicletta nella morsa e inserire prelievo
                	
                	utenteDao uD=utenteDao.get_instance();
                	utente u=uD.get_utente(uD.connetti(), user);
                	//se l'abbonamento non è ancora attivo , lo attivo
                	u.print_utente();
                	u.get_abbobamento().print_abbonamneto();;
                	abbonamento a=u.get_abbobamento();
                	a.print_abbonamneto();
                	a.set_attivo(true);
                	if(a.get_durata()==durata.giornaliero) {
                		a.set_scadenza(LocalDate.now().plusDays(1));
                		System.out.println(a.get_scadenza());
                	}
                	if(a.get_durata()==durata.settimanale) {
                		a.set_scadenza(LocalDate.now().plusDays(7));
                	}
                	abbonamentoDao aD=abbonamentoDao.get_instance();
                	aD.update(aD.connetti(), a);
                	prelievo p=new prelievo(u,bD.get_bici(bD.connetti(), bici_da_prelevare),LocalDateTime.now());
                	pD.insert(pD.connetti(), p);
                	m.blocca_morsa();
                	morsaDao mD=morsaDao.get_instance();
                	m.set_occupata(false);
                	m.set_bici_inserita("");
                	mD.update_morsa(mD.connetti(), m, null);
    			} else {
    				Alert alert = new Alert(AlertType.WARNING);
        			alert.setHeaderText("E' presente la bici del tipo selezionato ma sfortunatamente è danneggiata");
            		alert.showAndWait();
    			}
    			
    			
    		} 
    		
        }
    	
    	
    	
    	
    	
    	
    }
    
    @FXML
    void initialize() {
    	stazione.setValue("----------");
    	ArrayList<String> set=null;
    	rastrellieraDao rd=rastrellieraDao.get_instance();
		set=rd.select_id(rd.connetti());
		elenco_stazioni=FXCollections.observableArrayList(set);
		stazione.setItems(elenco_stazioni);
		bici.setItems(tipoBici);
    	bici.setValue("----------");
    }
    public void setLabelText(String text){
        label.setText(text);
    }
}



