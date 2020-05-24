package br.com.projeto.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.projeto.dao.TelefoneDAO;
import br.com.projeto.pojo.Telefone;

@Stateless
public class TelefoneBeanImpl implements TelefoneBean {

	@PersistenceContext( unitName = "ProjetoPU" )
	private EntityManager em;
	
	@Override
	public Telefone salvar(Telefone telefone) throws Exception {
		TelefoneDAO dao = new TelefoneDAO( em );
		
		return dao.salvar( telefone );
	}

	@Override
	public void delete(Long id) {
		TelefoneDAO dao = new TelefoneDAO( em );
		dao.delete( id );
	}

	@Override
	public Telefone telefonePorId(Long id) {
		TelefoneDAO dao = new TelefoneDAO( em );
		
		return dao.consultarPorId( id );
	}

	@Override
	public List<Telefone> consultarTodos() {
		TelefoneDAO dao = new TelefoneDAO( em );
		
		return dao.consultarTodos();
	}

}
