/**
 * 
 */
package br.leo.lojaSeuManuel.testes.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.leo.lojaSeuManuel.modelo.dao.ProdutoDao;
import br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql;
import br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel;
import br.leo.lojaSeuManuel.modelo.vo.Produto;
import br.leo.lojaSeuManuel.util.GeradorDados;

/**
 * @author leonardo
 *
 */
class ProdutoDaoSqlTeste {
	
	static ProdutoDao produtoDao = new ProdutoDaoSql();
	
	static GeradorDados geradorDados = new GeradorDados();
	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql#listar()}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testListar() throws ClassNotFoundException, SQLException {
		
		produtoDao.inserir( geradorDados.gerarNovoProduto() );
		
		produtoDao.inserir( geradorDados.gerarNovoProduto() );
		
		
		List<Produto> listaProdutosTemp = produtoDao.listar();
		
		assertNotNull(listaProdutosTemp);
		
		assertTrue(listaProdutosTemp.size() > 0);
		
		for (Produto produto : listaProdutosTemp) {
			
			assertNotNull(produto);
			assertNotNull(produto.getId());
			assertNotNull(produto.getNome());
			assertNotNull(produto.getPreco());
			assertNotNull(produto.getEstoque());
			assertNotNull(produto.getDescricao());
			assertNotNull(produto.getCodigo());
			assertNotNull(produto.getAtributosCustomizaveis());
			
			List<AtributoCustomizavel> listDeAtributos = produto.getAtributosCustomizaveis();
			
			for (AtributoCustomizavel atributoCustomizavel : listDeAtributos) {
				
				assertNotNull(atributoCustomizavel.getId());
				assertNotNull(atributoCustomizavel.getNome());
				assertNotNull(atributoCustomizavel.getValor());
				
			}
			
		}
		
	}

	
	
	
	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql#buscarPorId(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testBuscarPorId() throws ClassNotFoundException, SQLException {

		/**
		 * Testando a SQLException ao buscar por id de atributo não cadastrado no banco
		 */
		assertNull(produtoDao.buscarPorId(0));
		
		Produto produtoTemp = geradorDados.gerarNovoProduto();
		
		int idProdutoInserido = produtoDao.inserir(produtoTemp);
		
		produtoTemp.setId(idProdutoInserido);
		
		Produto produtoBuscado = produtoDao.buscarPorId(idProdutoInserido);
		
		assertNotNull(produtoBuscado);
		assertNotNull(produtoBuscado.getId());
		assertNotNull(produtoBuscado.getNome());
		assertNotNull(produtoBuscado.getPreco());
		assertNotNull(produtoBuscado.getEstoque());
		assertNotNull(produtoBuscado.getDescricao());
		assertNotNull(produtoBuscado.getCodigo());
		assertNotNull(produtoBuscado.getAtributosCustomizaveis());
		
		List<AtributoCustomizavel> listDeAtributos = produtoBuscado.getAtributosCustomizaveis();
		
		for (AtributoCustomizavel atributoCustomizavel : listDeAtributos) {
			
			assertNotNull(atributoCustomizavel.getId());
			assertNotNull(atributoCustomizavel.getNome());
			assertNotNull(atributoCustomizavel.getValor());
			
		}
		
		for (int i = 0; i < listDeAtributos.size(); i++) {
			
			produtoTemp.getAtributosCustomizaveis().get(i).setId(
					produtoBuscado.getAtributosCustomizaveis().get(i).getId()
			);
			
		}
		
		assertTrue(produtoTemp.equals( produtoBuscado ));
		
	}

	
	
	
	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql#inserir(br.leo.lojaSeuManuel.modelo.vo.Produto)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testInserir() throws ClassNotFoundException, SQLException {
		
		int idProdutoInserido = 0;
		
		Produto produtoTemp = geradorDados.gerarNovoProduto();
		
		idProdutoInserido = produtoDao.inserir(produtoTemp);
		
		// Testa se o id do produto inserido não é nulo
		assertNotNull(idProdutoInserido);
		
		// Testa se o id do produto inserido é maior que zero
		assertTrue(idProdutoInserido > 0);
		
	}

	
	
	
	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql#atualizar(br.leo.lojaSeuManuel.modelo.vo.Produto)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testAtualizar() throws ClassNotFoundException, SQLException {
		
		Produto produtoTemp = geradorDados.gerarNovoProduto();
		
		// Insere um novo produto no banco de dados
		int idProdutoInserido = produtoDao.inserir(produtoTemp);
		
		// Busca por id o produto inserido previamente
		produtoTemp = produtoDao.buscarPorId(idProdutoInserido);
		
		// Realiza algumas alterações nos dados
		produtoTemp.setNome("Nome produto");
		produtoTemp.setEstoque(8);
		produtoTemp.setPreco(10);
		produtoTemp.setDescricao("Descrica teste teste");
		produtoTemp.getAtributosCustomizaveis().get(0).setValor("preto");
		produtoTemp.getAtributosCustomizaveis().get(1).setValor("3kg");
		
		// Aplica as alterações no banco
		produtoDao.atualizar(produtoTemp);
		
		// Testa se o produto atualizado no banco têm os mesmos dados do produto 
		// antes de aplicar as alterações
		assertTrue(produtoTemp.equals( produtoDao.buscarPorId(idProdutoInserido)) );
		
		// Aplica as alterações no banco
		produtoDao.atualizar(produtoTemp);
		
		// Testa se o produto atualizado no banco têm os mesmos dados do produto 
		// antes de aplicar as alterações 
		assertTrue(produtoTemp.equals( produtoDao.buscarPorId(idProdutoInserido)) );

	}

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql#excluir(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testExcluir() throws ClassNotFoundException, SQLException {
		
		Produto produtoTemp = geradorDados.gerarNovoProduto();
		
		int idProdutoInserido = produtoDao.inserir(produtoTemp);
		
		produtoDao.excluir(idProdutoInserido);
		
		assertNull(produtoDao.buscarPorId(idProdutoInserido));
		
		
	}
	
	
//	@AfterAll
//	static void limparBanco() throws ClassNotFoundException, SQLException {
//		
//		List<Produto> listaAtributosTemp = produtoDao.listar();
//		
//		for (Produto atributoCustomizavel : listaAtributosTemp) {
//			
//			produtoDao.excluir(atributoCustomizavel.getId());
//			
//			assertNull(produtoDao.buscarPorId(atributoCustomizavel.getId()));
//			
//		}
//		
//	}

}
