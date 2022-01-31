package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import domain.abbonamento;
import domain.bici_normale;
import domain.carta_credito;
import domain.durata;
import domain.utente;
import domain.*;
import java.time.LocalDateTime;

class prelievoTest {
	
	
	//gli studenti le bici nomrali non le pagano mai
	@Test
	void test() {
		carta_credito c=new carta_credito("1111111111111112",112,LocalDate.now().plusYears(2),100);
		abbonamento a=new abbonamento(durata.annuale,c,true,0);
		utente u=new utente("Marco","Cavvallari","Password_corretta123",true,a);
		bici_normale b=new bici_normale("CODICEBICI",false);
		prelievo p=new prelievo(u,b,LocalDateTime.now());
		long durata=p.get_durata(p.get_inizio(), LocalDateTime.now().plusMinutes(10));
		double output=p.calcola_spesa(durata);
		assertEquals(0,output);
		
	}
	
	
	//studenti enon per utilizzi< a 30 min pagano 25 centesimi
		@Test
		void test2() {
			carta_credito c=new carta_credito("1111111111111112",112,LocalDate.now().plusYears(2),100);
			abbonamento a=new abbonamento(durata.annuale,c,true,0);
			utente u=new utente("Marco","Cavvallari","Password_corretta123",true,a);
			bici_elettrica b=new bici_elettrica("CODICEBICI",false);
			prelievo p=new prelievo(u,b,LocalDateTime.now());
			long durata=p.get_durata(p.get_inizio(), LocalDateTime.now().plusMinutes(10));
			double output=p.calcola_spesa(durata);
			assertEquals(0.25,output);
			
		}
		
		
		//per l'utilizzo della bici elettriìca con seggiolino di 3 ore il pagamento è di 7 euro e 75 centeimi perchè:
		//costo prima mezzora di utilizzo : 0.25
		//costo seconda mezzora di utilizzo : 0.50
		//costo terza mezzora di utilizzo : 1.00
		//costo quarta mezzora di utilizzo : 2.00
		//costo per ore successive (nel nostro esempio di 1 ora oltre alle prime due) : 4
		//--->7.75
				@Test
				void test3() {
					carta_credito c=new carta_credito("1111111111111112",112,LocalDate.now().plusYears(2),100);
					abbonamento a=new abbonamento(durata.annuale,c,true,0);
					utente u=new utente("Marco","Cavvallari","Password_corretta123",true,a);
					bici_elettrica_con_seggiolino b=new bici_elettrica_con_seggiolino("CODICEBICI",false);
					prelievo p=new prelievo(u,b,LocalDateTime.now());
					long durata=p.get_durata(p.get_inizio(), LocalDateTime.now().plusMinutes(180));
					double output=p.calcola_spesa(durata);
					assertEquals(7.75,output);
					
				}

}
