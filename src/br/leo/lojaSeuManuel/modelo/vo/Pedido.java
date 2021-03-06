/**
 * 
 */
package br.leo.lojaSeuManuel.modelo.vo;

import java.sql.Date;
import java.util.List;

import br.leo.lojaSeuManuel.util.Formatador;

/**
 * @author leonardo
 *
 */
public class Pedido {
	
	private int id;
	
	private String codigo;
	
	private Date dataCompra;
	
	private String nomeComprador;
	
	private String estado;
	
	private double valorFrete;
	
	private double valorTotal;
	
	private List<ItemPedido> itensDoPedido;
	
	
	public Pedido() {
	}


	/**
	 * @param codigo
	 * @param dataCompra
	 * @param nomeComprador
	 * @param estado
	 * @param valorFrete
	 * @param itensDoPedido
	 */
	public Pedido(String codigo, Date dataCompra, String nomeComprador, String estado, double valorFrete,
			List<ItemPedido> itensDoPedido) {
		this.codigo = codigo;
		this.dataCompra = dataCompra;
		this.nomeComprador = nomeComprador;
		this.estado = estado;
		this.valorFrete = valorFrete;
		this.itensDoPedido = itensDoPedido;
		atualizarValorTotal();
	}


	/**
	 * @param id
	 * @param codigo
	 * @param dataCompra
	 * @param nomeComprador
	 * @param estado
	 * @param valorFrete
	 * @param itensDoPedido
	 */
	public Pedido(int id, String codigo, Date dataCompra, String nomeComprador, String estado, double valorFrete,
			List<ItemPedido> itensDoPedido) {
		this.id = id;
		this.codigo = codigo;
		this.dataCompra = dataCompra;
		this.nomeComprador = nomeComprador;
		this.estado = estado;
		this.valorFrete = valorFrete;
		this.itensDoPedido = itensDoPedido;
		atualizarValorTotal();
	}
	
	
	
	public void atualizarValorTotal() {
		
		this.valorTotal = this.valorFrete;
		
		for (ItemPedido itemPedido : this.itensDoPedido) {
			
			this.valorTotal += itemPedido.getValorParcial();
			
		}
		
		this.valorTotal = Formatador.formatarDoubeParaDoisDecimais(this.valorTotal);
		
		
	}
	
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public Date getDataCompra() {
		return dataCompra;
	}


	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}


	public String getNomeComprador() {
		return nomeComprador;
	}


	public void setNomeComprador(String nomeComprador) {
		this.nomeComprador = nomeComprador;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public double getValorFrete() {
		return valorFrete;
	}


	public void setValorFrete(double valorFrete) {
		this.valorFrete = valorFrete;
		atualizarValorTotal();
	}


	public double getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}


	public List<ItemPedido> getItensDoPedido() {
		return itensDoPedido;
	}


	public void setItensDoPedido(List<ItemPedido> itensDoPedido) {
		this.itensDoPedido = itensDoPedido;
		atualizarValorTotal();
	}


	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Pedido pedidoTemp = (Pedido) obj;
		
		if (this.id != pedidoTemp.getId())
			return false;
		
		if (!this.codigo.equals(pedidoTemp.getCodigo()))
			return false;
		
		if (!this.dataCompra.equals(pedidoTemp.getDataCompra()))
			return false;
		
		if (!this.nomeComprador.equals(pedidoTemp.getNomeComprador()))
			return false;
			
		if (!this.estado.equals(pedidoTemp.getEstado()))
			return false;
		
		if (this.valorFrete != pedidoTemp.getValorFrete())
			return false;
		
		if (this.valorTotal != pedidoTemp.getValorTotal())
			return false;
		
		for (int i = 0; i < this.itensDoPedido.size(); i++) {
			
			if ( !this.itensDoPedido.get(i).equals(pedidoTemp.getItensDoPedido().get(i)) ) {
				return false;
				
			}
			
		}
		
		return true;
	}


}
