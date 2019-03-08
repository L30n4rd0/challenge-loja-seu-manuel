/**
 * 
 */
package br.leo.lojaSeuManuel.testes;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.leo.lojaSeuManuel.modelo.dao.ProdutoDao;
import br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql;
import br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel;
import br.leo.lojaSeuManuel.modelo.vo.Produto;

/**
 * @author leonardo
 *
 */
class ProdutoDaoSqlTeste {
	
	static ProdutoDao produtoDao = new ProdutoDaoSql();
	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql#listar()}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testListar() throws ClassNotFoundException, SQLException {
		
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
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql#buscaPorId(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testBuscaPorId() throws ClassNotFoundException, SQLException {

		/**
		 * Testando a SQLException ao buscar por id de atributo não cadastrado no banco
		 */
		assertNull(produtoDao.buscaPorId(0));
		
		
		List<AtributoCustomizavel> atributosExtras = new ArrayList<AtributoCustomizavel>();
		
		atributosExtras.add(new AtributoCustomizavel("cor", "amarelo"));
		atributosExtras.add(new AtributoCustomizavel("cor", "azul"));
		atributosExtras.add(new AtributoCustomizavel("peso", "2kg"));
		
		Produto produtoTemp = new Produto("prod01", "dvd", "dvd de sertanejo", 8, 10, atributosExtras);
		
		int idProdutoInserido = produtoDao.adicionar(produtoTemp);
		
		produtoTemp.setId(idProdutoInserido);
		
		Produto produtoBuscaodo = produtoDao.buscaPorId(idProdutoInserido);
		
		assertNotNull(produtoBuscaodo);
		assertNotNull(produtoBuscaodo.getId());
		assertNotNull(produtoBuscaodo.getNome());
		assertNotNull(produtoBuscaodo.getPreco());
		assertNotNull(produtoBuscaodo.getEstoque());
		assertNotNull(produtoBuscaodo.getDescricao());
		assertNotNull(produtoBuscaodo.getCodigo());
		assertNotNull(produtoBuscaodo.getAtributosCustomizaveis());
		
		List<AtributoCustomizavel> listDeAtributos = produtoBuscaodo.getAtributosCustomizaveis();
		
		for (AtributoCustomizavel atributoCustomizavel : listDeAtributos) {
			
			assertNotNull(atributoCustomizavel.getId());
			assertNotNull(atributoCustomizavel.getNome());
			assertNotNull(atributoCustomizavel.getValor());
			
		}
		
		for (int i = 0; i < listDeAtributos.size(); i++) {
			
			produtoTemp.getAtributosCustomizaveis().get(i).setId(
					produtoBuscaodo.getAtributosCustomizaveis().get(i).getId()
			);
			
		}
		
		assertTrue(produtoTemp.equals( produtoBuscaodo ));
		
	}

	
	
	
	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql#adicionar(br.leo.lojaSeuManuel.modelo.vo.Produto)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testAdicionar() throws ClassNotFoundException, SQLException {
		
		int idProdutoInserido = 0;
		
		List<AtributoCustomizavel> atributosExtras = new ArrayList<AtributoCustomizavel>();
		
		atributosExtras.add(new AtributoCustomizavel("cor", "amarelo"));
		atributosExtras.add(new AtributoCustomizavel("cor", "azul"));
		atributosExtras.add(new AtributoCustomizavel("peso", "2kg"));
		
		Produto produtoTemp = new Produto("prod01", "dvd", "dvd de sertanejo", 8, 10, atributosExtras);
		
		idProdutoInserido = produtoDao.adicionar(produtoTemp);
		
		Integer temp = idProdutoInserido;
		
		assertTrue(temp instanceof Integer);
		
		/**
		 * O método adicionar retorna o id gerado no banco de dados em caso de sucesso
		 */
		assertTrue(idProdutoInserido > 0);
		
	}

	
	
	
	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql#editar(br.leo.lojaSeuManuel.modelo.vo.Produto)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testEditar() throws ClassNotFoundException, SQLException {
		
		List<AtributoCustomizavel> atributosExtras = new ArrayList<AtributoCustomizavel>();
		
		atributosExtras.add(new AtributoCustomizavel("cor", "amarelo"));
		atributosExtras.add(new AtributoCustomizavel("cor", "azul"));
		atributosExtras.add(new AtributoCustomizavel("peso", "2kg"));
		
		Produto produtoTemp = new Produto("prod01", "dvd", "dvd de sertanejo", 8, 10, atributosExtras);
		
		int idProdutoInserido = produtoDao.adicionar(produtoTemp);
		
		produtoTemp = produtoDao.buscaPorId(idProdutoInserido);
		
		produtoTemp.setNome("cd");
		produtoTemp.setEstoque(8);
		produtoTemp.setPreco(10);
		produtoTemp.setDescricao("cd sertanejo");
		produtoTemp.getAtributosCustomizaveis().get(0).setValor("preto");
		produtoTemp.getAtributosCustomizaveis().get(2).setValor("3kg");
		
		produtoDao.editar(produtoTemp);
		
		assertTrue(produtoTemp.equals( produtoDao.buscaPorId(idProdutoInserido)) );
		
		
		produtoTemp.getAtributosCustomizaveis().remove(0);
		
		produtoDao.editar(produtoTemp);
		
		assertTrue(produtoTemp.equals( produtoDao.buscaPorId(idProdutoInserido)) );
		
		

	}

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql#excluir(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testExcluir() throws ClassNotFoundException, SQLException {
		
		List<AtributoCustomizavel> atributosExtras = new ArrayList<AtributoCustomizavel>();
		
		atributosExtras.add(new AtributoCustomizavel("cor", "amarelo"));
		atributosExtras.add(new AtributoCustomizavel("cor", "azul"));
		atributosExtras.add(new AtributoCustomizavel("peso", "2kg"));
		
		Produto produtoTemp = new Produto("prod01", "dvd", "dvd de sertanejo", 8, 10, atributosExtras);
		
		int idProdutoInserido = produtoDao.adicionar(produtoTemp);
		
		produtoDao.excluir(idProdutoInserido);
		
		assertNull(produtoDao.buscaPorId(idProdutoInserido));
		
		
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
//			assertNull(produtoDao.buscaPorId(atributoCustomizavel.getId()));
//			
//		}
//		
//	}

}
