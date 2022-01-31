package Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	//private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage.stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
        primaryStage.setTitle("BIKEMI");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
	 public static void main(String[] args) {
	        launch(args);
	 }
}












/*SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
carta_credito c=new carta_credito("1234567890123456",511,sdf.parse("15-12-2021"),1000);
carta_creditoDao cD=new carta_creditoDao();
cD.insert(cD.connetti(), c);
servizio_pagamenti s =new servizio_pagamenti();
boolean x=s.verifica(c);
if(x) {
	abbonamento a=new abbonamento(durata.annuale,c);
	utente u=new utente("Marco","Cavallari","Ciao123","ABCDE12",true,a);
	s.scala_importo(c, a);
	a.print_abbonamneto();
	utenteDao uDao = new utenteDao();
	uDao.insert(uDao.connetti(), u);
	abbonamentoDao aDao = new abbonamentoDao();
	aDao.insert(aDao.connetti(), a);
	cD.update_saldo(cD.connetti(),c);
}*/
/*servizio_pagamenti s =new servizio_pagamenti();
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
utente u=new utente("Marco","Cavallari","sffs","0M8Z8O68YD");
carta_credito c=new carta_credito("1234567890123456",511,sdf.parse("15-12-2021"),1000);
abbonamento a=new abbonamento(durata.annuale,u,c,true);
utenteDao uDao = new utenteDao();
uDao.insert(uDao.connetti(), u);
carta_creditoDao cD=new carta_creditoDao();
cD.insert(cD.connetti(), c);
abbonamentoDao aDao = new abbonamentoDao();
aDao.insert(aDao.connetti(), a);
s.scala_importo(c, a);
cD.update_saldo(cD.connetti(),c);
rastrelliera r=new rastrelliera(5);
totem t=new totem(r);
t.login(u);/*


/*morsa m=new morsa("elettrica",false);
bici_elettrica_con_seggiolino b=new bici_elettrica_con_seggiolino(false);
b.print_info_bici();

System.out.println(m.inserisci_bici(b));*/





/*bici_elettrica_con_seggiolino b1=new bici_elettrica_con_seggiolino(false);
bici_normale b2=new bici_normale(false);
bici_elettrica b3=new bici_elettrica(false);
bici_elettrica b5=new bici_elettrica(false);
bici_normale b4=new bici_normale(false);
b1.print_info_bici();
b2.print_info_bici();






rastrelliera r=new rastrelliera(5);
r.print_rastrelliera();
//r.inserisci_bici(b3);
System.out.println("------------INSERISCO 2 BICI-------------");
r.inserisci_bici(b2);
//r.inserisci_bici(b1);
r.inserisci_bici(b3);
//r.inserisci_bici(b4);
//r.inserisci_bici(b5);
//r.print_rastrelliera();
r.print_rastrelliera();
System.out.println("--------------UTENTE VUOLE PRELEVARE LA BICI------------");
totem t=new totem(r);
utente u=new utente("Marco","Cavallari","sffs","0M8Z8O68YD");
t.login(u);
//LOGIN è ANDATO
String tipo=t.scegli_bici("elettrica");
if(t.get_rastrelliera().verifica_disponibilita_bici(tipo)) {
	int x=t.get_rastrelliera().slot_occupato(tipo);
	System.out.println("PRELEVARE BICI ALLA POSTAZIONE "+(x+1));
	t.get_rastrelliera().preleva_bici(x);
	t.get_rastrelliera().print_rastrelliera();
} else {
	System.out.println("NON E' STATA TROVATA ALCUNA BICICLETTA DEL SEGUENTE TIPO: "+tipo);
}

System.out.println("-----------L'UTENTE STA DEPOSITANDO LA BICICLETTA PRECEDENTEMENTE PRELEVATA--------");
t.get_rastrelliera().inserisci_bici(b2);
t.get_rastrelliera().print_rastrelliera();*/



/*bici_normale b1=new bici_normale(false);
rastrelliera r=new rastrelliera(5);
r.inserisci_bici(b1);
totem t=new totem(r);
t.get_rastrelliera().print_rastrelliera();
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
carta_credito c=new carta_credito("1234567890123456",511,sdf.parse("15-12-2021"),1000);
abbonamento a=new abbonamento(durata.annuale,c,true,0);
utente u=new utente("Marco","Cavallari","Ciao123","ABCDE12",true,a);
boolean y=t.login(u);
prelievo p=null;
if(y) {
	String tipo=t.scegli_bici("normale");
	if(t.get_rastrelliera().verifica_disponibilita_bici(tipo)) {
		int x=t.get_rastrelliera().slot_occupato(tipo);
		System.out.println("PRELEVARE BICI ALLA POSTAZIONE "+(x+1));
		t.get_rastrelliera().preleva_bici(x);
		t.get_rastrelliera().print_rastrelliera();
		//ORA L'UTENTE HA LA BICI
		if(!u.get_abbobamento().get_attivo()) {
			u.get_abbobamento().set_attivo();
		}
		p=new prelievo(u,b1,LocalTime.now());
	} else {
		System.out.println("NON E' STATA TROVATA ALCUNA BICICLETTA DEL SEGUENTE TIPO: "+tipo);
	}

}
System.out.println("-----------L'UTENTE STA DEPOSITANDO LA BICICLETTA PRECEDENTEMENTE PRELEVATA--------");
t.get_rastrelliera().inserisci_bici(b1);
p.set_fine(LocalTime.of(18, 40, 45, 342123342));
p.print_prelievo();
System.out.println(p.get_durata(p.get_inizio(), p.get_fine()));
System.out.println(p.calcola_spesa(p.get_durata(p.get_inizio(), p.get_fine())));
if(p.get_durata(p.get_inizio(), p.get_fine())>120) {
	u.get_abbobamento().add_ammonizione();
}*/
/*personale p2=new personale();
totem t2=p2.aggiungi_stazione(2);
//p2.delete_stazione(t2);
bici_normale b=p2.aggiungi_bici_normale(t2.get_rastrelliera());
t2.get_rastrelliera().print_rastrelliera();

totem t3=p2.aggiungi_stazione(3);
t3.get_rastrelliera().print_rastrelliera();



System.out.println("scambioooooooooooooooooooooooooooooooo");
p2.sposta_bicicletta(b, t2.get_rastrelliera(), t3.get_rastrelliera());
t2.get_rastrelliera().print_rastrelliera();
t3.get_rastrelliera().print_rastrelliera();*/

/*SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
carta_credito c=new carta_credito("1234567890123459",511,sdf.parse("15-12-2021"),1000);
carta_creditoDao cD=new carta_creditoDao();
cD.insert(cD.connetti(), c);
abbonamento a=new abbonamento(durata.settimanale,c,false,0);
a.print_abbonamneto();
abbonamentoDao aDao = new abbonamentoDao();
aDao.insert(aDao.connetti(), a);
utente u=new utente("Lorenzo","Mazzi","Casa184","ABCDF12",true,a);
utenteDao uDao = new utenteDao();
uDao.insert(uDao.connetti(), u);
carta_credito c2=new carta_credito("1234567890123456",511,sdf.parse("15-12-2021"),1000);
abbonamento a2=new abbonamento(durata.settimanale,c2,false,0);
utente u2=new utente("Marco","Cavallari","Ciao189","ABCDF1242",true,a2);
cD.insert(cD.connetti(), c2);
aDao.insert(aDao.connetti(), a2);
uDao.insert(aDao.connetti(), u2);*/





/*abbonamentoDao aDao = new abbonamentoDao();
utenteDao uDao = new utenteDao();
rastrelliera r=new rastrelliera(5);
bici_normale b1=new bici_normale(false);
r.inserisci_bici(b1);
utente u2=new utente ("Ciao189","ABCDF1242");
totem t=new totem(r);
boolean y=t.login(u2);
prelievo p=null;
if(y) {
	String tipo=t.scegli_bici("normale");
	if(t.get_rastrelliera().verifica_disponibilita_bici(tipo)) {
		int x=t.get_rastrelliera().slot_occupato(tipo);
		System.out.println("PRELEVARE BICI ALLA POSTAZIONE "+(x+1));
		t.get_rastrelliera().preleva_bici(x);
		t.get_rastrelliera().print_rastrelliera();
		//ORA L'UTENTE HA LA BICI
		p=new prelievo(u2,b1,LocalTime.now());
		uDao.update_abbonamento(uDao.connetti(), "ABCDF1242");
	} else {
		System.out.println("NON E' STATA TROVATA ALCUNA BICICLETTA DEL SEGUENTE TIPO: "+tipo);
	}
}*/
/*prelievoDao pre=new prelievoDao();
bici_normale b1=new bici_normale(false);
rastrelliera r=new rastrelliera(5);
r.inserisci_bici(b1);
totem t=new totem(r);
t.get_rastrelliera().print_rastrelliera();
boolean y=t.login("Ciao189","ABCDF1242");
prelievo p=null;
if(y) {
	String tipo=t.scegli_bici("normale");
	if(t.get_rastrelliera().verifica_disponibilita_bici(tipo)) {
		int x=t.get_rastrelliera().slot_occupato(tipo);
		System.out.println("PRELEVARE BICI ALLA POSTAZIONE "+(x+1));
		t.get_rastrelliera().preleva_bici(x);
		t.get_rastrelliera().print_rastrelliera();
		//utente u=new utente("Ciao189","ABCDF1242");//da sostituire con selectio da utente (metoto utenteDao che ritorna coppia utente password
		p=new prelievo(u,b1,LocalTime.now());
		//ORA L'UTENTE HA LA BICI
		//p=new prelievo()
		pre.insert(pre.connetti(), p);
	} else {
		System.out.println("NON E' STATA TROVATA ALCUNA BICICLETTA DEL SEGUENTE TIPO: "+tipo);
	}

}
System.out.println("-----------L'UTENTE STA DEPOSITANDO LA BICICLETTA PRECEDENTEMENTE PRELEVATA--------");
t.get_rastrelliera().inserisci_bici(b1);
p.set_fine(LocalTime.of(22, 40, 45, 342123342));
p.print_prelievo();
pre.update(pre.connetti(), p,p.get_fine());
System.out.println(p.get_durata(p.get_inizio(), p.get_fine()));
System.out.println(p.calcola_spesa(p.get_durata(p.get_inizio(), p.get_fine())));*/
/*utenteDao uDao = new utenteDao();
utente u=uDao.get_utente(uDao.connetti(), "ABCDF1242");
u.print_utente();
u.get_abbobamento().print_abbonamneto();
u.get_abbobamento().get_carta().print_carta();*/
/*utente u=new utente("Marco","Cavallari","hdbfujb",true,null);
System.out.println(u.get_codice());*/

