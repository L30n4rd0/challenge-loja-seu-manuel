/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.dao;

import java.sql.SQLException;
import java.util.List;

import br.leo.lojaSeuManuel.modelo.vo.Pedido;
import br.leo.lojaSeuManuel.modelo.vo.Periodo;

/**
 * @author leonardo
 *
 */
public interface PedidoDao {
	
	public List<Pedido> listar() throws ClassNotFoundException, SQLException;
	
	public List<Pedido> listarPorPeriodo(Periodo periodo) throws ClassNotFoundException, SQLException;
	
	public Pedido buscarPorId(int id) throws ClassNotFoundException, SQLException;
	
	public int inserir(Pedido pedido) throws ClassNotFoundException, SQLException;
	
	public void atualizar(Pedido pedido) throws ClassNotFoundException, SQLException;
	
	public void excluir(int id) throws ClassNotFoundException, SQLException;

}
