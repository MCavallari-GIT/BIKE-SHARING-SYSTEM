package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import domain.carta_credito;
import domain.servizio_pagamenti;

class servizio_pagamentiTest {

	@Test
	void test_verifica() {
		carta_credito c=new carta_credito("1111111111111112",112,LocalDate.now().plusYears(2),100);
		String durata="annuale";
		servizio_pagamenti s=new servizio_pagamenti();
		assertTrue(s.verifica(c, durata));
	}
	
	
	@Test
	void test_verifica2() {
		carta_credito c=new carta_credito("1",112,LocalDate.now().plusYears(2),100);
		String durata="annuale";
		servizio_pagamenti s=new servizio_pagamenti();
		assertFalse(s.verifica(c, durata));
	}
	
	@Test
	void test_verifica3() {
		carta_credito c=new carta_credito("1111111111111112",112,LocalDate.now().plusDays(5),100);
		String durata="annuale";
		servizio_pagamenti s=new servizio_pagamenti();
		assertFalse(s.verifica(c, durata));
	}

}
