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
public class ProdutoDaoSql implements ProdutoDao {

	
	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.ProdutoDao#listar()
	 */
	@Override
	public List<Produto> listar() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.ProdutoDao#buscaPorId(int)
	 */
	@Override
	public Produto buscaPorId(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.ProdutoDao#adicionar(br.leo.lojaSeuManuel.modelo.vo.Produto)
	 */
	@Override
	public int adicionar(Produto produto) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.ProdutoDao#editar(br.leo.lojaSeuManuel.modelo.vo.Produto)
	 */
	@Override
	public void editar(Produto produto) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

	
	
	
	/* (non-Javadoc)
	 * @see br.leo.lojaSeuManuel.modelo.dao.ProdutoDao#excluir(int)
	 */
	@Override
	public void excluir(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

}
