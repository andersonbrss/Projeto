package br.com.projeto;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.projeto.bean.OperadorBean;
import br.com.projeto.enuns.Perfil;
import br.com.projeto.pojo.Operador;

@ApplicationPath("recursos")
public class ApplicationConfig extends Application {

	@EJB
	OperadorBean bean;
	
	@PostConstruct
	public void cadastrarAdm() {
		if( bean.consultarLogin("admin", "admin") == null ) {
			try {
				bean.salvar( new Operador("admin", "admin", "admin", Perfil.ADM) );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
