package br.com.projeto.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.projeto.dao.PessoaDAO;
import br.com.projeto.dao.TelefoneDAO;
import br.com.projeto.pojo.Pessoa;

@Stateless
public class PessoaBeanImpl implements PessoaBean {

	@PersistenceContext( unitName = "ProjetoPU" )
	private EntityManager em;
	
	@Override
	public Pessoa salvar(Pessoa pessoa) throws Exception {
		PessoaDAO dao = new PessoaDAO( em );
		
		return dao.salvar( pessoa );
	}

	@Override
	public void delete(Long id) {
		TelefoneDAO telDao = new TelefoneDAO( em );
		telDao.deletePorIdPessoa(id);
		PessoaDAO dao = new PessoaDAO( em );
		dao.delete( id );
	}

	@Override
	public Pessoa pessoaPorId(Long id) {
		PessoaDAO dao = new PessoaDAO( em );
		
		return dao.consultarPorId( id );
	}

	@Override
	public List<Pessoa> consultarTodos() {
		PessoaDAO dao = new PessoaDAO( em );
		
		return dao.consultarTodos();
	}

}
