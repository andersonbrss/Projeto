package br.com.projeto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Session Bean implementation class Hello
 */
@Path("hello")
public class Hello {

    public Hello() {
        // TODO Auto-generated constructor stub
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response mensagem() {
    	return Response.ok("Hello World").build();
    }
    
}
