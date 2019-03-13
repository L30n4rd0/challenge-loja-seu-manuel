/**
 * 
 */
package br.leo.lojaSeuManuel.controle.rest;

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

import br.leo.lojaSeuManuel.controle.ControlePedido;
import br.leo.lojaSeuManuel.modelo.vo.Pedido;


/////////////////
// A configuração do serviço REST da biblioteca jersey 
// encontra-se no arquivo WebContent/WEB-INF/web.xml
////////////////


/**
 * @author leonardo
 *
 */
@Path("/pedidos")
public class PedidosRest {
	
private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	private ControlePedido controlePedido;
	
	
	
	@PostConstruct
	private void init() {
		controlePedido = new ControlePedido();
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Pedido> listar() {
		
		List<Pedido> listaPedidos = null;
		
		try {
			
			listaPedidos = controlePedido.listar();
			
		} catch (Exception e) {
			
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
			
			pedido = controlePedido.buscarPorId(idPedido);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		System.out.println("Executou o REST buscar pedido por id.");
		
		return pedido;
		
	}
	
	
	
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN + CHARSET_UTF8)
	public String atualizar(Pedido pedido, @PathParam("id") int idPedido) {
		
		String mensagemRetorno = "";
		
		try {
			
			pedido.setId(idPedido);
			
			mensagemRetorno = controlePedido.atualizar(pedido);
			
		} catch (Exception exception) {
			
			mensagemRetorno = "Ocorreu um erro ao atualizar: \n\n" + exception.getMessage();
			
			exception.printStackTrace();
			
		}
		
		System.out.println("Executou o REST atualizar pedido por id.");
		
		return mensagemRetorno;
		
	}
	
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN + CHARSET_UTF8)
	public String inserir(Pedido pedido) {
		
		String mensagemRetorno = "Pedido inserido id: ";
		
		try {
			
			mensagemRetorno += controlePedido.inserir(pedido);;
			
		} catch (Exception exception) {
			
			exception.printStackTrace();
			
			mensagemRetorno = "Erro ao inserir pedido:\n\n" + exception.getMessage();
		}
		
		System.out.println("Executou o REST inserir produto.");
		
		return mensagemRetorno;
		
	}
	

}
