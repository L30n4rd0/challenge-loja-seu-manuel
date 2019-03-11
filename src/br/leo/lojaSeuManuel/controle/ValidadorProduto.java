/**
 * 
 */
package br.leo.lojaSeuManuel.controle;

import br.leo.lojaSeuManuel.modelo.vo.Produto;

/**
 * @author leonardo
 *
 */
public class ValidadorProduto {

	
	protected void validarProduto(Produto produto) throws Exception {
		
		validarCodigo(produto.getCodigo());
		
		validarDescricao(produto.getDescricao());
		
		validarEstoque(produto.getEstoque());
		
		validarNome(produto.getNome());
		
		validarPreco(produto.getPreco());
		
		
		
	}
	
	
	private void validarCodigo(String codigo) throws Exception {
		
		if (codigo.equals("")) {
			
			throw new Exception("O código não pode ser vazio!");
			
		} else if (codigo.length() > 20) {
			
			throw new Exception("O código não pode ter mais que 20 caracteres!");
			
		}
		
		
	}
	
	
	private void validarNome(String nome) throws Exception {
		
		if (nome.equals("")) {
			
			throw new Exception("O nome não pode ser vazio!");
			
		} else if (nome.length() > 45) {
			
			throw new Exception("O nome não pode ter mais que 45 caracteres!");
			
		}
		
		
	}
	
	
	private void validarDescricao(String descricao) throws Exception {
		
		if (descricao.equals("")) {
			
			throw new Exception("A descrição não pode ser vazio!");
			
		} else if (descricao.length() > 45) {
			
			throw new Exception("A descrição não pode ter mais que 100 caracteres!");
			
		}
		
		
	}
	
	private void validarEstoque(int estoque) throws Exception {
		
		if (estoque < 0) {
			
			throw new Exception("O estoque não pode ser menor que zero!");
			
		}
		
		
	}
	
	private void validarPreco(double preco) throws Exception {
		
		if (preco <= 0) {
			
			throw new Exception("O preço não pode ser menor que ou igual a zero!");
			
		}
		
	}
	
	
}
