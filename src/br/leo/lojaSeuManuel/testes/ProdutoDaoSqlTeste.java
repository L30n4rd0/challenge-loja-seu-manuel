/**
 * 
 */
package br.leo.lojaSeuManuel.testes;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	static List<AtributoCustomizavel> atributosExtras = new ArrayList<AtributoCustomizavel>();
	
	static Produto produtoTemp;
	
	@BeforeAll
	static void iniciando() {
		
		atributosExtras.add(new AtributoCustomizavel("cor", "amarelo"));
		atributosExtras.add(new AtributoCustomizavel("cor", "azul"));
		atributosExtras.add(new AtributoCustomizavel("peso", "2kg"));
		
		produtoTemp = new Produto("prod01", "dvd", "dvd de sertanejo", 10, 10, atributosExtras);
		
	}
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql#listar()}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testListar() throws ClassNotFoundException, SQLException {
		
		List<Produto> listaProdutosTemp = produtoDao.listar();
		
//		for (Produto produto : listaProdutosTemp) {
//			System.out.println(produto.getId());
//			System.out.println(produto.getNome());
//		}
		
		assertTrue(listaProdutosTemp.size() > 0);
		
		
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
		
		
		int idProdutoInserido = produtoDao.adicionar(produtoTemp);
		
		produtoTemp.setId(idProdutoInserido);
		
		assertTrue(produtoTemp.equals( produtoDao.buscaPorId(idProdutoInserido) ));
		
		
	}

	
	
	
	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql#adicionar(br.leo.lojaSeuManuel.modelo.vo.Produto)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testAdicionar() throws ClassNotFoundException, SQLException {
		
		int idProdutoInserido = 0;
		
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
		
		int idProdutoInserido = produtoDao.adicionar(produtoTemp);
		
		produtoTemp.setId(idProdutoInserido);
		produtoTemp.setNome("cd");
		produtoTemp.setEstoque(8);
		produtoTemp.setPreco(10);
		produtoTemp.setDescricao("cd sertanejo");
		produtoTemp.getAtributosCustomizaveis().get(0).setValor("preto");
		
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
		
		int idProdutoInserido = produtoDao.adicionar(produtoTemp);
		
		produtoDao.excluir(idProdutoInserido);
		
		assertNull(produtoDao.buscaPorId(idProdutoInserido));
		
		
	}

}
