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
import br.leo.lojaSeuManuel.modelo.vo.Produto;

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
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		ResultSet resultSet = null;
		
		String stringSQL="SELECT * FROM item_pedido";
		
		List<ItemPedido> listaDeItens = null;
		
		try {
			connection = ConexaoSql.getConnection();
			
			preparedStatement = connection.prepareStatement(stringSQL);
			
			resultSet = preparedStatement.executeQuery();
			
			ProdutoDao produtoDao = null;

			while (resultSet.next()) {
				
				if (listaDeItens == null) {
					
					listaDeItens = new ArrayList<ItemPedido>();
					
				}
				
				if (produtoDao == null) {
					
					produtoDao = new ProdutoDaoSql();
					
				}
				
				int idProduto = resultSet.getInt("fk_id_produto");
				
				Produto produto = produtoDao.buscaPorId(idProduto);
				
				listaDeItens.add(
						
						new ItemPedido(
								resultSet.getInt("id_item_pedido"), 
								idProduto, 
								produto.getCodigo(), 
								produto.getNome(), 
								produto.getPreco(), 
								resultSet.getInt("quantidade"), 
								resultSet.getDouble("preco")
						)
						
				);
				
			}
				
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, resultSet);
			
		}
		
		return listaDeItens;
		
	}

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDao#buscaPorId(int)
	 */
	@Override
	public ItemPedido buscaPorId(int id) throws ClassNotFoundException, SQLException {
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		ResultSet resultSet = null;
		
		String stringSQL="SELECT * FROM item_pedido WHERE id_item_pedido = ?";
		
		ItemPedido itemRetorno = null;
		
		try {
			connection = ConexaoSql.getConnection();
			
			preparedStatement = connection.prepareStatement(stringSQL);
			
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			ProdutoDao produtoDao = null;

			if (resultSet.next()) {
				
				if (produtoDao == null) {
					
					produtoDao = new ProdutoDaoSql();
					
				}
				
				int idProduto = resultSet.getInt("fk_id_produto");
				
				Produto produto = produtoDao.buscaPorId(idProduto);
				
				itemRetorno = new ItemPedido(
						resultSet.getInt("id_item_pedido"), 
						idProduto, 
						produto.getCodigo(), 
						produto.getNome(), 
						produto.getPreco(), 
						resultSet.getInt("quantidade"), 
						resultSet.getDouble("preco")
				);
				
			}
				
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, resultSet);
			
		}
		
		return itemRetorno;
	}

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDao#adicionar(br.leo.lojaSeuManuel.modelo.vo.ItemPedido)
	 */
	@Override
	public int adicionar(ItemPedido itemPedido, int chaveEstrangeiraPedido) throws ClassNotFoundException, SQLException {
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		ResultSet resultSet = null;
		
		int idGerado = 0;
		
		String stringSQL="INSERT INTO item_pedido (quantidade, preco, fk_id_produto, fk_id_pedido) VALUES (?,?,?,?)";
		
		try {
			
			connection = ConexaoSql.getConnection();
			preparedStatement = connection.prepareStatement(stringSQL, Statement.RETURN_GENERATED_KEYS);
			
			itemPedido.setPrecoProduto( new ProdutoDaoSql().buscaPorId( itemPedido.getIdProduto() ).getPreco() );
			itemPedido.atualizarPreco();
			
			preparedStatement.setInt(1, itemPedido.getQuantidade());
			preparedStatement.setDouble(2, itemPedido.getPreco());
			preparedStatement.setInt(3, itemPedido.getIdProduto());
			preparedStatement.setInt(4, chaveEstrangeiraPedido);
			
			preparedStatement.execute();
			
			resultSet = preparedStatement.getGeneratedKeys();
			
			if (resultSet.next()) {
				
				idGerado = resultSet.getInt(1);
				
			}
			
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, resultSet);
			
		}
		
		return idGerado;
		
	}

	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDao#editar(br.leo.lojaSeuManuel.modelo.vo.ItemPedido)
	 */
	@Override
	public void editar(ItemPedido itemPedido) throws ClassNotFoundException, SQLException {
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		String stringSQL="UPDATE item_pedido SET quantidade = ?, preco = ?, fk_id_produto = ? WHERE id_item_pedido = ?";
		
		try {
			
			connection = ConexaoSql.getConnection();
			preparedStatement = connection.prepareStatement(stringSQL);
			
			itemPedido.setPrecoProduto( new ProdutoDaoSql().buscaPorId( itemPedido.getIdProduto() ).getPreco() );
			itemPedido.atualizarPreco();
			
			preparedStatement.setInt(1, itemPedido.getQuantidade());
			preparedStatement.setDouble(2, itemPedido.getPreco());
			preparedStatement.setInt(3, itemPedido.getIdProduto());
			preparedStatement.setInt(4, itemPedido.getId());
			
			preparedStatement.execute();
			
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, null);
			
		}
		
	}

	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDao#excluir(int)
	 */
	@Override
	public void excluir(int id) throws ClassNotFoundException, SQLException {
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		String stringSQL="DELETE FROM item_pedido WHERE id_item_pedido = ?";
		
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
