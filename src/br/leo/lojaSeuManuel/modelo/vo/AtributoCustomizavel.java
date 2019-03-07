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

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		AtributoCustomizavel atributoTemp = (AtributoCustomizavel) obj;
		
		if (this.id != atributoTemp.getId())
			return false;
		
		if (!this.nome.equals(atributoTemp.getNome()))
			return false;
		
		if (!this.valor.equals(atributoTemp.getValor()))
			return false;
		
		return true;
	}
	
}
