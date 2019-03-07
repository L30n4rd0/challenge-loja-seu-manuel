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
	
	public Produto buscaPorId(int id) throws ClassNotFoundException, SQLException;
	
	public int adicionar(Produto produto) throws ClassNotFoundException, SQLException;
	
	public void editar(Produto produto) throws ClassNotFoundException, SQLException;
	
	public void excluir(int id) throws ClassNotFoundException, SQLException;

}
