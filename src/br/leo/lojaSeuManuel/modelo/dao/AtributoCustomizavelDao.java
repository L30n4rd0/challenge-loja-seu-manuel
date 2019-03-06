/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.dao;

import java.util.List;

import br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel;

/**
 * @author leonardo
 *
 */
public interface AtributoCustomizavelDao {
	
	public List<AtributoCustomizavel> listar();
	
	public AtributoCustomizavel buscaPorId(int id);
	
	public int adicionar(AtributoCustomizavel atributoCustomizavel);
	
	public void editar(AtributoCustomizavel atributoCustomizavel);
	
	public void excluir(int id);

}
