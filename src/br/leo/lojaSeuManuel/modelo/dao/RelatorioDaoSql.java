package br.leo.lojaSeuManuel.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.leo.lojaSeuManuel.modelo.dao.conexao.ConexaoSql;
import br.leo.lojaSeuManuel.modelo.vo.Periodo;
import br.leo.lojaSeuManuel.modelo.vo.RelatorioTicketMedioPorComprador;
import br.leo.lojaSeuManuel.modelo.vo.RelatorioTicketMedioPorVendas;

public class RelatorioDaoSql implements RelatorioDao {

	
	
	@Override
	public RelatorioTicketMedioPorVendas gerarRelatorioTicketMedioPorVendas(Periodo periodo)
			throws ClassNotFoundException, SQLException {
		
		PreparedStatement preparedStatement = null;
		
		Connection connection = null;
		
		ResultSet resultSet = null;
		
		String stringSQL = "SELECT COUNT(*) AS quantidade_vendas, SUM(valor_total) AS valor_total_vendas FROM pedido WHERE data_compra BETWEEN ? AND ?";
		
		RelatorioTicketMedioPorVendas relatorioVendas = null;
		
		try {
			connection = ConexaoSql.getConnection();
			
			preparedStatement = connection.prepareStatement(stringSQL);
			
			preparedStatement.setDate(1, periodo.getDataInicial());
			preparedStatement.setDate(2, periodo.getDataFinal());
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				relatorioVendas = new RelatorioTicketMedioPorVendas(
						
						periodo, 
						resultSet.getDouble("valor_total_vendas"), 
						resultSet.getInt("quantidade_vendas")
						
						);
				
			}
				
		} catch (SQLException sqlException) {
			
			throw new SQLException("Erro ao gerar relatório" + "\n\n" + sqlException.getMessage());
			
		} catch (ClassNotFoundException classNotFoundException) {
			
			throw new SQLException("Erro ao gerar relatório" + "\n\n" + classNotFoundException.getMessage());
			
		} finally {
			
			ConexaoSql.closeConnection(connection, preparedStatement, resultSet);
			
		}
		
		return relatorioVendas;
	}

	
	
	
	@Override
	public RelatorioTicketMedioPorComprador gerarRelatorioTicketMedioPorCompradores(Periodo periodo)
			throws ClassNotFoundException, SQLException {
		return null;
	}

}
