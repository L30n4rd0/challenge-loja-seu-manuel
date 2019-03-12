package br.leo.lojaSeuManuel.util;

import java.text.SimpleDateFormat;
import java.sql.Date;

import br.leo.lojaSeuManuel.controle.ControleRelatorio;
import br.leo.lojaSeuManuel.modelo.vo.Periodo;
import br.leo.lojaSeuManuel.modelo.vo.RelatorioTicketMedioPorVendas;

public class MainUtil {
	
	public static void main(String[] args) throws Exception {
		
			
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dataInicial = new Date(simpleDateFormat.parse("2010-01-02").getTime());
		
		System.out.println("Inicial: " + dataInicial);
		
		Date dataFinal = new Date(simpleDateFormat.parse("2017-12-10").getTime());
		
		System.out.println("Final: " + dataFinal);
		
		Periodo periodo = new Periodo(dataInicial, dataFinal);
		
		RelatorioTicketMedioPorVendas relatorioTicketMedioPorVendas = new ControleRelatorio().gerarRelatorioTicketMedioPorVendas(periodo);
		
		System.out.println(relatorioTicketMedioPorVendas.getQuantidadeVendas());
		
		System.out.println(relatorioTicketMedioPorVendas.getValorTotalVendas());
		
		System.out.println(relatorioTicketMedioPorVendas.getTicketMedio());
			
		
	}

}
