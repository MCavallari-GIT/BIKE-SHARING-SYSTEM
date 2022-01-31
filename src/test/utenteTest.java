package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import domain.*;
import domain.durata;

class utenteTest {

	@Test
	void test_verificaPsw1() {
		carta_credito c=new carta_credito("1111111111111112",112,LocalDate.now().plusYears(2),100);
		abbonamento a=new abbonamento(durata.annuale,c,true,0);
		utente u=new utente("Marco","Cavvallari","Password_corretta123",true,a);
		assertTrue(u.verifica_password(u.get_password()));
	}
	
	@Test
	void test_verificaPsw2() {
		carta_credito c=new carta_credito("1111111111111112",112,LocalDate.now().plusYears(2),100);
		abbonamento a=new abbonamento(durata.annuale,c,true,0);
		utente u=new utente("Marco","Cavvallari","password_senza_mainuscole",true,a);
		assertTrue(u.verifica_password(u.get_password()));
	}
	
	@Test
	void test_verificaPsw3() {
		carta_credito c=new carta_credito("1111111111111112",112,LocalDate.now().plusYears(2),100);
		abbonamento a=new abbonamento(durata.annuale,c,true,0);
		utente u=new utente("Marco","Cavvallari","Password_senza_numeri",true,a);
		assertTrue(u.verifica_password(u.get_password()));
	}

}
