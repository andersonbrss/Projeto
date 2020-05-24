package br.com.projeto.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.projeto.bean.LoginBean;
import br.com.projeto.pojo.Operador;
import br.com.projeto.security.JWTUtil;
import br.com.projeto.security.dto.LoginRequest;
import br.com.projeto.security.dto.LoginResponse;

@Path("login")
@Stateless
public class LoginResource {

	@EJB
	private LoginBean bean;
	
	@GET
	public Response init() {
		return Response.ok("Hello JWT").build();
	}
	
	@POST
	@Produces( MediaType.APPLICATION_JSON )
	public Response login( LoginRequest credentials) {
		Operador operador = bean.login(credentials);
		
	    if( operador != null && operador.getId() != null ){
	        String token = JWTUtil.create( credentials.getLogin(), operador.getPerfil().toString() );
	        LoginResponse user  = new LoginResponse( credentials.getLogin(), operador.getPerfil().toString(), token );
	        
	        return Response.ok().entity( user ).build();
	    }else{
	        return Response.status(Response.Status.UNAUTHORIZED).build();
	    }
	}
	
}
