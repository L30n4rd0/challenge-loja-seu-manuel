/**
 * 
 */
package br.leo.lojaSeuManuel.controle.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.ApplicationPath;

import br.leo.lojaSeuManuel.controle.ControleProduto;
import br.leo.lojaSeuManuel.modelo.vo.Produto;

/**
 * @author leonardo
 *
 */

@Path("/produtos")
public class ProdutosRest {
	private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	private ControleProduto controleProduto;
	
	
	
	@PostConstruct
	private void init() {
		
		this.controleProduto = new ControleProduto();
		
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Produto> listar() {
		
		List<Produto> listaProdutos = null;
		
		try {
			
			listaProdutos = controleProduto.listar();
			
		} catch (Exception exception) {
			
			exception.printStackTrace();
			
		}
		
		System.out.println("Executou o REST Listar produtos.");
		
		return listaProdutos;
		
	}
	
	
	
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Produto buscarPorId(@PathParam("id") int idProduto) {
		
		Produto produto = null;
		
		try {
			
			produto = controleProduto.buscarPorId(idProduto);
			
		} catch (Exception exception) {
			
			exception.printStackTrace();
			
		}
		
		System.out.println("Executou o REST buscar produto por id.");
		
		return produto;
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN + CHARSET_UTF8)
	public String inserir(Produto produto) {
		
		String mensagemRetorno = "Produto inserido id: ";
		
		try {
			
			mensagemRetorno += controleProduto.inserir(produto);
			
		} catch (Exception exception) {
			
			exception.printStackTrace();
			
			mensagemRetorno = exception.getMessage();
		}
		
		System.out.println("Executou o REST inserir produto.");
		
		return mensagemRetorno;
		
	}
	
	
	
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN + CHARSET_UTF8)
	public String atualizar(Produto produto, @PathParam("id") int idProduto) {
		
		String mensagemRetorno = "";
		
		try {
			
			produto.setId(idProduto);
			
			mensagemRetorno = controleProduto.atualizar(produto);
			
		} catch (Exception exception) {
			
			mensagemRetorno = "Ocorreu um erro ao atualizar: \n\n" + exception.getMessage();
			
			exception.printStackTrace();
			
		}
		
		System.out.println("Executou o REST atualizar produto por id.");
		
		return mensagemRetorno;
		
	}
	
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN + CHARSET_UTF8)
	public String excluirPorId(@PathParam("id") int idProduto) {
		
		String mensagemRetorno = "";
		
		try {
			
			mensagemRetorno = controleProduto.excluir(idProduto);
			
		} catch (Exception exception) {
			
			mensagemRetorno = "Ocorreu um erro ao excluir: \n\n" + exception.getMessage();
			
			exception.printStackTrace();
			
		}
		
		System.out.println("Executou o REST excluir produto por id.");
		
		return mensagemRetorno;
		
	}
	
	

}
