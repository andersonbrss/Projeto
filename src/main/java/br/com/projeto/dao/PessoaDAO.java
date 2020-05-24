package br.com.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projeto.pojo.Pessoa;

public class PessoaDAO {

	private EntityManager em;
	
	public PessoaDAO( EntityManager em ) {
		this.em = em;
	}
	
	public Pessoa salvar( Pessoa pessoa ) throws Exception {
		if( pessoa.getId() == null ) {
			em.persist( pessoa );
		} else  {
			if( !em.contains( pessoa )) {
				if( em.find(Pessoa.class, pessoa.getId()) == null ) {
					throw new Exception( "Erro ao atualizar a pessoa." );
				}
			}
			pessoa = em.merge( pessoa );
		}
		return pessoa;
	}
	
	public void delete( Long id ) {
		Pessoa pessoa = em.find( Pessoa.class, id );
		em.remove( pessoa );
	}
	
	public Pessoa consultarPorId( Long id ) {
		return em.find( Pessoa.class, id );
	}
	
	public List< Pessoa > consultarTodos() {
		Query q = em.createNamedQuery( "Pessoa.consultarTodos" );
		
		return q.getResultList();
	}
}
