/**
 * 
 */
package br.leo.lojaSeuManuel.controle.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.ApplicationPath;

/**
 * @author leonardo
 *
 */

@Path("/produtos")
public class ProdutosRest {
	private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN + CHARSET_UTF8)
	public String list() {
		
		String result = "Um primeiro teeeeeste de lista!!!";
		
		System.out.println(result);
		
		return result;
		
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN + CHARSET_UTF8)
	public String buscarPorId(@PathParam("id") int idNota) {
		
		String result = "Um primeiro de busca por id!!!";
		
		System.out.println(result);
		
		return result;
		
	}
	

}
