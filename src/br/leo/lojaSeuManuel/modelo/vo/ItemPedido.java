/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.vo;

/**
 * @author leonardo
 *
 */
public class ItemPedido {
	
	private int id;
	
	private Produto produto;
	
	private int quantidade;
	
	private double preco;
	
	public ItemPedido() {
	}

	/**
	 * @param produto
	 * @param quantidade
	 * @param preco
	 */
	public ItemPedido(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.preco = this.quantidade * this.produto.getPreco();
	}

	/**
	 * @param id
	 * @param produto
	 * @param quantidade
	 * @param preco
	 */
	public ItemPedido(int id, Produto produto, int quantidade) {
		this.id = id;
		this.produto = produto;
		this.quantidade = quantidade;
		this.preco = this.quantidade * this.produto.getPreco();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
}
