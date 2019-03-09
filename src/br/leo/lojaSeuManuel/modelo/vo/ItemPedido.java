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
	
	private int idProduto;
	
	private String codigoProduto;
	
	private String nomeProduto;
	
	private double precoProduto;
	
	private int quantidade;
	
	private double preco;
	
	
	
	public ItemPedido() {
	}
	
	
	
	
	/**
	 * @param idProduto
	 * @param quantidade
	 */
	public ItemPedido(int idProduto, int quantidade) {
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}
	
	
	
	
	/**
	 * @param id
	 * @param idProduto
	 * @param quantidade
	 */
	public ItemPedido(int id, int idProduto, int quantidade) {
		this.id = id;
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}
	

	
	
	
	public void atualizarPreco() {
		this.preco = this.quantidade * this.precoProduto;
	}
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(double precoProduto) {
		this.precoProduto = precoProduto;
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
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		ItemPedido itemPedidoTemp = (ItemPedido) obj;
		
		if (this.id != itemPedidoTemp.getId())
			return false;
		
		if (this.idProduto != itemPedidoTemp.getIdProduto())
			return false;
		
		if (!this.codigoProduto.equals(itemPedidoTemp.getCodigoProduto()))
			return false;
		
		if (!this.nomeProduto.equals(itemPedidoTemp.getNomeProduto()))
			return false;
		
		if (this.precoProduto != itemPedidoTemp.getPrecoProduto())
			return false;
		
		if (this.quantidade != itemPedidoTemp.quantidade)
			return false;
		
		if (this.preco != itemPedidoTemp.getPreco())
			return false;
		
		return true;
		
	}
	
}
