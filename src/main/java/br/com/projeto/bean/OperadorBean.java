package br.com.projeto.bean;

import java.util.List;

import br.com.projeto.pojo.Operador;

public interface OperadorBean {
	
	public Operador salvar( Operador operador ) throws Exception;
	
	public void delete( Long id );
	
	public Operador operadorPorId( Long id );
	
	public List< Operador > consultarTodos();
	
	public Operador consultarLogin( String login, String senha );
}
