package br.com.projeto.bean;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.projeto.pojo.Operador;
import br.com.projeto.security.dto.LoginRequest;

@Stateless
public class LoginBeanImpl implements LoginBean {

	@EJB
	private OperadorBean bean;
	
	@Override
	public Operador login( LoginRequest credentials) {
		
		return bean.consultarLogin(credentials.getLogin(), credentials.getSenha());
	}

}
