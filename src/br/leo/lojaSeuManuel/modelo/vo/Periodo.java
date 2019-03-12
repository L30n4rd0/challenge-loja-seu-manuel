/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.vo;

import java.sql.Date;

/**
 * @author leonardo
 *
 */
public class Periodo {
	
	private Date dataInicial;
	
	private Date dataFinal;

	/**
	 * @param dataInicial
	 * @param dataFinal
	 */
	public Periodo(Date dataInicial, Date dataFinal) {
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}
	
	
	

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	

}
