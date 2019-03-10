/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.leo.lojaSeuManuel.modelo.dao.conexao.ConexaoSql;
import br.leo.lojaSeuManuel.modelo.vo.ItemPedido;
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
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		ResultSet resultSet = null;
		
		String stringSQL="SELECT * FROM pedido";
		
		List<Pedido> listaDePedidos = null;
		
		try {
			connection = ConexaoSql.getConnection();
			
			preparedStatement = connection.prepareStatement(stringSQL);
			
			resultSet = preparedStatement.executeQuery();
			
			ItemPedidoDao itemPedidoDao = null;

			while (resultSet.next()) {
				
				if (listaDePedidos == null) {
					
					listaDePedidos = new ArrayList<Pedido>();
					
				}
				
				if (itemPedidoDao == null) {
					
					itemPedidoDao = new ItemPedidoDaoSql();
					
				}
				
				int idPedido = resultSet.getInt("id_pedido");
				
				List<ItemPedido> listaDeItens = itemPedidoDao.buscaPorChaveEstrangeiraPedido(idPedido);
				
				listaDePedidos.add(
						
						new Pedido(
								idPedido, 
								resultSet.getString("codigo"), 
								resultSet.getDate("data_compra"), 
								resultSet.getString("nome_comprador"), 
								resultSet.getString("estado"), 
								resultSet.getDouble("valor_frete"), 
								listaDeItens
						)
				);
				
			}
				
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, resultSet);
			
		}
		
		return listaDePedidos;
		
	}

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.PedidoDao#buscaPorId(int)
	 */
	@Override
	public Pedido buscaPorId(int id) throws ClassNotFoundException, SQLException {
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		ResultSet resultSet = null;
		
		String stringSQL="SELECT * FROM pedido WHERE id_pedido = ?";
		
		Pedido pedidoRetorno = null;
		
		try {
			connection = ConexaoSql.getConnection();
			
			preparedStatement = connection.prepareStatement(stringSQL);
			
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			ItemPedidoDao itemPedidoDao = null;

			if (resultSet.next()) {
				
				itemPedidoDao = new ItemPedidoDaoSql();
					
				int idPedido = resultSet.getInt("id_pedido");
				
				List<ItemPedido> listaDeItens = itemPedidoDao.buscaPorChaveEstrangeiraPedido(idPedido);
				
				pedidoRetorno = new Pedido(
								idPedido, 
								resultSet.getString("codigo"), 
								resultSet.getDate("data_compra"), 
								resultSet.getString("nome_comprador"), 
								resultSet.getString("estado"), 
								resultSet.getDouble("valor_frete"), 
								listaDeItens
				);
				
			}
				
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, resultSet);
			
		}
		
		return pedidoRetorno;
		
	}

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.PedidoDao#adicionar(br.leo.lojaSeuManuel.modelo.vo.Pedido)
	 */
	@Override
	public int adicionar(Pedido pedido) throws ClassNotFoundException, SQLException {
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		ResultSet resultSet = null;
		
		int idGerado = 0;
		
		String stringSQL="INSERT INTO pedido (codigo, data_compra, nome_comprador, estado, valor_frete, valor_total) VALUES (?,?,?,?,?,?)";
		
		try {
			
			connection = ConexaoSql.getConnection();
			preparedStatement = connection.prepareStatement(stringSQL, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, pedido.getCodigo());
			preparedStatement.setDate(2, pedido.getDataCompra());
			preparedStatement.setString(3, pedido.getNomeComprador());
			preparedStatement.setString(4, pedido.getEstado());
			preparedStatement.setDouble(5, pedido.getValorFrete());
			preparedStatement.setDouble(6, pedido.getValorTotal());
			
			preparedStatement.execute();
			
			resultSet = preparedStatement.getGeneratedKeys();
			
			if (resultSet.next()) {
				
				idGerado = resultSet.getInt(1);
				
				ItemPedidoDao itemPedidoDao = new ItemPedidoDaoSql();
				
				for (ItemPedido itemPedido : pedido.getItensDoPedido()) {
					
					itemPedidoDao.adicionar(itemPedido, idGerado);
					
				}
				
			}
			
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, resultSet);
			
		}
		
		return idGerado;
		
	}

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.PedidoDao#editar(br.leo.lojaSeuManuel.modelo.vo.Pedido)
	 */
	@Override
	public void editar(Pedido pedido) throws ClassNotFoundException, SQLException {
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		String stringSQL="UPDATE pedido SET codigo = ?, data_compra = ?, nome_comprador = ?, estado = ?, valor_frete = ?, valor_total = ? WHERE id_pedido = ?";
		
		try {
			
			connection = ConexaoSql.getConnection();
			preparedStatement = connection.prepareStatement(stringSQL);
			
			preparedStatement.setString(1, pedido.getCodigo());
			preparedStatement.setDate(2, pedido.getDataCompra());
			preparedStatement.setString(3, pedido.getNomeComprador());
			preparedStatement.setString(4, pedido.getEstado());
			preparedStatement.setDouble(5, pedido.getValorFrete());
			preparedStatement.setDouble(6, pedido.getValorTotal());
			preparedStatement.setInt(7, pedido.getId());
			
			preparedStatement.execute();
			
				
			ItemPedidoDao itemPedidoDao = new ItemPedidoDaoSql();
			
			for (ItemPedido itemPedido : pedido.getItensDoPedido()) {
				
				itemPedidoDao.editar(itemPedido);
				
			}
				
			
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, null);
			
		}

	}

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.PedidoDao#excluir(int)
	 */
	@Override
	public void excluir(int id) throws ClassNotFoundException, SQLException {
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		String stringSQL="DELETE FROM pedido WHERE id_pedido = ?";
		
		try {
			
			connection = ConexaoSql.getConnection();
			preparedStatement = connection.prepareStatement(stringSQL);
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.execute();
			
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, null);
			
		}

	}

}
