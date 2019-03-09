/**
 * 
 */
package br.leo.lojaSeuManuel.util;

/**
 * @author leonardo
 *
 */
public enum EstadoPedido {
	
	NOVO("novo"),
	APROVADO("aprovado"),
	ENTREGUE("entregue"),
	CANCELADO("cancelado");
 
    private String valor;
 
    EstadoPedido(String descricao) {
        this.valor = descricao;
    }

	public String getValor() {
		return valor;
	}
 

}

