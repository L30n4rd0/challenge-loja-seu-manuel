/**
 * 
 */
package br.leo.lojaSeuManuel.testes.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.leo.lojaSeuManuel.modelo.dao.PedidoDao;
import br.leo.lojaSeuManuel.modelo.dao.PedidoDaoSql;
import br.leo.lojaSeuManuel.modelo.dao.ProdutoDao;
import br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql;
import br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel;
import br.leo.lojaSeuManuel.modelo.vo.ItemPedido;
import br.leo.lojaSeuManuel.modelo.vo.Pedido;
import br.leo.lojaSeuManuel.modelo.vo.Produto;
import br.leo.lojaSeuManuel.util.EstadoPedido;

/**
 * @author leonardo
 *
 */
class PedidoDaoSqlTeste {
	
	////////////////////////////////////////////////////////////////////////////
	// Atributos estáticos
	////////////////////////////////////////////////////////////////////////////
	
	static PedidoDao pedidoDao = new PedidoDaoSql();
	
	static ProdutoDao produtoDao = new ProdutoDaoSql();
	
	
	
	
	/**
	 * Cria uma no nova instância do objeto Pedido
	 * @return Um objeto Pedido
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Pedido novoPedido() throws ClassNotFoundException, SQLException {
		
		// Lista do itens do pedido
		List<ItemPedido> listaItensPedido = new ArrayList<ItemPedido>();
		
		// Lista de atributos extras do produto
		List<AtributoCustomizavel> atributosExtras = new ArrayList<AtributoCustomizavel>();
		
		atributosExtras.add(new AtributoCustomizavel("cor", "amarelo"));
		atributosExtras.add(new AtributoCustomizavel("cor", "azul"));
		atributosExtras.add(new AtributoCustomizavel("peso", "20g"));
		
		// Produto temporário utilizado nos testes
		Produto produtoTemp = new Produto("prod01", "dvd", "dvd de sertanejo", 8, 10, atributosExtras);
		
		// Id do produto, gerado no banco durante a inserção
		int idProdutoInserido = produtoDao.inserir(produtoTemp);
		
		// Insere o produto na lista de itens do pedido
		ItemPedido itemPedidoTemp = new ItemPedido(idProdutoInserido, 2);
		itemPedidoTemp.setPrecoProdutoVenda(produtoTemp.getPreco());
		listaItensPedido.add(itemPedidoTemp);
		
		// Atualiza os atributos do produto para inserir um segundo produto no banco
		produtoTemp.setCodigo("prod02");
		produtoTemp.setDescricao("dvd pagode");
		produtoTemp.setPreco(5);
		produtoTemp.setEstoque(20);
		
		// Id do produto, gerado no banco durante a inserção
		idProdutoInserido = produtoDao.inserir(produtoTemp);
		
		// Insere o produto na lista de itens do pedido
		itemPedidoTemp = new ItemPedido(idProdutoInserido, 3);
		itemPedidoTemp.setPrecoProdutoVenda(produtoTemp.getPreco());
		listaItensPedido.add(itemPedidoTemp);
		
		// Pedido temporário utilizado nos testes
		Pedido pedidoTemp = new Pedido(
				"ped01", 
				new Date(System.currentTimeMillis()), 
				"pedro", 
				EstadoPedido.APROVADO.getValor(), 
				10, 
				listaItensPedido
		);
		
		return pedidoTemp;
		
	}
	
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.PedidoDaoSql#listar()}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testListar() throws ClassNotFoundException, SQLException {
		
		// Insere alguns pedidos no banco
		pedidoDao.inserir(novoPedido());
		pedidoDao.inserir(novoPedido());
		
		// Busca a lista de pedido
		List<Pedido> listaPedidosTemp = pedidoDao.listar();

		// Testa se a lista não é nula
		assertNotNull(listaPedidosTemp);
		
		// Testa se a lista tem ao menos 1 elemento
		assertTrue(listaPedidosTemp.size() > 0);
		
		
	}
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.PedidoDaoSql#buscarPorId(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testBuscarPorId() throws ClassNotFoundException, SQLException {
		
		// Testa a busca de pedido que naão existe no banco
		assertNull(pedidoDao.buscarPorId(0));
		
		// Pedido temporário utilizado nos testes
		Pedido pedidoTemp = novoPedido();
		
		// Id do item de pedido, gerado no banco
		int idPedidoInserido = pedidoDao.inserir(pedidoTemp);
		
		// Busca o item inserido no banco através do id para confirmar a inserções dos dados
		Pedido pedidoBuscado = pedidoDao.buscarPorId(idPedidoInserido);
		
		// Testa se os dados retornados não são nulos
		assertNotNull(pedidoBuscado);
		assertNotNull(pedidoBuscado.getCodigo());
		assertNotNull(pedidoBuscado.getDataCompra());
		assertNotNull(pedidoBuscado.getEstado());
		assertNotNull(pedidoBuscado.getId());
		assertNotNull(pedidoBuscado.getItensDoPedido());
		assertNotNull(pedidoBuscado.getNomeComprador());
		assertNotNull(pedidoBuscado.getValorFrete());
		assertNotNull(pedidoBuscado.getValorTotal());
		
//		// Atualiza o pedidoBusca e insere no banco como um novo pedido
//		pedidoTemp.setCodigo("ped02");
//		pedidoTemp.setIdProduto(idProdutoInserido);
//		
//		// Atualiza o objeto pedidoTemp com os dados do produto
//		pedidoTemp.setCodigoProduto(produtoTemp.getCodigo());
//		pedidoTemp.setNomeProduto(produtoTemp.getNome());
//		pedidoTemp.setPrecoProduto(produtoTemp.getPreco());
//		pedidoTemp.atualizarPreco();
//		
//		// Testa se item pedido inserido no banco é igual (tem o mesmo conteúdo) do retornado na busca
//		assertTrue(pedidoTemp.equals( pedidoBuscado )); 
		
	}
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.PedidoDaoSql#inserir(br.leo.lojaSeuManuel.modelo.vo.Pedido)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testInserir() throws ClassNotFoundException, SQLException {
		
		// Id do pedido, gerado no banco
		int idPedidoInserido = pedidoDao.inserir(novoPedido());
		
		// Teste se o id pedido inserido não é nulo
		assertNotNull(idPedidoInserido);
		
		// Testa se o id do pedido inserido é maior que zero
		assertTrue(idPedidoInserido > 0);
		
	}
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.PedidoDaoSql#atualizar(br.leo.lojaSeuManuel.modelo.vo.Pedido)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testAtualizar() throws ClassNotFoundException, SQLException {
		
		// Pedido temporário utilizado nos testes
		Pedido pedidoTemp = novoPedido();
		
		// Id do pedido, gerado no banco
		int idPedidoInserido = pedidoDao.inserir(pedidoTemp);
		
		// Busca o pedido inserido no banco através do id
		pedidoTemp = pedidoDao.buscarPorId(idPedidoInserido);
		
		// Realiza algumas alterações nos dados
		pedidoTemp.setEstado(EstadoPedido.ENTREGUE.getValor());
		pedidoTemp.setNomeComprador("lima");
		pedidoTemp.setValorFrete(14);
		
		// Aplica as alterações no banco
		pedidoDao.atualizar(pedidoTemp);
		
		// Testa se o item atualizado no banco tem os mesmos dados do item 
		// antes de inserir as alterações no banco 
		assertTrue(pedidoTemp.equals( pedidoDao.buscarPorId(idPedidoInserido)) );
		
		
		
	}
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.PedidoDaoSql#excluir(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testExcluir() throws ClassNotFoundException, SQLException {
		
		// Id do pedido, gerado no banco
		int idPedidoInserido = pedidoDao.inserir(novoPedido());
		
		// Exclui através item inserido através do id
		pedidoDao.excluir(idPedidoInserido);
		
		// Testa se o pedido foi realmente excluído
		assertNull(pedidoDao.buscarPorId(idPedidoInserido));
		
		
		
	}
	
	

}
