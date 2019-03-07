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
public class ItemPedidoDaoSql implements ItemPedidoDao {

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDao#listar()
	 */
	@Override
	public List<ItemPedido> listar() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDao#buscaPorId(int)
	 */
	@Override
	public ItemPedido buscaPorId(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDao#adicionar(br.leo.lojaSeuManuel.modelo.vo.ItemPedido)
	 */
	@Override
	public int adicionar(ItemPedido itemPedido) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDao#editar(br.leo.lojaSeuManuel.modelo.vo.ItemPedido)
	 */
	@Override
	public void editar(ItemPedido itemPedido) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDao#excluir(int)
	 */
	@Override
	public void excluir(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

}
