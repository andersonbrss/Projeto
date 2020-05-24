package br.com.projeto.bean;

import java.util.List;

import br.com.projeto.pojo.Pessoa;

public interface PessoaBean {

	public Pessoa salvar( Pessoa pessoa ) throws Exception;
	
	public void delete( Long id );
	
	public Pessoa pessoaPorId( Long id );
	
	public List< Pessoa > consultarTodos();
	
}
