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
import br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel;

/**
 * @author leonardo
 *
 */
public class AtributoCustomizavelDaoSql implements AtributoCustomizavelDao {

	
	
	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDao#listar()
	 */
	@Override
	public List<AtributoCustomizavel> listar() throws ClassNotFoundException, SQLException {
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		ResultSet resultSet = null;
		
		String stringSQL="SELECT * FROM atributo_customizavel";
		
		List<AtributoCustomizavel> listaDeAtributos = null;
		
		try {
			connection = ConexaoSql.getConnection();
			
			preparedStatement = connection.prepareStatement(stringSQL);
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				if (listaDeAtributos == null) {
					
					listaDeAtributos = new ArrayList<AtributoCustomizavel>();
					
				}
				
				listaDeAtributos.add(
						new AtributoCustomizavel(
								resultSet.getInt("id_atributo"), 
								resultSet.getString("nome"), 
								resultSet.getString("valor")
						)
				);
				
			}
				
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, resultSet);
			
		}
		
		return listaDeAtributos;
		
	}

	
	
	
	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDao#buscaPorId(int)
	 */
	@Override
	public AtributoCustomizavel buscaPorId(int id) throws ClassNotFoundException, SQLException {

		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		ResultSet resultSet = null;
		
		String stringSQL="SELECT * FROM atributo_customizavel WHERE id_atributo = ?";
		
		AtributoCustomizavel atributoCustomizavel = null;
		
		try {
			
			connection = ConexaoSql.getConnection();
			
			preparedStatement = connection.prepareStatement(stringSQL);
			
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				
				atributoCustomizavel = new AtributoCustomizavel(
						resultSet.getInt("id_atributo"), 
						resultSet.getString("nome"), 
						resultSet.getString("valor")
				);
			}
				
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, resultSet);
			
		}
		
		return atributoCustomizavel;
	}
	
	
	
	

	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDao#adicionar(br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel)
	 */
	@Override
	public int adicionar(AtributoCustomizavel atributoCustomizavel, int chaveEstrangeiraProduto) throws SQLException, ClassNotFoundException {
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		ResultSet resultSet = null;
		
		int idGerado = 0;
		
		String stringSQL="INSERT INTO atributo_customizavel (nome, valor, fk_id_produto) VALUES (?,?,?)";
		
		try {
			
			connection = ConexaoSql.getConnection();
			preparedStatement = connection.prepareStatement(stringSQL, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, atributoCustomizavel.getNome());
			preparedStatement.setString(2, atributoCustomizavel.getValor());
			preparedStatement.setInt(3, chaveEstrangeiraProduto);
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
	 * @see br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDao#editar(br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel)
	 */
	@Override
	public void editar(AtributoCustomizavel atributoCustomizavel) throws ClassNotFoundException, SQLException {
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		String stringSQL="UPDATE atributo_customizavel SET nome = ?, valor = ? WHERE id_atributo = ?";
		
		try {
			
			connection = ConexaoSql.getConnection();
			preparedStatement = connection.prepareStatement(stringSQL);
			
			preparedStatement.setString(1, atributoCustomizavel.getNome());
			preparedStatement.setString(2, atributoCustomizavel.getValor());
			preparedStatement.setInt(3, atributoCustomizavel.getId());
			
			preparedStatement.execute();
			
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, null);
			
		}

	}
	
	
	
	

	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDao#excluir(int)
	 */
	@Override
	public void excluir(int id) throws ClassNotFoundException, SQLException {
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		String stringSQL="DELETE FROM atributo_customizavel WHERE id_atributo = ?";
		
		try {
			
			connection = ConexaoSql.getConnection();
			preparedStatement = connection.prepareStatement(stringSQL);
			
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, null);
			
		}

	}






	@Override
	public List<AtributoCustomizavel> buscaPorChaveEstrangeiraProduto(int chaveEstrangeiraProduto)
			throws ClassNotFoundException, SQLException {
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		ResultSet resultSet = null;
		
		String stringSQL = "SELECT * FROM atributo_customizavel WHERE fk_id_produto = ?";
		
		List<AtributoCustomizavel> listaDeAtributos = null;
		
		try {
			connection = ConexaoSql.getConnection();
			
			preparedStatement = connection.prepareStatement(stringSQL);
			
			preparedStatement.setInt(1, chaveEstrangeiraProduto);
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				if (listaDeAtributos == null) {
					
					listaDeAtributos = new ArrayList<AtributoCustomizavel>();
					
				}
				
				listaDeAtributos.add(
						new AtributoCustomizavel(
								resultSet.getInt("id_atributo"), 
								resultSet.getString("nome"), 
								resultSet.getString("valor")
						)
				);
				
			}
				
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, resultSet);
			
		}
		
		return listaDeAtributos;
		
		
	}

}
