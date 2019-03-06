/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.vo;

/**
 * @author leonardo
 * 
 */
public class AtributoCustomizavel {
	
	private int id;
	
	private String nome;
	
	private String valor;

	/**
	 * 
	 */
	public AtributoCustomizavel() {
	}

	/**
	 * @param nome
	 * @param valor
	 */
	public AtributoCustomizavel(String nome, String valor) {
		this.nome = nome;
		this.valor = valor;
	}

	/**
	 * @param id
	 * @param nome
	 * @param valor
	 */
	public AtributoCustomizavel(int id, String nome, String valor) {
		this.id = id;
		this.nome = nome;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
