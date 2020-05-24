package br.com.projeto.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.projeto.bean.OperadorBean;
import br.com.projeto.pojo.Operador;

@Path( "operador" )
public class OperadorResource {

	@EJB
	private OperadorBean bean;
	
	public OperadorResource() {}
	
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Response getOperadores() {
		List<Operador> operadorList = bean.consultarTodos();
		return Response.ok(operadorList).build();
	}
	
	@Path( "{id}" )
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Response getOperador( @PathParam( "id" ) Long id ) {
		
		return Response.ok( bean.operadorPorId(id) ).build();
	}
	
	@POST
	@Produces( MediaType.APPLICATION_JSON )
	public Response salvar( Operador operador ) {
		try {
			operador = bean.salvar( operador );
		} catch (Exception e) {
			System.out.println( e.getMessage() );
			return Response.status( Status.BAD_REQUEST ).build();
		}
		
		return Response.ok( operador ).build();
	}
	
	@DELETE
	@Path( "{id}" )
	@Produces( MediaType.APPLICATION_JSON )
	public Response delete( final @PathParam( "id" ) Long id ) {
		bean.delete( id );
		
		return Response.ok().build();
	}
	
	@PUT
	@Produces( MediaType.APPLICATION_JSON )
	public void update( Operador operador ) {
		
		salvar( operador );
	}
}
