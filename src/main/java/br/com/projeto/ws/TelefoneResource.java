package br.com.projeto.ws;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
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

import br.com.projeto.bean.TelefoneBean;
import br.com.projeto.pojo.Telefone;


@Path( "telefones" )
public class TelefoneResource {

	@EJB
	private TelefoneBean bean;
	
	public TelefoneResource() {}
	
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Response getTelefones() {
		
		return Response.ok( bean.consultarTodos() ).build();
	}
	
	@Path( "{id}" )
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Response getTelefone( @PathParam( "id" ) Long id ) {
		return Response.ok( bean.telefonePorId( id ) ).build();
	}
	
	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public Response salvar( Telefone telefone ) {
		try {
			bean.salvar( telefone );
		} catch( Exception e ) {
			System.out.println( e.getMessage() );
			return Response.status( Status.BAD_REQUEST ).build();
		}
		return Response.status( Status.CREATED ).build();
	}
	
	@DELETE
	@Path( "{id}" )
	public Response delete( final @PathParam( "id" ) Long id ) {
		bean.delete( id );
		
		return Response.ok().build();
	}
	
	@PUT
	@Consumes( MediaType.APPLICATION_JSON )
	public Response update( Telefone telefone ) {
		salvar( telefone );
		
		return Response.ok().build();
	}
	
}
