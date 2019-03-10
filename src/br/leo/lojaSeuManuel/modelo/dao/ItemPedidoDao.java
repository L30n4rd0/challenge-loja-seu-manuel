/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.dao;

import java.sql.SQLException;
import java.util.List;

import br.leo.lojaSeuManuel.modelo.vo.ItemPedido;

/**
 * @author leonardo
 *
 */
public interface ItemPedidoDao {
	
	public List<ItemPedido> listar() throws ClassNotFoundException, SQLException;
	
	public ItemPedido buscarPorId(int id) throws ClassNotFoundException, SQLException;
	
	int inserir(ItemPedido itemPedido, int chaveEstrangeiraPedido) throws ClassNotFoundException, SQLException;
	
	public void atualizar(ItemPedido itemPedido) throws ClassNotFoundException, SQLException;
	
	public void excluir(int id) throws ClassNotFoundException, SQLException;

	List<ItemPedido> buscarPorChaveEstrangeiraPedido(int chaveEstrangeiraPedido)
			throws ClassNotFoundException, SQLException;


}
