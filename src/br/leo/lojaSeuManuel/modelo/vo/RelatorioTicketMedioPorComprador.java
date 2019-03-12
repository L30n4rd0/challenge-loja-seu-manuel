/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.vo;

import br.leo.lojaSeuManuel.util.FormatarValor;

/**
 * @author leonardo
 *
 */
public class RelatorioTicketMedioPorComprador extends Relatorio {
	
	private int quantidadeCompradores;
	
	private double ticketMedio;

	
	
	
	public RelatorioTicketMedioPorComprador() {
	}
	
	
	
	
	
	/**
	 * @param periodo
	 * @param valorTotalCompradores
	 * @param quantidadeCompradores
	 */
	public RelatorioTicketMedioPorComprador(Periodo periodo, double valorTotalCompradores, int quantidadeCompradores) {
		super(periodo, valorTotalCompradores);
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
