/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.dao;

import java.util.List;

import br.leo.lojaSeuManuel.modelo.vo.ItemPedido;

/**
 * @author leonardo
 *
 */
public interface ItemPedidoDao {
	
	public List<ItemPedido> listar();
	
	public ItemPedido buscaPorId(int id);
	
	public int adicionar(ItemPedido itemPedido);
	
	public void editar(ItemPedido itemPedido);
	
	public void excluir(int id);

}
