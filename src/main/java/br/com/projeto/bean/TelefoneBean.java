package br.com.projeto.bean;

import java.util.List;

import br.com.projeto.pojo.Telefone;

public interface TelefoneBean {

	public Telefone salvar( Telefone telefone ) throws Exception;
	
	public void delete( Long id );
	
	public void deletePorIdPessoa( Long id );
	
	public Telefone telefonePorId( Long id );
	
	public List< Telefone > telefonePorIdPessoa( Long id );
	
	public List< Telefone > consultarTodos();
	
}
