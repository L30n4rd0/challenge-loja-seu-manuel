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
		
//		assertNull(listaAtributosTemp);
		
		assertTrue(listaAtributosTemp.size() > 0);
		
	}
	
	
	
	
	@Test
	void testbuscarPorChaveEstrangeiraProduto() throws ClassNotFoundException, SQLException {
		
		List<AtributoCustomizavel> listaAtributosTemp = atributoDao.buscarPorChaveEstrangeiraProduto(idProduto);
		
		assertNotNull(listaAtributosTemp);
		
		assertTrue(listaAtributosTemp.size() > 0);
		
	}

	
	
	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDaoSql#buscarPorId(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testBuscarPorId() throws ClassNotFoundException, SQLException {
		
		/**
		 * Testando a SQLException ao buscar por id de atributo não cadastrado no banco
		 */
		assertNull(atributoDao.buscarPorId(0));
		
		
		AtributoCustomizavel atributoTemp = new AtributoCustomizavel("atributoNome", "333");
		
		int idAtributoInserido = atributoDao.inserir(atributoTemp, idProduto);
		
		atributoTemp.setId(idAtributoInserido);
		
		assertTrue(atributoTemp.equals( atributoDao.buscarPorId(idAtributoInserido) ));
	}
	
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDaoSql#inserir(br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testInserir() throws ClassNotFoundException, SQLException {
		
		/**
		 * Testando a SQLException no caso onde fk_id_produto é zero
//		 */
//		assertThrows(SQLException.class, () -> {
//			
//	        atributoDao.inserir(new AtributoCustomizavel("atributoNome", "333"), 0);
//	        
//	    });
		
		
		int idAtributoInserido = 0;
		
		idAtributoInserido = atributoDao.inserir(new AtributoCustomizavel("atributoNome", "333"), idProduto);
		
		Integer temp = idAtributoInserido;
		
		assertTrue(temp instanceof Integer);
		
		/**
		 * O método inserir retorna o id gerado no banco de dados em caso de sucesso
		 */
		assertTrue(idAtributoInserido > 0);
		
	}
	
	
	
	

	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDaoSql#atualizar(br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testAtualizar() throws ClassNotFoundException, SQLException {
		
		AtributoCustomizavel atributoTemp = new AtributoCustomizavel("atributoNome", "333");
		
		int idAtributoInserido = atributoDao.inserir(atributoTemp, idProduto);
		
		atributoTemp.setId(idAtributoInserido);
		atributoTemp.setNome("aaa");
		atributoTemp.setValor("555");
		
		atributoDao.atualizar(atributoTemp);
		
		assertTrue(atributoTemp.equals( atributoDao.buscarPorId(idAtributoInserido)) );
		
	}

	
	
	
	
	/**
	 * Test method for {@link br.leo.lojaSeuManuel.modelo.dao.AtributoCustomizavelDaoSql#excluir(int)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testExcluir() throws ClassNotFoundException, SQLException {
		
		AtributoCustomizavel atributoTemp = new AtributoCustomizavel("atributoNome", "333");
		
		int idAtributoInserido = atributoDao.inserir(atributoTemp, idProduto);
		
		atributoDao.excluir(idAtributoInserido);
		
		assertNull(atributoDao.buscarPorId(idAtributoInserido));
		
	}
	
	
	
	
//	@AfterAll
//	static void limparBanco() throws ClassNotFoundException, SQLException {
//		
//		List<AtributoCustomizavel> listaAtributosTemp = atributoDao.listar();
//		
//		for (AtributoCustomizavel atributoCustomizavel : listaAtributosTemp) {
//			
//			atributoDao.excluir(atributoCustomizavel.getId());
//			
//			assertNull(atributoDao.buscarPorId(atributoCustomizavel.getId()));
//			
//		}
//		
//	}

}
