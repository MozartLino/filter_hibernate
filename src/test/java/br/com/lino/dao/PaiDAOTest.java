package br.com.lino.dao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.lino.model.Filho;
import br.com.lino.model.Pai;

public class PaiDAOTest {

	public Session session;
	private SessionFactory sessionFactory;

	@Test
	public void deveTrazerPaiContendoOsFilhosComIdadeMaiorQue10() {
		Pai pai = new Pai("Pai",
				Arrays.asList(
						new Filho("Filho 1", 10),
						new Filho("Filho 2", 14),
						new Filho("Filho 3", 9)
						)
				);

		session.save(pai);
		session.flush();
		session.clear();

		Pai paiComDeterminadosFilhos = new PaiDAO(session).buscaPaiComFilhosDeIdadeMaiorQue(10, 1L);
		assertEquals(1, paiComDeterminadosFilhos.getFilhos().size());
	}

	@Before
	public void setUp() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.createSQLQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
		session.createSQLQuery("truncate table Pai").executeUpdate();
		session.createSQLQuery("truncate table Filho").executeUpdate();
		session.createSQLQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
	}

	@After
	public void setDown() {
		session.close();
		sessionFactory.close();
	}

}
