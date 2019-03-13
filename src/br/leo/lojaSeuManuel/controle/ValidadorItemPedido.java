/**
 * 
 */
package br.leo.lojaSeuManuel.controle;

import br.leo.lojaSeuManuel.modelo.dao.ProdutoDao;
import br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql;
import br.leo.lojaSeuManuel.modelo.vo.ItemPedido;
import br.leo.lojaSeuManuel.modelo.vo.Produto;

/**
 * @author leonardo
 *
 */
public class ValidadorItemPedido {
	
	private Produto produto;
	private ProdutoDao produtoDao;
	
	/**
	 * @param produtoDao
	 */
	public ValidadorItemPedido() {
		
		this.produtoDao = new ProdutoDaoSql();
		
	}


	protected void validarItemPedido(ItemPedido itemPedido) throws Exception {
		
		validarIdProduto(itemPedido.getIdProduto());
		
		ValidadorProduto validadorProduto = new ValidadorProduto();
		
		validadorProduto.validarProduto(produto);
		
		atualizarItemComDadosDoProduto(itemPedido);
		
		validarQuantidade(itemPedido.getQuantidade());
		
		atualizarEstoque(produto, itemPedido.getQuantidade());
		
		validarValorParcial(itemPedido.getValorParcial());
		
		
	}


	private void atualizarEstoque(Produto produto, int quantidade) throws Exception {
		
		produto.setEstoque(produto.getEstoque() - quantidade);
		
		produtoDao.atualizar(produto);
		
	}


	private void atualizarItemComDadosDoProduto(ItemPedido itemPedido) {
		
		// Coloca o preço do produto cadastrado no banco e coloca no preço de venda
		// Este método também atualiza o valor parcial do item através do interno atualizarValorParcial()
		itemPedido.setPrecoProdutoVenda( produto.getPreco() );
		
		// Atualiza o objeto itemPedido com os dados do produto
		itemPedido.setCodigoProduto( produto.getCodigo() );
		itemPedido.setNomeProduto( produto.getNome() );
		
	}


	private void validarValorParcial(double valorParcial) throws Exception {
		
		if (valorParcial <= 0) {
			
			throw new Exception("O valor parcial do item de pedido não pode ser zero!");
			
		}
		
	}


	private void validarQuantidade(int quantidade) throws Exception {
		
		if (quantidade <= 0) {
			
			throw new Exception("O valor quantidade do item de pedido não pode ser zero!");
			
		} else if (produto.getEstoque() - quantidade < 0) {
			
			throw new Exception(
					"Não existe estoque suficiente para o produto:" + 
					"\nCodigo do produto: " + produto.getCodigo() +
					"\nID do produto: " + produto.getId() +
					"\n\nQuantidade desejada: " + quantidade + 
					"\nQuantidade disponível em estoque: " + produto.getEstoque()
			);
			
		}
		
	}



	private void validarIdProduto(int idProduto) throws Exception {
		
		if (idProduto <= 0) {
			
			throw new Exception("O id do produto não pode ser zero!");
			
		}
		
		produto = produtoDao.buscarPorId(idProduto);
		
		if (produto == null) {
			
			throw new Exception("O produto de id: " + idProduto + " não está cadastrado");
			
		} 
		
	}
	

}
