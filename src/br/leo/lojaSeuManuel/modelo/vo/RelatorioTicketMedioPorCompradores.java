/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.vo;

import br.leo.lojaSeuManuel.util.FormatarValor;

/**
 * @author leonardo
 *
 */
public class RelatorioTicketMedioPorCompradores extends Relatorio {
	
	private int quantidadeCompradores;
	
	private double ticketMedio;

	
	
	
	public RelatorioTicketMedioPorCompradores() {
	}
	
	
	
	
	
	/**
	 * @param periodo
	 * @param valorTotalVendas
	 * @param quantidadeCompradores
	 */
	public RelatorioTicketMedioPorCompradores(Periodo periodo, double valorTotalVendas, int quantidadeCompradores) {
		super(periodo, valorTotalVendas);
		this.quantidadeCompradores = quantidadeCompradores;
		calcularTicketMedio();
	}
	
	
	
	
	
	
	
	public void calcularTicketMedio() {
		
		this.ticketMedio = this.getValorTotalVendas() / this.quantidadeCompradores;
		
		this.ticketMedio = FormatarValor.formatarDoubeParaDoisDecimais(this.ticketMedio);
		
	}

	public double getTicketMedio() {
		return ticketMedio;
	}

	public void setTicketMedio(double ticketMedio) {
		this.ticketMedio = ticketMedio;
	}

	public int getQuantidadeCompradores() {
		return quantidadeCompradores;
	}

	public void setQuantidadeCompradores(int quantidadeCompradores) {
		this.quantidadeCompradores = quantidadeCompradores;
	}

	

}
