/**
 * 
 */
package br.leo.lojaSeuManuel.testes.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDao;
import br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDaoSql;
import br.leo.lojaSeuManuel.modelo.dao.ProdutoDao;
import br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql;
import br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel;
import br.leo.lojaSeuManuel.modelo.vo.ItemPedido;
import br.leo.lojaSeuManuel.modelo.vo.Produto;

/**
 * @author leonardo
 *
 */
class ItemPedidoDaoSqlTeste {
	
	static ItemPedidoDao itemPedidoDao = new ItemPedidoDaoSql();
	
	static ProdutoDao produtoDao = new ProdutoDaoSql();

	int chaveEstrangeiraPedido = 1;
	
	
	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDaoSql#listar()}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testListar() throws ClassNotFoundException, SQLException {
		
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
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDaoSql#buscaPorId(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testBuscaPorId() throws ClassNotFoundException, SQLException {
		
		assertNull(itemPedidoDao.buscaPorId(0));
		
		// Lista de atributos extras do produto
		List<AtributoCustomizavel> atributosExtras = new ArrayList<AtributoCustomizavel>();
		
		atributosExtras.add(new AtributoCustomizavel("cor", "amarelo"));
		atributosExtras.add(new AtributoCustomizavel("cor", "azul"));
		atributosExtras.add(new AtributoCustomizavel("peso", "2kg"));
		
		// Produto temporário utilizado nos testes
		Produto produtoTemp = new Produto("prod01", "dvd", "dvd de sertanejo", 8, 10, atributosExtras);
		
		// Id do produto, gerado no banco durante a inserção
		int idProdutoInserido = produtoDao.adicionar(produtoTemp);
		
		// Item de pedido temporário utilizado nos testes
		ItemPedido itemPedidoTemp = new ItemPedido(idProdutoInserido, 20);
		
		// Id do item de pedido, gerado no banco
		int idItemPedidoInserido = itemPedidoDao.adicionar(itemPedidoTemp, chaveEstrangeiraPedido);
		
		// Busca o item inserido no banco através do id para confirmar a inserções dos dados
		ItemPedido itemBuscado = itemPedidoDao.buscaPorId(idItemPedidoInserido);
		
		// Testa se os dados retornados não são nulos
		assertNotNull(itemBuscado);
		assertNotNull(itemBuscado.getId());
		assertNotNull(itemBuscado.getIdProduto());
		assertNotNull(itemBuscado.getCodigoProduto());
		assertNotNull(itemBuscado.getNomeProduto());
		assertNotNull(itemBuscado.getPrecoProdutoVenda());
		assertNotNull(itemBuscado.getQuantidade());
		assertNotNull(itemBuscado.getValorParcial());
		
		// Atualiza o objeto itemPedidoTemp com os IDs gerados no banco
		// ao adicionar um novo registro
		itemPedidoTemp.setId(idItemPedidoInserido);
		itemPedidoTemp.setIdProduto(idProdutoInserido);
		
		// Atualiza o objeto itemPedidoTemp com os dados do produto
		itemPedidoTemp.setCodigoProduto(produtoTemp.getCodigo());
		itemPedidoTemp.setNomeProduto(produtoTemp.getNome());
		itemPedidoTemp.setPrecoProdutoVenda(produtoTemp.getPreco());
//		itemPedidoTemp.atualizarValorParcial();
		
		// Testa se item pedido inserido no banco é igual (tem o mesmo conteúdo) do retornado na busca
		assertTrue(itemPedidoTemp.equals( itemBuscado ));
		
	}
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDaoSql#adicionar(br.leo.lojaSeuManuel.modelo.vo.ItemPedido)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testAdicionar() throws ClassNotFoundException, SQLException {
		
		// Lista de atributos extras do produto
		List<AtributoCustomizavel> atributosExtras = new ArrayList<AtributoCustomizavel>();
		
		atributosExtras.add(new AtributoCustomizavel("cor", "amarelo"));
		atributosExtras.add(new AtributoCustomizavel("cor", "azul"));
		atributosExtras.add(new AtributoCustomizavel("peso", "2kg"));
		
		// Produto temporário utilizado nos testes
		Produto produtoTemp = new Produto("prod01", "dvd", "dvd de sertanejo", 8, 10, atributosExtras);
		
		// Id do produto, gerado no banco durante a inserção
		int idProdutoInserido = produtoDao.adicionar(produtoTemp);
		
		// Item de pedido temporário utilizado nos testes
		ItemPedido itemPedidoTemp = new ItemPedido(idProdutoInserido, 20);
		
		// Id do item de pedido, gerado no banco
		int idItemPedidoInserido = itemPedidoDao.adicionar(itemPedidoTemp, chaveEstrangeiraPedido);
		
		// Teste se o id do item adicionado não é nulo
		assertNotNull(idItemPedidoInserido);
		
		// Testa se o id do item adicionado é maior que zero
		assertTrue(idItemPedidoInserido > 0);
		
	}
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDaoSql#editar(br.leo.lojaSeuManuel.modelo.vo.ItemPedido)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testEditar() throws ClassNotFoundException, SQLException {
		
		// Lista de atributos extras do produto
		List<AtributoCustomizavel> atributosExtras = new ArrayList<AtributoCustomizavel>();
		
		atributosExtras.add(new AtributoCustomizavel("cor", "amarelo"));
		atributosExtras.add(new AtributoCustomizavel("cor", "azul"));
		atributosExtras.add(new AtributoCustomizavel("peso", "2kg"));
		
		// Produto temporário utilizado nos testes
		Produto produtoTemp = new Produto("prod01", "dvd", "dvd de sertanejo", 8, 10, atributosExtras);
		
		// Id do produto, gerado no banco durante a inserção
		int idProdutoInserido = produtoDao.adicionar(produtoTemp);
		
		// Item de pedido temporário utilizado nos testes
		ItemPedido itemPedidoTemp = new ItemPedido(idProdutoInserido, 20);
		
		// Id do item de pedido, gerado no banco
		int idItemPedidoInserido = itemPedidoDao.adicionar(itemPedidoTemp, chaveEstrangeiraPedido);
		
		// Busca o item de pedido inserido no banco através do id
		itemPedidoTemp = itemPedidoDao.buscaPorId(idItemPedidoInserido);
		
		// Realiza algumas alterações nos dados
		itemPedidoTemp.setQuantidade(15);
		
		// Aplica as alterações no banco
		itemPedidoDao.editar(itemPedidoTemp);
		
		// Testa se o item editado no banco tem os mesmos dados do item 
		// antes de adicionar as alterações no banco 
		assertTrue(itemPedidoTemp.equals( itemPedidoDao.buscaPorId(idItemPedidoInserido)) );
		
	}
	
	
	

	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDaoSql#excluir(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testExcluir() throws ClassNotFoundException, SQLException {
		
		// Lista de atributos extras do produto
		List<AtributoCustomizavel> atributosExtras = new ArrayList<AtributoCustomizavel>();
		
		atributosExtras.add(new AtributoCustomizavel("cor", "amarelo"));
		atributosExtras.add(new AtributoCustomizavel("cor", "azul"));
		atributosExtras.add(new AtributoCustomizavel("peso", "2kg"));
		
		// Produto temporário utilizado nos testes
		Produto produtoTemp = new Produto("prod01", "dvd", "dvd de sertanejo", 8, 10, atributosExtras);
		
		// Id do produto, gerado no banco durante a inserção
		int idProdutoInserido = produtoDao.adicionar(produtoTemp);
		
		// Item de pedido temporário utilizado nos testes
		ItemPedido itemPedidoTemp = new ItemPedido(idProdutoInserido, 20);
		
		// Id do item de pedido, gerado no banco
		int idItemPedidoInserido = itemPedidoDao.adicionar(itemPedidoTemp, chaveEstrangeiraPedido);
		
		// Exclui através do id o item inserido
		itemPedidoDao.excluir(idItemPedidoInserido);
		
		// Testa se o item foi realmente excluído
		assertNull(itemPedidoDao.buscaPorId(idItemPedidoInserido));
		
		
	}
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ItemPedidoDaoSql#ebuscaPorChaveEstrangeiraPedido(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testbuscaPorChaveEstrangeiraProduto() throws ClassNotFoundException, SQLException {
		
		List<ItemPedido> listaAtributosTemp = itemPedidoDao.buscaPorChaveEstrangeiraPedido(chaveEstrangeiraPedido);
		
		assertNotNull(listaAtributosTemp);
		
		assertTrue(listaAtributosTemp.size() > 0);
		
//		for (ItemPedido itemPedido : listaAtributosTemp) {
//			System.out.println(itemPedido.getId());
//		}
//		
	}

}
