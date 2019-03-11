package br.leo.lojaSeuManuel.controle;

import java.util.List;

import br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql;
import br.leo.lojaSeuManuel.modelo.vo.Pedido;


/**
 * @author leonardo
 *
 */
public class ControlePedido {
	

	public List<Pedido> listar() {
		return null;
	}

	public Pedido buscarPorId(int id) {
		return null;
	}

	public String inserir(Pedido pedido) {
		
		// Busca o preço do produto e coloca no preço de venda
//		itemPedido.setPrecoProdutoVenda( new ProdutoDaoSql().buscarPorId( itemPedido.getIdProduto() ).getPreco() );
		
		// Atualiza o valor parcial do item 
		// valorParcial = quantidade * precoProdutoVenda
//					itemPedido.atualizarValorParcial();
		return null;
	}

	public String atualizar(Pedido pedido) {
		
		// Busca o preço do produto e coloca no preço de venda
//		itemPedido.setPrecoProdutoVenda( new ProdutoDaoSql().buscarPorId( itemPedido.getIdProduto() ).getPreco() );
		
		// Atualiza o valor parcial do item 
		// valorParcial = quantidade * precoProdutoVenda
//					itemPedido.atualizarValorParcial();
		return null;

	}

	public String excluir(int id) {
		return null;

	}

}
