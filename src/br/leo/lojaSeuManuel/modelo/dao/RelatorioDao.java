package br.leo.lojaSeuManuel.modelo.dao;

import java.sql.SQLException;

import br.leo.lojaSeuManuel.modelo.vo.Periodo;
import br.leo.lojaSeuManuel.modelo.vo.RelatorioTicketMedioPorCompradores;
import br.leo.lojaSeuManuel.modelo.vo.RelatorioTicketMedioPorVendas;

public interface RelatorioDao {
	
	public RelatorioTicketMedioPorVendas gerarRelatorioTicketMedioPorVendas(Periodo periodo) throws ClassNotFoundException, SQLException;
	
	public RelatorioTicketMedioPorCompradores gerarRelatorioTicketMedioPorCompradores(Periodo periodo) throws ClassNotFoundException, SQLException;

}
