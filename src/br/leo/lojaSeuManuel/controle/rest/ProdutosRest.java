/**
 * 
 */
package br.leo.lojaSeuManuel.controle.rest;

import java.sql.SQLException;
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

import br.leo.lojaSeuManuel.modelo.dao.ProdutoDao;
import br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql;
import br.leo.lojaSeuManuel.modelo.vo.Produto;

/**
 * @author leonardo
 *
 */

@Path("/produtos")
public class ProdutosRest {
	private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	private ProdutoDao produtoDao;
	
	
	
	@PostConstruct
	private void init() {
		produtoDao = new ProdutoDaoSql();
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Produto> list() {
		
		List<Produto> listaProdutos = null;
		
		try {
			
			listaProdutos = produtoDao.listar();
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
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
			
			produto = produtoDao.buscaPorId(idProduto);
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		System.out.println("Executou o REST buscar produto por id.");
		
		return produto;
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN + CHARSET_UTF8)
	public String adicionar(Produto produto) {
		
		String mensagemRetorno = "Produto inserido id: ";
		
		try {
			
			int idGerado = produtoDao.adicionar(produto);
			
			mensagemRetorno += idGerado;
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		System.out.println("Executou o REST adicionar produto.");
		
		return mensagemRetorno;
		
	}
	
	
	
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN + CHARSET_UTF8)
	public String editar(Produto produto, @PathParam("id") int idProduto) {
		
		String mensagemRetorno = "Produto editado id: ";
		
		try {
			
			produto.setId(idProduto);
			
			produtoDao.editar(produto);
			
			mensagemRetorno += idProduto;
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		System.out.println("Executou o REST editar produto por id.");
		
		return mensagemRetorno;
		
	}
	
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN + CHARSET_UTF8)
	public String excluirPorId(@PathParam("id") int idProduto) {
		
		String mensagemRetorno = "Produto excluido id: ";
		
		try {
			
			produtoDao.excluir(idProduto);
			
			mensagemRetorno += idProduto;
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		System.out.println("Executou o REST excluir produto por id.");
		
		return mensagemRetorno;
		
	}
	
	

}
