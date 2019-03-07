/**
 * 
 */
package br.leo.lojaSeuManuel.testes;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDao;
import br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDaoSql;
import br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel;

/**
 * @author leonardo
 *
 */
class AtributoCustomizavelDaoSqlTeste {
	
	static AtributoCustomizavelDao atributoDao = new AtributoCustomizavelDaoSql();
	
	int idProduto = 1;

	
	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDaoSql#listar()}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testListar() throws ClassNotFoundException, SQLException {
		
		List<AtributoCustomizavel> listaAtributosTemp = atributoDao.listar();
		
//		for (AtributoCustomizavel atributoCustomizavel : listaAtributosTemp) {
//			System.out.println(atributoCustomizavel.getId());
//			System.out.println(atributoCustomizavel.getNome());
//		}
		
		assertTrue(listaAtributosTemp.size() > 0);
		
	}

	
	
	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDaoSql#buscaPorId(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testBuscaPorId() throws ClassNotFoundException, SQLException {
		
		/**
		 * Testando a SQLException ao buscar por id de atributo não cadastrado no banco
		 */
		assertNull(atributoDao.buscaPorId(0));
		
		
		AtributoCustomizavel atributoTemp = new AtributoCustomizavel("atributoNome", "333");
		
		int idAtributoInserido = atributoDao.adicionar(atributoTemp, idProduto);
		
		atributoTemp.setId(idAtributoInserido);
		
		assertTrue(atributoTemp.equals( atributoDao.buscaPorId(idAtributoInserido) ));
	}
	
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDaoSql#adicionar(br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testAdicionar() throws ClassNotFoundException, SQLException {
		
		/**
		 * Testando a SQLException no caso onde fk_id_produto é zero
//		 */
//		assertThrows(SQLException.class, () -> {
//			
//	        atributoDao.adicionar(new AtributoCustomizavel("atributoNome", "333"), 0);
//	        
//	    });
		
		
		int idAtributoInserido = 0;
		
		idAtributoInserido = atributoDao.adicionar(new AtributoCustomizavel("atributoNome", "333"), 1);
		
		Integer temp = idAtributoInserido;
		
		assertTrue(temp instanceof Integer);
		
		/**
		 * O método adicionar retorna o id gerado no banco de dados em caso de sucesso
		 */
		assertTrue(idAtributoInserido > 0);
		
	}
	
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDaoSql#editar(br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testEditar() throws ClassNotFoundException, SQLException {
		
		AtributoCustomizavel atributoTemp = new AtributoCustomizavel("atributoNome", "333");
		
		int idAtributoInserido = atributoDao.adicionar(atributoTemp, idProduto);
		
		atributoTemp.setId(idAtributoInserido);
		atributoTemp.setNome("aaa");
		atributoTemp.setValor("555");
		
		atributoDao.editar(atributoTemp);
		
		assertTrue(atributoTemp.equals( atributoDao.buscaPorId(idAtributoInserido)) );
		
	}

	
	
	
	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDaoSql#excluir(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testExcluir() throws ClassNotFoundException, SQLException {
		
		AtributoCustomizavel atributoTemp = new AtributoCustomizavel("atributoNome", "333");
		
		int idAtributoInserido = atributoDao.adicionar(atributoTemp, idProduto);
		
		atributoDao.excluir(idAtributoInserido);
		
		assertNull(atributoDao.buscaPorId(idAtributoInserido));
		
	}
	
	
	
	
	@AfterAll
	static void limparBanco() throws ClassNotFoundException, SQLException {
		
		List<AtributoCustomizavel> listaAtributosTemp = atributoDao.listar();
		
		for (AtributoCustomizavel atributoCustomizavel : listaAtributosTemp) {
			
			atributoDao.excluir(atributoCustomizavel.getId());
			
			assertNull(atributoDao.buscaPorId(atributoCustomizavel.getId()));
			
		}
		
	}

}
