/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.dao;

import java.sql.SQLException;
import java.util.List;

import br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel;

/**
 * @author leonardo
 *
 */
public interface AtributoCustomizavelDao {
	
	public List<AtributoCustomizavel> listar() throws ClassNotFoundException, SQLException;
	
	public AtributoCustomizavel buscaPorId(int id) throws ClassNotFoundException, SQLException;
	
	public int adicionar(AtributoCustomizavel atributoCustomizavel, int fk_id_produto) throws SQLException, ClassNotFoundException;
	
	public void editar(AtributoCustomizavel atributoCustomizavel) throws ClassNotFoundException, SQLException;
	
	public void excluir(int id) throws ClassNotFoundException, SQLException;


}
