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
		int idProdutoInserido = produtoDao.adicionar(produtoTemp);
		
		// Adiciona o produto na lista de itens do pedido
		listaItensPedido.add(new ItemPedido(idProdutoInserido, 2));
		
		// Edita os atributos do produto para adicionar um segundo produto no banco
		produtoTemp.setCodigo("prod02");
		produtoTemp.setDescricao("dvd pagode");
		produtoTemp.setEstoque(20);
		
		// Id do produto, gerado no banco durante a inserção
		idProdutoInserido = produtoDao.adicionar(produtoTemp);
		
		// Adiciona o produto na lista de itens do pedido
		listaItensPedido.add(new ItemPedido(idProdutoInserido, 3));

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
		
		// Adiciona alguns pedidos no banco
		pedidoDao.adicionar(novoPedido());
		pedidoDao.adicionar(novoPedido());
		
		// Busca a lista de pedido
		List<Pedido> listaPedidosTemp = pedidoDao.listar();

		// Testa se a lista não é nula
		assertNotNull(listaPedidosTemp);
		
		// Testa se a lista tem ao menos 1 elemento
		assertTrue(listaPedidosTemp.size() > 0);
		
	}
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.PedidoDaoSql#buscaPorId(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testBuscaPorId() throws ClassNotFoundException, SQLException {
		
		// Testa a busca de pedido que naão existe no banco
		assertNull(pedidoDao.buscaPorId(0));
		
		// Pedido temporário utilizado nos testes
		Pedido pedidoTemp = novoPedido();
		
		// Id do item de pedido, gerado no banco
		int idPedidoInserido = pedidoDao.adicionar(pedidoTemp);
		
		// Busca o item inserido no banco através do id para confirmar a inserções dos dados
		Pedido pedidoBuscado = pedidoDao.buscaPorId(idPedidoInserido);
		
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
		
//		// Edita o pedidoBusca e adiciona no banco como um novo pedido
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
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.PedidoDaoSql#adicionar(br.leo.lojaSeuManuel.modelo.vo.Pedido)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testAdicionar() throws ClassNotFoundException, SQLException {
		
		// Id do pedido, gerado no banco
		int idPedidoInserido = pedidoDao.adicionar(novoPedido());
		
		// Teste se o id pedido adicionado não é nulo
		assertNotNull(idPedidoInserido);
		
		// Testa se o id do pedido adicionado é maior que zero
		assertTrue(idPedidoInserido > 0);
		
	}
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.PedidoDaoSql#editar(br.leo.lojaSeuManuel.modelo.vo.Pedido)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testEditar() throws ClassNotFoundException, SQLException {
		
		// Pedido temporário utilizado nos testes
		Pedido pedidoTemp = novoPedido();
		
		// Id do pedido, gerado no banco
		int idPedidoInserido = pedidoDao.adicionar(pedidoTemp);
		
		// Busca o pedido inserido no banco através do id
		pedidoTemp = pedidoDao.buscaPorId(idPedidoInserido);
		
		// Realiza algumas alterações nos dados
		pedidoTemp.setDataCompra(new Date(System.currentTimeMillis()));
		pedidoTemp.setEstado(EstadoPedido.ENTREGUE.getValor());
		pedidoTemp.setValorFrete(14);
		
		// Aplica as alterações no banco
		pedidoDao.editar(pedidoTemp);
		
		// Testa se o item editado no banco tem os mesmos dados do item 
		// antes de adicionar as alterações no banco 
		assertTrue(pedidoTemp.equals( pedidoDao.buscaPorId(idPedidoInserido)) );
		
		
		
	}
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.PedidoDaoSql#excluir(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testExcluir() throws ClassNotFoundException, SQLException {
		
		// Id do pedido, gerado no banco
		int idPedidoInserido = pedidoDao.adicionar(novoPedido());
		
		// Exclui através item inserido através do id
		pedidoDao.excluir(idPedidoInserido);
		
		// Testa se o pedido foi realmente excluído
		assertNull(pedidoDao.buscaPorId(idPedidoInserido));
		
		
		
	}
	
	

}
