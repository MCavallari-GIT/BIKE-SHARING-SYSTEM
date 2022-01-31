package test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.utente;
import domain.*;
class abbonamentoTest {

	@Test
	void test_ammonizioni() {
		carta_credito c=new carta_credito("1111111111111112",112,LocalDate.now().plusYears(2),100);
		abbonamento a=new abbonamento(durata.annuale,c,true,0);
		a.ammonisci();
		assertEquals(a.get_ammonizioni(),1);
	}
	
	@Test
	void test_disattivzione() {
		carta_credito c=new carta_credito("1111111111111112",112,LocalDate.now().plusYears(2),100);
		abbonamento a=new abbonamento(durata.annuale,c,true,2);
		a.ammonisci();
		assertEquals(a.get_attivo(),false);
	}
	
	

}