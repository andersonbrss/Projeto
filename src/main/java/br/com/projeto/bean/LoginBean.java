package br.com.projeto.bean;

import br.com.projeto.pojo.Operador;
import br.com.projeto.security.dto.LoginRequest;

public interface LoginBean {

	public Operador login( LoginRequest credentials );
	
}
