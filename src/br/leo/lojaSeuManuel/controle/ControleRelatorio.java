/**
 * 
 */
package br.leo.lojaSeuManuel.controle;

import br.leo.lojaSeuManuel.modelo.dao.RelatorioDao;
import br.leo.lojaSeuManuel.modelo.dao.RelatorioDaoSql;
import br.leo.lojaSeuManuel.modelo.vo.Periodo;
import br.leo.lojaSeuManuel.modelo.vo.RelatorioTicketMedioPorCompradores;
import br.leo.lojaSeuManuel.modelo.vo.RelatorioTicketMedioPorVendas;

/**
 * @author leonardo
 *
 */
public class ControleRelatorio {
	
	private RelatorioDao relatorioDao;
	
	public ControleRelatorio() {
		
		this.relatorioDao = new RelatorioDaoSql();
		
	}
	
	
	
	
	
	public RelatorioTicketMedioPorVendas gerarRelatorioTicketMedioPorVendas(Periodo periodo) throws Exception {
		
		return relatorioDao.gerarRelatorioTicketMedioPorVendas(periodo);
		
	}
	
	
	
	
	
	public RelatorioTicketMedioPorCompradores gerarRelatorioTicketMedioPorCompradores(Periodo periodo) throws Exception {
		
		return relatorioDao.gerarRelatorioTicketMedioPorCompradores(periodo);
		
	}
	
	
	
	
}
