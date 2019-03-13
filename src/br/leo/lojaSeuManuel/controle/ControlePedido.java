package br.leo.lojaSeuManuel.controle;

import java.util.List;

import br.leo.lojaSeuManuel.modelo.dao.PedidoDao;
import br.leo.lojaSeuManuel.modelo.dao.PedidoDaoSql;
import br.leo.lojaSeuManuel.modelo.vo.Pedido;


/**
 * @author leonardo
 *
 */
public class ControlePedido {
	
	private PedidoDao pedidoDao;
	
	private ValidadorPedido validadorPedido;
	
	
	
	
	
	/**
	 * @param pedidoDao
	 * @param validadorPedido
	 */
	public ControlePedido() {
		this.pedidoDao = new PedidoDaoSql();
		this.validadorPedido = new ValidadorPedido();
	}
	
	
	
	

	public List<Pedido> listar() throws Exception {
		
		return pedidoDao.listar();
		
	}
	
	
	
	

	public Pedido buscarPorId(int id) throws Exception {
		
		return pedidoDao.buscarPorId(id);
		
	}
	
	
	
	

	public int inserir(Pedido pedido) throws Exception {
		
		validadorPedido.validarPedido(pedido);

		return pedidoDao.inserir(pedido);
	}
	
	
	

	public String atualizar(Pedido pedido) throws Exception {
		
		if (pedidoDao.buscarPorId(pedido.getId()) == null) {
			
			throw new Exception("Pedido não cadastrado!");
			
		}
		
		validadorPedido.validarPedido(pedido);

		pedidoDao.atualizar(pedido);
		
		return "Produto atualizado id: " + pedido.getId();
		
	}
	
	

//	public String excluir(int id) throws Exception {
//		
//		if (pedidoDao.buscarPorId(id) == null) {
//			
//			throw new Exception("Pedido não cadastrado!");
//			
//		}
//		
//		pedidoDao.excluir(id);
//		
//		return "Pedido excuído id: " + id;
//
//	}
	
	

}
