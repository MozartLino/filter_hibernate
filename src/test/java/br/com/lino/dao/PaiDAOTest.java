package br.com.lino.dao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.lino.model.Filho;
import br.com.lino.model.Pai;
import br.com.lino.util.HibernateUtil;

public class PaiDAOTest {

	public Session session;

	@Test
	public void deveTrazerPaiContendoOsFilhosComIdadeMaiorQue10() {
		Pai pai = new Pai("Pai",
				Arrays.asList(
						new Filho("Filho 1", 10),
						new Filho("Filho 2", 14),
						new Filho("Filho 3", 9)));

		session.save(pai);
		session.flush();
		session.clear();

		Pai paiComDeterminadosFilhos = new PaiDAO(session).buscaPaiComFilhosDeIdadeMaiorQue(10, 1L);
		assertEquals(1, paiComDeterminadosFilhos.getFilhos().size());
	}

	@Before
	public void setUp() {
		session = HibernateUtil.getCurrentSession();
		HibernateUtil.beginTransaction();
	}

	@After
	public void setDown() {
		HibernateUtil.rollbackTransaction();
		HibernateUtil.closeSession();
	}


}
