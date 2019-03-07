/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.dao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author leonardo
 *
 */
public class ConexaoSql {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException  {
		Connection connection = null;
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/bd_loja_seu_manuel","root", "");
		
		return connection;
		
	}
	
	public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
		
		if (resultSet != null) 
			resultSet.close();
		
		if (statement != null) 
			statement.close();
		
		if (connection != null) 
			connection.close();
		
//		throw new SQLException("SQLException: Erro ao fechar conexao Banco de dados");
			
	}

}
