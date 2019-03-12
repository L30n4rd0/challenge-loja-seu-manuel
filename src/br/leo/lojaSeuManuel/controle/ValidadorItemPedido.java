/**
 * 
 */
package br.leo.lojaSeuManuel.controle;

import br.leo.lojaSeuManuel.modelo.vo.ItemPedido;
import br.leo.lojaSeuManuel.modelo.vo.Produto;

/**
 * @author leonardo
 *
 */
public class ValidadorItemPedido {
	
	private Produto produto;
	
	/**
	 * @param produtoDao
	 */
	public ValidadorItemPedido(Produto produto) {
		this.produto = produto;
	}


	protected void validarItemPedido(ItemPedido itemPedido) throws Exception {
		
		validarIdProduto(itemPedido.getIdProduto());
		
		ValidadorProduto validadorProduto = new ValidadorProduto();
		
		validadorProduto.validarProduto(produto);
		
		validarQuantidade(itemPedido.getQuantidade());
		
		validarValorParcial(itemPedido.getValorParcial());
		
		
	}


	private void validarValorParcial(double valorParcial) throws Exception {
		
		if (valorParcial <= 0) {
			
			throw new Exception("O valor parcial do item de pedido não pode ser zero!");
			
		}
		
	}


	private void validarQuantidade(int quantidade) throws Exception {
		
		if (quantidade <= 0) {
			
			throw new Exception("O valor parcial do item de pedido não pode ser zero!");
			
		} else if (produto.getEstoque() - quantidade < 0) {
			
			throw new Exception(
					"Não existe estoque suficiente para o produto: " + produto.getCodigo() +
					"\n\nQuantidade desejada: " + quantidade + 
					"\nQuantidade disponível em estoque: " + produto.getEstoque()
			);
			
		}
		
	}



	private void validarIdProduto(int idProduto) throws Exception {
		
		if (idProduto <= 0) {
			
			throw new Exception("O id do produto não pode ser zero!");
			
		}
		
	}
	

}
