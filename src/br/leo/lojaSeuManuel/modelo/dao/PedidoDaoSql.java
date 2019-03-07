/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.dao;

import java.sql.SQLException;
import java.util.List;

import br.leo.lojaSeuManuel.modelo.vo.Pedido;

/**
 * @author leonardo
 *
 */
public class PedidoDaoSql implements PedidoDao {

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.PedidoDao#listar()
	 */
	@Override
	public List<Pedido> listar() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.PedidoDao#buscaPorId(int)
	 */
	@Override
	public Pedido buscaPorId(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.PedidoDao#adicionar(br.leo.lojaSeuManuel.modelo.vo.Pedido)
	 */
	@Override
	public int adicionar(Pedido pedido) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.PedidoDao#editar(br.leo.lojaSeuManuel.modelo.vo.Pedido)
	 */
	@Override
	public void editar(Pedido pedido) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.PedidoDao#excluir(int)
	 */
	@Override
	public void excluir(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

}
