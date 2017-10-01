package com.controleFinanceiro;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.controleFinanceiro.model.Pessoa;

import junit.framework.Assert;

public class ControleFinanceiroApplicationTests {

	@Test
	public void contextLoads() {
		
		assertTrue(true);
		
	}
	
	@Test
	public void isPessoaAtivaTest() {
		
		Pessoa p = new Pessoa();
		p.setAtivo(true);
		
		assertEquals(false, p.isPessoaInativo());
		
	}
	
	@Test
	public void isPessoaInativaTest() {
		
		Pessoa p = new Pessoa();
		p.setAtivo(false);
		
		assertEquals(true, p.isPessoaInativo());
		
	}

}
