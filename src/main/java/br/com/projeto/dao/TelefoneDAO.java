package br.com.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projeto.pojo.Telefone;

public class TelefoneDAO {

private EntityManager em;
	
	public TelefoneDAO( EntityManager em ) {
		this.em = em;
	}
	
	public Telefone salvar( Telefone telefone ) throws Exception {
		if( telefone.getId() == null ) {
			em.persist( telefone );
		} else  {
			if( !em.contains( telefone )) {
				if( em.find( Telefone.class, telefone.getId()) == null ) {
					throw new Exception( "Erro ao atualizar a pessoa." );
				}
			}
			telefone = em.merge( telefone );
		}
		return telefone;
	}
	
	public void delete( Long id ) {
		Telefone pessoa = em.find( Telefone.class, id );
		em.remove( pessoa );
	}
	
	public Telefone consultarPorId( Long id ) {
		return em.find( Telefone.class, id );
	}
	
	public List< Telefone > consultarTodos() {
		Query q = em.createNamedQuery( "Telefone.consultarTodos" );
		
		return q.getResultList();
	}
	
	public List< Telefone > consultarPorIdPessoa( Long id ) {
		Query q = em.createNamedQuery( "Telefone.consultarPorIdPessoa" );
		q.setParameter("idpessoa", id);
		
		return q.getResultList();
	}
	
	public void deletePorIdPessoa( Long id ) {
		Query q = em.createNamedQuery( "Telefone.deletePorIdPessoa" );
		q.setParameter("idpessoa", id);
		q.executeUpdate();
	}
}
