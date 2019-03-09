/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.leo.lojaSeuManuel.modelo.dao.conexao.ConexaoSql;
import br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel;
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
	public int adicionar(ItemPedido itemPedido, int chaveEstrangeiraPedido) throws ClassNotFoundException, SQLException {
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		ResultSet resultSet = null;
		
		int idGerado = 0;
		
		String stringSQL="INSERT INTO item_pedido (quantidade, preco, fk_id_produto, fk_id_pedido) VALUES (?,?,?,?)";
		
		try {
			
			connection = ConexaoSql.getConnection();
			preparedStatement = connection.prepareStatement(stringSQL, Statement.RETURN_GENERATED_KEYS);
			
			double preco = ( new ProdutoDaoSql().buscaPorId(itemPedido.getIdProduto()).getPreco() * itemPedido.getQuantidade() );
			
			preparedStatement.setInt(1, itemPedido.getQuantidade());
			preparedStatement.setDouble(2, preco);
			preparedStatement.setInt(3, itemPedido.getIdProduto());
			preparedStatement.setInt(4, chaveEstrangeiraPedido);
			
			preparedStatement.execute();
			
			resultSet = preparedStatement.getGeneratedKeys();
			
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, null);
			
		}
		
		return idGerado;
		
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
