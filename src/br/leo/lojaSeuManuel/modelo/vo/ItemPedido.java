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
	
	private double precoProdutoVenda;
	
	private int quantidade;
	
	private double valorParcial;
	
	
	
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
	
	
	
	
	
	/**
	 * @param id
	 * @param idProduto
	 * @param codigoProduto
	 * @param nomeProduto
	 * @param precoProdutoVenda
	 * @param quantidade
	 * @param valorParcial
	 */
	public ItemPedido(int id, int idProduto, String codigoProduto, String nomeProduto, double precoProdutoVenda,
			int quantidade, double valorParcial) {
		this.id = id;
		this.idProduto = idProduto;
		this.codigoProduto = codigoProduto;
		this.nomeProduto = nomeProduto;
		this.precoProdutoVenda = precoProdutoVenda;
		this.quantidade = quantidade;
		this.valorParcial = valorParcial;
	}




	private void atualizarValorParcial() {
		this.valorParcial = this.quantidade * this.precoProdutoVenda;
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

	public double getPrecoProdutoVenda() {
		return precoProdutoVenda;
	}

	public void setPrecoProdutoVenda(double precoProduto) {
		this.precoProdutoVenda = precoProduto;
		atualizarValorParcial();
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
		atualizarValorParcial();
	}

	public double getValorParcial() {
		return valorParcial;
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
		
		if (this.precoProdutoVenda != itemPedidoTemp.getPrecoProdutoVenda())
			return false;
		
		if (this.quantidade != itemPedidoTemp.quantidade)
			return false;
		
		if (this.valorParcial != itemPedidoTemp.getValorParcial())
			return false;
		
		return true;
		
	}
	
}
