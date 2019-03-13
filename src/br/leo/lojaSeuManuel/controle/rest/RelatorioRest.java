/**
 * 
 */
package br.leo.lojaSeuManuel.controle.rest;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.leo.lojaSeuManuel.controle.ControleRelatorio;
import br.leo.lojaSeuManuel.modelo.vo.Periodo;
import br.leo.lojaSeuManuel.modelo.vo.RelatorioTicketMedioPorCompradores;
import br.leo.lojaSeuManuel.modelo.vo.RelatorioTicketMedioPorVendas;


/////////////////
//A configuração do serviço REST da biblioteca jersey 
//encontra-se no arquivo WebContent/WEB-INF/web.xml
////////////////



/**
 * @author leonardo
 *
 */
@Path("/relatorios")
public class RelatorioRest {
	
	private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	private ControleRelatorio controleRelatorio;
	
	
	
	
	@PostConstruct
	private void init() {
		
		this.controleRelatorio = new ControleRelatorio();
		
	}
	
	
	
	
	
	
	@POST
	@Path("/ticketmedioporvendas")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public RelatorioTicketMedioPorVendas ticketMedioPorVendas(Periodo periodo) {
		
		RelatorioTicketMedioPorVendas relatorioRetorno = null;
		
		try {
			
			relatorioRetorno = controleRelatorio.gerarRelatorioTicketMedioPorVendas(periodo);
			
		} catch (Exception exception) {
			
			exception.printStackTrace();
			
		}
		
		System.out.println("Executou o REST ticketMedioPorVendas.");
		
		return relatorioRetorno;
		
	}
	
	
	
	
	@POST
	@Path("/ticketmedioporcompradores")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public RelatorioTicketMedioPorCompradores ticketMedioPorCompradores(Periodo periodo) {
		
		RelatorioTicketMedioPorCompradores relatorioRetorno = null;
		
		try {
			
			relatorioRetorno = controleRelatorio.gerarRelatorioTicketMedioPorCompradores(periodo);
			
		} catch (Exception exception) {
			
			exception.printStackTrace();
			
		}
		
		System.out.println("Executou o REST ticketMedioPorCompradores.");
		
		return relatorioRetorno;
		
	}

}
