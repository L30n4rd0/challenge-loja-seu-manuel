/**
 * 
 */
package br.leo.lojaSeuManuel.testes.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDao;
import br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDaoSql;
import br.leo.lojaSeuManuel.modelo.dao.PedidoDao;
import br.leo.lojaSeuManuel.modelo.dao.PedidoDaoSql;
import br.leo.lojaSeuManuel.modelo.dao.ProdutoDao;
import br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql;
import br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel;
import br.leo.lojaSeuManuel.modelo.vo.ItemPedido;
import br.leo.lojaSeuManuel.modelo.vo.Pedido;
import br.leo.lojaSeuManuel.modelo.vo.Produto;
import br.leo.lojaSeuManuel.util.EstadoPedido;
import br.leo.lojaSeuManuel.util.GeradorDados;

/**
 * @author leonardo
 *
 */
class ItemPedidoDaoSqlTeste {
	
	static ItemPedidoDao itemPedidoDao = new ItemPedidoDaoSql();
	
	static ProdutoDao produtoDao = new ProdutoDaoSql();
	
	static GeradorDados geradorDados = new GeradorDados();
	
	static PedidoDao pedidoDao = new PedidoDaoSql();

	static int chaveEstrangeiraPedido = 1;
	
	
	
	
	@BeforeAll
	public static void insereUmPedido() throws ClassNotFoundException, SQLException {
		
		// Lista do itens do pedido, não precisa ter conteúdo porque
		// a intenção é obter o id gerado no banco e atribuir à variável chaveEstrangeiraPedido
		List<ItemPedido> listaItensPedido = new ArrayList<ItemPedido>();
		
		// Pedido temporário utilizado nos testes
		Pedido pedidoTemp = new Pedido(
				"ped01", 
				new Date(System.currentTimeMillis()), 
				"Pedro Augusto", 
				EstadoPedido.APROVADO.getValor(), 
				10, 
				listaItensPedido
		);
		
		
		// Iniciando a chaveEstrangeiraPedido com o id gerado
		chaveEstrangeiraPedido = pedidoDao.inserir(pedidoTemp);
		
		
	}
	
	
	
	
	
	
	/**
	 * Cria uma no nova instância do objeto Pedido
	 * @return Um objeto Pedido
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Pedido novoPedido() throws ClassNotFoundException, SQLException {
		
		// Lista do itens do pedido
		List<ItemPedido> listaItensPedido = new ArrayList<ItemPedido>();
		
//		// Produto temporário utilizado nos testes
//		Produto produtoTemp = geradorDados.gerarNovoProduto();
//		
//		// Id do produto, gerado no banco durante a inserção
//		int idProdutoInserido = produtoDao.inserir(produtoTemp);
//		
//		// Insere o produto na lista de itens do pedido
//		ItemPedido itemPedidoTemp = new ItemPedido(idProdutoInserido, 2);
//		itemPedidoTemp.setPrecoProdutoVenda(produtoTemp.getPreco());
//		listaItensPedido.add(itemPedidoTemp);
//		
//		// Gera um segundo produto
//		produtoTemp = geradorDados.gerarNovoProduto();
//		
//		// Id do produto, gerado no banco durante a inserção
//		idProdutoInserido = produtoDao.inserir(produtoTemp);
//		
//		// Insere o produto na lista de itens do pedido
//		itemPedidoTemp = new ItemPedido(idProdutoInserido, 3);
//		itemPedidoTemp.setPrecoProdutoVenda(produtoTemp.getPreco());
//		listaItensPedido.add(itemPedidoTemp);
		
		// Pedido temporário utilizado nos testes
		Pedido pedidoTemp = new Pedido(
				"ped01", 
				new Date(System.currentTimeMillis()), 
				"Pedro Augusto", 
				EstadoPedido.APROVADO.getValor(), 
				10, 
				listaItensPedido
		);
		
		return pedidoTemp;
		
	}
	
	
	
	
	
	public ItemPedido novoItemPedido() throws ClassNotFoundException, SQLException {
		
		// Produto temporário utilizado nos testes
		Produto produtoTemp = geradorDados.gerarNovoProduto();
		
		// Id do produto, gerado no banco durante a inserção
		int idProdutoInserido = produtoDao.inserir(produtoTemp);
		
		ItemPedido itemPedidoTemp = new ItemPedido(idProdutoInserido, 2);
		
		
		// Atualiza o objeto itemPedidoTemp com os IDs gerados no banco
		// ao inserir um novo registro
		itemPedidoTemp.setIdProduto(idProdutoInserido);
		
		// Atualiza o objeto itemPedidoTemp com os dados do produto
		itemPedidoTemp.setCodigoProduto(produtoTemp.getCodigo());
		itemPedidoTemp.setNomeProduto(produtoTemp.getNome());
		itemPedidoTemp.setPrecoProdutoVenda(produtoTemp.getPreco());
//				itemPedidoTemp.atualizarValorParcial();
		
		return itemPedidoTemp;
		
	}
	
	
	
	
	
	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDaoSql#listar()}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testListar() throws ClassNotFoundException, SQLException {
		
		itemPedidoDao.inserir(novoItemPedido(), chaveEstrangeiraPedido);
		
		itemPedidoDao.inserir(novoItemPedido(), chaveEstrangeiraPedido);
		
		
		List<ItemPedido> listaItensPedidoTemp = itemPedidoDao.listar();
		
		assertNotNull(listaItensPedidoTemp);
		
		assertTrue(listaItensPedidoTemp.size() > 0);
		
		for (ItemPedido itemPedido : listaItensPedidoTemp) {
			
			assertNotNull(itemPedido);
			assertNotNull(itemPedido.getId());
			assertNotNull(itemPedido.getIdProduto());
			assertNotNull(itemPedido.getCodigoProduto());
			assertNotNull(itemPedido.getNomeProduto());
			assertNotNull(itemPedido.getPrecoProdutoVenda());
			assertNotNull(itemPedido.getQuantidade());
			assertNotNull(itemPedido.getValorParcial());
			
		}
		
	}
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDaoSql#buscarPorId(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testBuscarPorId() throws ClassNotFoundException, SQLException {
		
		assertNull(itemPedidoDao.buscarPorId(0));
		
		// Item de pedido temporário utilizado nos testes
		ItemPedido itemPedidoTemp = novoItemPedido();
		
		// Id do item de pedido, gerado no banco
		int idItemPedidoInserido = itemPedidoDao.inserir(itemPedidoTemp, chaveEstrangeiraPedido);
		
		// Busca o item inserido no banco através do id para confirmar a inserções dos dados
		ItemPedido itemBuscado = itemPedidoDao.buscarPorId(idItemPedidoInserido);
		
		// Testa se os dados retornados não são nulos
		assertNotNull(itemBuscado);
		assertNotNull(itemBuscado.getId());
		assertNotNull(itemBuscado.getIdProduto());
		assertNotNull(itemBuscado.getCodigoProduto());
		assertNotNull(itemBuscado.getNomeProduto());
		assertNotNull(itemBuscado.getPrecoProdutoVenda());
		assertNotNull(itemBuscado.getQuantidade());
		assertNotNull(itemBuscado.getValorParcial());
		
		// Atualiza com id gerado no banco
		itemPedidoTemp.setId(idItemPedidoInserido);
		
		// Testa se item pedido inserido no banco é igual (tem o mesmo conteúdo) do retornado na busca
		assertTrue(itemPedidoTemp.equals( itemBuscado ));
		
	}
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDaoSql#inserir(br.leo.lojaSeuManuel.modelo.vo.ItemPedido)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testInserir() throws ClassNotFoundException, SQLException {
		
		
		// Item de pedido temporário utilizado nos testes
		ItemPedido itemPedidoTemp = novoItemPedido();
		
		// Id do item de pedido, gerado no banco
		int idItemPedidoInserido = itemPedidoDao.inserir(itemPedidoTemp, chaveEstrangeiraPedido);
		
		// Testa se o id do item inserido não é nulo
		assertNotNull(idItemPedidoInserido);
		
		// Testa se o id do item inserido é maior que zero
		assertTrue(idItemPedidoInserido > 0);
		
	}
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDaoSql#atualizar(br.leo.lojaSeuManuel.modelo.vo.ItemPedido)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testAtualizar() throws ClassNotFoundException, SQLException {
		
		// Item de pedido temporário utilizado nos testes
		ItemPedido itemPedidoTemp = novoItemPedido();
		
		// Id do item de pedido, gerado no banco
		int idItemPedidoInserido = itemPedidoDao.inserir(itemPedidoTemp, chaveEstrangeiraPedido);
		
		// Busca o item de pedido inserido no banco através do id
		itemPedidoTemp = itemPedidoDao.buscarPorId(idItemPedidoInserido);
		
		// Realiza algumas alterações nos dados
		itemPedidoTemp.setQuantidade(15);
		
		// Aplica as alterações no banco
		itemPedidoDao.atualizar(itemPedidoTemp);
		
		// Testa se o item atualizado no banco tem os mesmos dados do item 
		// antes de inserir as alterações no banco 
		assertTrue(itemPedidoTemp.equals( itemPedidoDao.buscarPorId(idItemPedidoInserido)) );
		
	}
	
	
	

	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDaoSql#excluir(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testExcluir() throws ClassNotFoundException, SQLException {
		
		// Item de pedido temporário utilizado nos testes
		ItemPedido itemPedidoTemp = novoItemPedido();
		
		// Id do item de pedido, gerado no banco
		int idItemPedidoInserido = itemPedidoDao.inserir(itemPedidoTemp, chaveEstrangeiraPedido);
		
		// Exclui através do id o item inserido
		itemPedidoDao.excluir(idItemPedidoInserido);
		
		// Testa se o item foi realmente excluído
		assertNull(itemPedidoDao.buscarPorId(idItemPedidoInserido));
		
		
	}
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDaoSql#ebuscarPorChaveEstrangeiraPedido(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testbuscarPorChaveEstrangeiraProduto() throws ClassNotFoundException, SQLException {
		
		// Item de pedido temporário utilizado nos testes
		ItemPedido itemPedidoTemp = novoItemPedido();
		
		// Id do item de pedido, gerado no banco
		itemPedidoDao.inserir(itemPedidoTemp, chaveEstrangeiraPedido);
		
		List<ItemPedido> listaAtributosTemp = itemPedidoDao.buscarPorChaveEstrangeiraPedido(chaveEstrangeiraPedido);
		
		assertNotNull(listaAtributosTemp);
		
		assertTrue(listaAtributosTemp.size() > 0);
		
	}

}
