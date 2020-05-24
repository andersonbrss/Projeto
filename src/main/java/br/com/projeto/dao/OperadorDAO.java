package br.com.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projeto.pojo.Operador;

public class OperadorDAO {

	private EntityManager em;
	
	public OperadorDAO( EntityManager em ) {
		this.em = em;
	}
	
	public Operador salvar( Operador operador ) throws Exception {
		if( operador.getId() == null ) {
			em.persist( operador );
		} else {
			if( !em.contains( operador ) ) {
				if( em.find(Operador.class, operador.getId()) == null) {
					throw new Exception( "Erro ao atualizar o operador" );
				}
			}
			operador = em.merge( operador );
		}
		return operador;
	}
	
	public void remover( Long id ) {
		Operador operador = em.find( Operador.class, id );
		em.remove( operador );
	}
	
	public Operador consultarPorId( Long id ) {
		return em.find( Operador.class, id );
	}
	
	public List<Operador> consultarTodos() {
		Query q = em.createNamedQuery("Operador.consultarTodos");
		
		return q.getResultList();
	}
	
	public Operador consultarLogin( String login, String senha ) {
		Query query = em.createQuery(
				"SELECT operador FROM Operador operador WHERE operador.login =:login and operador.senha =:senha",
				Operador.class);	
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		Operador o;
		try {
			o = (Operador) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		return o;
	}
	
}
