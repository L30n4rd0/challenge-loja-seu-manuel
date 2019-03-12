/**
 * 
 */
package br.leo.lojaSeuManuel.controle;

import java.sql.Date;
import java.util.List;

import br.leo.lojaSeuManuel.modelo.vo.ItemPedido;
import br.leo.lojaSeuManuel.modelo.vo.Pedido;
import br.leo.lojaSeuManuel.util.EstadoPedido;

/**
 * @author leonardo
 *
 */
public class ValidadorPedido {

	
	protected void validarPedido(Pedido pedido) throws Exception {
		
		validarCodigo(pedido.getCodigo());
		
		validarDataCompra(pedido.getDataCompra());
		
		validarNomeComprador(pedido.getNomeComprador());
		
		validarEstado(pedido.getEstado());
		
		validarValorFrete(pedido.getValorFrete());
		
		validarItensPedido(pedido.getItensDoPedido());
		
		pedido.atualizarValorTotal();
		
		validarValorTotal(pedido.getValorTotal());
		
		
	}
	
	
	
	
	private void validarItensPedido(List<ItemPedido> itensDoPedido) throws Exception {
		
		ValidadorItemPedido validadorItemPedido = new ValidadorItemPedido();
		
		for (ItemPedido itemPedido : itensDoPedido) {
			
			validadorItemPedido.validarItemPedido(itemPedido);
			
		}
		
	}


	
	
	
	
	private void validarValorTotal(double valorTotal) throws Exception {
		
		if (valorTotal <= 0) {
			
			throw new Exception("O preço não pode ser menor que ou igual a zero!");
			
		}
		
	}

	
	

	private void validarCodigo(String codigo) throws Exception {
		
		if (codigo.equals("")) {
			
			throw new Exception("O código não pode ser vazio!");
			
		} else if (codigo.length() > 20) {
			
			throw new Exception("O código não pode ter mais que 20 caracteres!");
			
		}
		
		
	}
	
	
	
	
	private void validarNomeComprador(String nome) throws Exception {
		
		if (nome.equals("")) {
			
			throw new Exception("O nome não pode ser vazio!");
			
		} else if (nome.length() > 45) {
			
			throw new Exception("O nome não pode ter mais que 45 caracteres!");
			
		}
		
		
	}
	
	
	
	
	private void validarDataCompra(Date dataCompra) throws Exception {
		
		if (dataCompra.equals("")) {
			
			throw new Exception("A data de compra não pode ser vazia!");
			
		} else if (dataCompra.getTime() > System.currentTimeMillis()) {
			
			throw new Exception("A data de compra não pode posterior à data atual!");
			
		}
		
		
	}
	
	
	
	
	private void validarEstado(String estado) throws Exception {
		
		if (estado.equals("")) {
			
			throw new Exception("O estado não pode ser vazio!");
			
		} else {
			
			boolean estadoValido = false;
			
			for (EstadoPedido estadoPedido : EstadoPedido.values()) {
				
				if (estado.equals(estadoPedido.getValor())) {
					
					estadoValido = true;
					
					break;
					
				}
				
			}
			
			if (!estadoValido) {
				
				String estadosValidos = "";
				
				for (EstadoPedido estadoPedido : EstadoPedido.values()) {
					
					estadosValidos += estadoPedido.getValor() + "\n";
					
				}
				
				throw new Exception(
						"O estado não é válido!\n" +
						"Os valores válidos são:\n" +
						estadosValidos
						
						);
				
			}
			
		}
		
		
	}
	
	
	
	
	private void validarValorFrete(double valorFrete) throws Exception {
		
		if (valorFrete < 0) {
			
			throw new Exception("O preço não pode ser menor que zero!");
			
		}
		
	}
	
	
}
