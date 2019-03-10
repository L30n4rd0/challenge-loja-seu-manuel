/**
 * 
 */
package br.leo.lojaSeuManuel.controle.rest;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.leo.lojaSeuManuel.modelo.dao.PedidoDao;
import br.leo.lojaSeuManuel.modelo.dao.PedidoDaoSql;
import br.leo.lojaSeuManuel.modelo.dao.ProdutoDao;
import br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql;
import br.leo.lojaSeuManuel.modelo.vo.Pedido;
import br.leo.lojaSeuManuel.modelo.vo.Produto;

/**
 * @author leonardo
 *
 */


@Path("/pedidos")
public class PedidosRest {
	
private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	private PedidoDao pedidoDao;
	
	
	
	@PostConstruct
	private void init() {
		pedidoDao = new PedidoDaoSql();
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Pedido> list() {
		
		List<Pedido> listaPedidos = null;
		
		try {
			
			listaPedidos = pedidoDao.listar();
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		System.out.println("Executou o REST Listar pedidos.");
		
		return listaPedidos;
		
	}
	
	
	
	
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Pedido buscarPorId(@PathParam("id") int idPedido) {
		
		Pedido pedido = null;
		
		try {
			
			pedido = pedidoDao.buscaPorId(idPedido);
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		System.out.println("Executou o REST buscar pedido por id.");
		
		return pedido;
		
	}
	
	
	
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN + CHARSET_UTF8)
	public String editar(Pedido pedido, @PathParam("id") int idPedido) {
		
		String mensagemRetorno = "Produto editado id: ";
		
		try {
			
			pedido.setId(idPedido);
			
			pedidoDao.editar(pedido);
			
			mensagemRetorno += idPedido;
			
		} catch (ClassNotFoundException e) {
			
			mensagemRetorno = "Ocorreu um erro ao aplicar edição: " + e.getMessage();
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			mensagemRetorno = "Ocorreu um erro ao aplicar edição: " + e.getMessage();
			
			e.printStackTrace();
			
		}
		
		System.out.println("Executou o REST editar pedido por id.");
		
		return mensagemRetorno;
		
	}
	
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN + CHARSET_UTF8)
	public String adicionar(Pedido pedido) {
		
		String mensagemRetorno = "Produto inserido id: ";
		
		try {
			
			int idGerado = pedidoDao.adicionar(pedido);
			
			mensagemRetorno += idGerado;
			
		} catch (ClassNotFoundException e) {
			
			mensagemRetorno = "Ocorreu um erro ao adicionar: " + e.getMessage();
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			mensagemRetorno = "Ocorreu um erro ao adicionar: " + e.getMessage();
			
			e.printStackTrace();
			
		}
		
		System.out.println("Executou o REST adicionar produto.");
		
		return mensagemRetorno;
		
	}
	

}
