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
	
	public AtributoCustomizavel buscarPorId(int id) throws ClassNotFoundException, SQLException;
	
	public List<AtributoCustomizavel> buscarPorChaveEstrangeiraProduto(int chaveEstrangeiraProduto) throws ClassNotFoundException, SQLException;
	
	public int inserir(AtributoCustomizavel atributoCustomizavel, int chaveEstrangeiraProduto) throws SQLException, ClassNotFoundException;
	
	public void atualizar(AtributoCustomizavel atributoCustomizavel) throws ClassNotFoundException, SQLException;
	
	public void excluir(int id) throws ClassNotFoundException, SQLException;


}
