package br.com.projeto.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.projeto.dao.OperadorDAO;
import br.com.projeto.pojo.Operador;

@Stateless
public class OperadorBeanImpl implements OperadorBean {

	@PersistenceContext( unitName = "ProjetoPU" )
	private EntityManager em;
	
	@Override
	public Operador salvar(Operador operador) throws Exception {
		OperadorDAO dao = new OperadorDAO( em );
		
		return dao.salvar( operador );
	}

	@Override
	public void delete(Long id) {
		OperadorDAO dao = new OperadorDAO( em );
		dao.remover(id);
	}

	@Override
	public Operador operadorPorId(Long id) {
		OperadorDAO dao = new OperadorDAO( em );
		
		return dao.consultarPorId( id );
	}

	@Override
	public List<Operador> consultarTodos() {
		OperadorDAO dao = new OperadorDAO( em );
		
		return dao.consultarTodos();
	}

	@Override
	public Operador consultarLogin(String login, String senha) {
		OperadorDAO dao = new OperadorDAO( em );
		
		return dao.consultarLogin(login, senha);
	}

}
