/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.dao;

import java.sql.SQLException;
import java.util.List;

import br.leo.lojaSeuManuel.modelo.vo.Produto;

/**
 * @author leonardo
 *
 */
public interface ProdutoDao {
	
	public List<Produto> listar() throws ClassNotFoundException, SQLException;
	
	public Produto buscarPorId(int id) throws ClassNotFoundException, SQLException;
	
	public int inserir(Produto produto) throws ClassNotFoundException, SQLException;
	
	public void atualizar(Produto produto) throws ClassNotFoundException, SQLException;
	
	public void excluir(int id) throws ClassNotFoundException, SQLException;

}
