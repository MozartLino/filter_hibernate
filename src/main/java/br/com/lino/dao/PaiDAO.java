package br.com.lino.dao;

import org.hibernate.Session;

import br.com.lino.model.Pai;

public class PaiDAO {

	private Session session;

	public PaiDAO(Session session) {
		this.session = session;
	}

	public Pai buscaPaiComFilhosDeIdadeMaiorQue(Long id, Integer idade) {
		session.enableFilter("filterIdade").setParameter("idade", idade);
		Pai pai = (Pai) session.load(Pai.class, id);
		session.disableFilter("filterIdade");

		return pai;
	}

}
