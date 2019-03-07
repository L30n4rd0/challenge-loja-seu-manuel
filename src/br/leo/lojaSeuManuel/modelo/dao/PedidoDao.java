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
public interface PedidoDao {
	
	public List<Pedido> listar() throws ClassNotFoundException, SQLException;
	
	public Pedido buscaPorId(int id) throws ClassNotFoundException, SQLException;
	
	public int adicionar(Pedido pedido) throws ClassNotFoundException, SQLException;
	
	public void editar(Pedido pedido) throws ClassNotFoundException, SQLException;
	
	public void excluir(int id) throws ClassNotFoundException, SQLException;

}
