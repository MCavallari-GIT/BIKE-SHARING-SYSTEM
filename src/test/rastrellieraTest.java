package test;

import static org.junit.jupiter.api.Assertions.*;
import domain.*;

import org.junit.jupiter.api.Test;

class rastrellieraTest {

	@Test
	void test_aggiorna_morse() {
		rastrelliera r=new rastrelliera(3);
		assertEquals(r.get_capacita(),r.aggiorna_morse_disponibili());
	}
	
	@Test
	void test_disponibilita_bici() {
		rastrelliera r=new rastrelliera(3);
		assertFalse(r.verifica_disponibilita_bici("normale"));
	}
	
	
	@Test
	void test_disponibilita_bici3() {
		rastrelliera r=new rastrelliera(3);
		bici_normale b=new bici_normale("CODICEBICI",false);
		r.inserisci_bici(b);
		assertTrue(r.verifica_disponibilita_bici("normale"));
	}

}
