/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.vo;

import java.util.List;

/**
 * @author leonardo
 *
 */
public class Produto {
	
	private int id;
	
	private String codigo;
	
	private String nome;
	
	private String descricao;
	
	private int estoque;
	
	private double preco;
	
	private List<AtributoCustomizavel> atributosCustomizaveis;
	
	public Produto() {
	}

	/**
	 * @param codigo
	 * @param nome
	 * @param descricao
	 * @param estoque
	 * @param preco
	 * @param atributosCustomizaveis
	 */
	public Produto(String codigo, String nome, String descricao, int estoque, double preco,
			List<AtributoCustomizavel> atributosCustomizaveis) {
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.estoque = estoque;
		this.preco = preco;
		this.atributosCustomizaveis = atributosCustomizaveis;
	}

	/**
	 * @param id
	 * @param codigo
	 * @param nome
	 * @param descricao
	 * @param estoque
	 * @param preco
	 * @param atributosCustomizaveis
	 */
	public Produto(int id, String codigo, String nome, String descricao, int estoque, double preco,
			List<AtributoCustomizavel> atributosCustomizaveis) {
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.estoque = estoque;
		this.preco = preco;
		this.atributosCustomizaveis = atributosCustomizaveis;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public List<AtributoCustomizavel> getAtributosCustomizaveis() {
		return atributosCustomizaveis;
	}

	public void setAtributosCustomizaveis(List<AtributoCustomizavel> atributosCustomizaveis) {
		this.atributosCustomizaveis = atributosCustomizaveis;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Produto produtoTemp = (Produto) obj;
		
		if (!this.nome.equals(produtoTemp.getNome()))
			return false;
			
		if (this.id != produtoTemp.getId())
			return false;
		
		if (!this.codigo.equals(produtoTemp.getCodigo()))
			return false;
		
		
		if (!this.descricao.equals(produtoTemp.getDescricao()))
			return false;
		
		if (this.estoque != produtoTemp.getEstoque())
			return false;
		
		if (this.preco != produtoTemp.getPreco())
			return false;
		
		if (this.atributosCustomizaveis.size() != produtoTemp.getAtributosCustomizaveis().size())
			return false;
		
		for (int i = 0; i < this.atributosCustomizaveis.size(); i++) {
			
			if ( !this.atributosCustomizaveis.get(i).equals(produtoTemp.getAtributosCustomizaveis().get(i)) )
				return false;
			
		}
		
		return true;
		
	}
	
	
	
}
