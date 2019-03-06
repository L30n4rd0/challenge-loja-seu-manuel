/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.dao;

import java.util.List;

import br.leo.lojaSeuManuel.modelo.vo.Produto;

/**
 * @author leonardo
 *
 */
public interface ProdutoDao {
	
	public List<Produto> listar();
	
	public Produto buscaPorId(int id);
	
	public int adicionar(Produto produto);
	
	public void editar(Produto produto);
	
	public void excluir(int id);

}
