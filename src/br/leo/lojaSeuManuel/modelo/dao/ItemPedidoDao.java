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
	
	public ItemPedido buscaPorId(int id) throws ClassNotFoundException, SQLException;
	
	int adicionar(ItemPedido itemPedido, int chaveEstrangeiraPedido) throws ClassNotFoundException, SQLException;
	
	public void editar(ItemPedido itemPedido) throws ClassNotFoundException, SQLException;
	
	public void excluir(int id) throws ClassNotFoundException, SQLException;

	List<ItemPedido> buscaPorChaveEstrangeiraPedido(int chaveEstrangeiraPedido)
			throws ClassNotFoundException, SQLException;


}
