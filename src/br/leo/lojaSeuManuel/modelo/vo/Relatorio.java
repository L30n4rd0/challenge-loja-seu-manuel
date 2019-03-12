/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.vo;

import br.leo.lojaSeuManuel.util.FormatarValor;

/**
 * @author leonardo
 *
 */
public class Relatorio {
	
	private Periodo periodo;
	
	private double valorTotalVendas;
	
	
	
	public Relatorio() {
	}
	
	
	

	/**
	 * @param periodo
	 * @param valorTotalVendas
	 */
	public Relatorio(Periodo periodo, double valorTotalVendas) {
		this.periodo = periodo;
		this.valorTotalVendas = FormatarValor.formatarDoubeParaDoisDecimais(valorTotalVendas);
	}
	
	
	
	
	
	public Periodo getPeriodo() {
		return periodo;
	}
	
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	
	public double getValorTotalVendas() {
		return valorTotalVendas;
	}
	
	public void setValorTotalVendas(double valorTotalVendas) {
		this.valorTotalVendas = valorTotalVendas;
	}
	

}
