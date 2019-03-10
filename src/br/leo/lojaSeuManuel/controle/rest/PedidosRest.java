/**
 * 
 */
package br.leo.lojaSeuManuel.controle.rest;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
		
		System.out.println("Executou o REST Listar produtos.");
		
		return listaPedidos;
		
	}
	

}
