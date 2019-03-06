/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.dao;

import java.util.List;

import br.leo.lojaSeuManuel.modelo.vo.Pedido;

/**
 * @author leonardo
 *
 */
public interface PedidoDao {
	
	public List<Pedido> listar();
	
	public Pedido buscaPorId(int id);
	
	public int adicionar(Pedido pedido);
	
	public void editar(Pedido pedido);
	
	public void excluir(int id);

}
