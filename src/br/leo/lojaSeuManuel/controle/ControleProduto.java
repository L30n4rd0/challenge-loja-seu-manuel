/**
 * 
 */
package br.leo.lojaSeuManuel.controle;

import java.util.List;

import br.leo.lojaSeuManuel.modelo.dao.ProdutoDao;
import br.leo.lojaSeuManuel.modelo.dao.ProdutoDaoSql;
import br.leo.lojaSeuManuel.modelo.vo.Produto;

/**
 * @author leonardo
 *
 */
public class ControleProduto {
	
	private ProdutoDao produtoDao;
	
	private ValidadorProduto validador;
	
	
	/**
	 * @param produtoDao
	 */
	public ControleProduto() {
		
		this.produtoDao = new ProdutoDaoSql();
		this.validador = new ValidadorProduto();
		
	}



	public List<Produto> listar() throws Exception {
		
		return produtoDao.listar();
	}
	
	

	public Produto buscarPorId(int id) throws Exception {
		
		return produtoDao.buscarPorId(id);
	}
	
	

	public int inserir(Produto produto) throws Exception {
		
		
		validador.validarProduto(produto);
		
		return produtoDao.inserir(produto);
		
	}
	
	

	public String atualizar(Produto produto) throws Exception {
		
		if (produtoDao.buscarPorId(produto.getId()) == null) {
			
			throw new Exception("Produto não cadastrado!");
			
		}
		
		validador.validarProduto(produto);
		
		produtoDao.atualizar(produto);
		
		return "Produto atualizado id: " + produto.getId();

	}
	
	

	public String excluir(int id) throws Exception {
		
		if (produtoDao.buscarPorId(id) == null) {
			
			throw new Exception("Produto não cadastrado!");
			
		}
		
		produtoDao.excluir(id);
		
		return "Produto excluído id: " + id;

	}
	

}
