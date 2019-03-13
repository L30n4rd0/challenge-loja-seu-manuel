/**
 * 
 */
package br.leo.lojaSeuManuel.util;

import java.util.ArrayList;
import java.util.List;

import br.leo.lojaSeuManuel.controle.ControlePedido;
import br.leo.lojaSeuManuel.controle.ControleProduto;
import br.leo.lojaSeuManuel.modelo.vo.Pedido;
import br.leo.lojaSeuManuel.modelo.vo.Produto;

/**
 * @author leonardo
 *
 */
public class PovoarBanco {
	
	private int quantidadeProdutos = 100;
	
	private int quantidadePedidos = 50;
	
	private ControleProduto controleProduto = new ControleProduto();
	
	private ControlePedido controlePedido = new ControlePedido();
	
	private GeradorDados geradorDados = new GeradorDados();
	
	
	//////////// MAIN /////////////////
	public static void main(String[] args) throws Exception {
		
		new PovoarBanco().povoar();
		
	}
	
	
	
	
	public void povoar() throws Exception {
		
		List<Integer> listaIdsProdutosCadstrados = inserirProdutos(quantidadeProdutos);
		
		interirPedidos(quantidadePedidos, listaIdsProdutosCadstrados);
		
	}
	
	
	
	
	
	
	public List<Integer> interirPedidos(int quantidadePedidos, List<Integer> listaIdsProdutosCadstrados) throws Exception {
		
		List<Integer> listaIdsPedidosCadstrados = new ArrayList<Integer>();
		
		/*
		 * Gerador de pedidos
		 */
		
		for (int i = 0; i < quantidadePedidos; i++) {
			
			Pedido novoPedido = geradorDados.gerarNovoPedido(listaIdsProdutosCadstrados);
			
			int idPedidoInserido = controlePedido.inserir(novoPedido);
			
			listaIdsPedidosCadstrados.add(idPedidoInserido);
			
			System.out.println("Pedidos inseridos: " + (i + 1));
			
		}
		return listaIdsPedidosCadstrados;
		
	}
	
	
	
	
	

	public List<Integer> inserirProdutos(int quantidadeProdutos) throws Exception {
		
		List<Integer> listaIdsProdutosCadstrados = new ArrayList<Integer>();
		
		
		/*
		 * Gerador de produtos
		 */
		for (int i = 0; i < quantidadeProdutos; i++) {
			
			Produto novoProduto = geradorDados.gerarNovoProduto();
			
			listaIdsProdutosCadstrados.add(controleProduto.inserir(novoProduto));
			
			System.out.println("Produtos inseridos: " + (i + 1));
			
			
		}
		
		return listaIdsProdutosCadstrados;
		
	}



}
