/**
 * 
 */
package br.leo.lojaSeuManuel.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;

import br.leo.lojaSeuManuel.controle.ControlePedido;
import br.leo.lojaSeuManuel.controle.ControleProduto;
import br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel;
import br.leo.lojaSeuManuel.modelo.vo.ItemPedido;
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
	
	private Locale brasil = new Locale("pt", "BR");
	
	private Faker faker = new Faker(brasil);
	
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", brasil);
	
	
	
	//////////// MAIN /////////////////
	public static void main(String[] args) throws Exception {
		
		new PovoarBanco().povoar();
		
	}
	
	
	
	public void povoar() throws Exception {
		
		List<Integer> listaIdsProdutosCadstrados = inserirProdutos();
		
		interirPedidos(listaIdsProdutosCadstrados);
		
	}
	
	
	private void interirPedidos(List<Integer> listaIdsProdutosCadstrados) throws Exception {
		
		Date dataInicial = new Date(simpleDateFormat.parse("01-01-2008").getTime());
		
		Date dataFinal = new Date(System.currentTimeMillis());
		
		
		/*
		 * Gerador de pedidos
		 */
		
		for (int i = 0; i < quantidadePedidos; i++) {
			
			String pedidoNomeComprador= faker.name().nameWithMiddle();
			
			Date pedidoDateCcompra = faker.date().between(dataInicial, dataFinal);
			
			String pedidoCodigo = "ped" + faker.number().numberBetween(100, 500);
			
			String pedidoValorFrete = faker.commerce().price(10, 40);
			
			String pedidoEstado = gerarEstado();
			
			
			List<ItemPedido> listaItensPedido = gerarListaItensPedido(listaIdsProdutosCadstrados);
			
			controlePedido.inserir(
					
					new Pedido(
							pedidoCodigo, 
							new java.sql.Date(pedidoDateCcompra.getTime()), 
							pedidoNomeComprador, 
							pedidoEstado, 
							FormatarValor.formatarDoube(Double.parseDouble(pedidoValorFrete)), 
							listaItensPedido
					)
					
			);
			
			System.out.println("Pedidos inseridos: " + (i + 1));
			
		}
		
	}


	private List<ItemPedido> gerarListaItensPedido(List<Integer> listaIdsProdutosCadstrados) {
		
		List<ItemPedido> listaItensPedido = new ArrayList<ItemPedido>();
		
		int itensPorPedido = faker.number().numberBetween(2, 5);
		
		for (int j = 0; j < itensPorPedido; j++) {
			
			int indiceIdProduto = faker.number().numberBetween(0, quantidadeProdutos - 1);
			
			int idProduto = listaIdsProdutosCadstrados.get(indiceIdProduto);
			
			int quantidadeProdutoPorItem = faker.number().numberBetween(2, 5);
			
			listaItensPedido.add(new ItemPedido(idProduto, quantidadeProdutoPorItem));
			
		}
		
		return listaItensPedido;
		
	}



	private String gerarEstado() {
		
		int indiceEstado = faker.number().numberBetween(10, 49);
		
		// Com valores pequenos no Between(1, 4) é muito difícil gerar o 4
		indiceEstado = (int) (indiceEstado / 10);
		
		String pedidoEstado = EstadoPedido.APROVADO.getValor();
		
		switch (indiceEstado) {
		
		case 1:
			pedidoEstado = EstadoPedido.APROVADO.getValor();
			
			break;
			
		case 2:
			pedidoEstado = EstadoPedido.CANCELADO.getValor();
			
			break;
			
		case 3:
			pedidoEstado = EstadoPedido.ENTREGUE.getValor();
			
			break;
			
		case 4:
			pedidoEstado = EstadoPedido.NOVO.getValor();
			
			break;

		}
		
		return pedidoEstado;
		
	}



	private List<Integer> inserirProdutos() throws Exception {
		
		List<Integer> listaIdsProdutosCadstrados = new ArrayList<Integer>();
		
		
		/*
		 * Gerador de produtos
		 */
		for (int i = 0; i < quantidadeProdutos; i++) {
			
			String produtoPreco = faker.commerce().price();
			
			String produtoNome = faker.commerce().productName();
			
			int produtoEstoque = faker.number().numberBetween(500, 1000);
			
			String produtoCodigo = "prod" + faker.number().numberBetween(100, 500);
			
			String produtoDescricao = faker.lorem().sentence();
			
			List<AtributoCustomizavel> atributosCustomizaveis = gerarAtributosCustomizaveis();
			
			listaIdsProdutosCadstrados.add(
					
					controleProduto.inserir(
							new Produto(
							produtoCodigo, 
							produtoNome, 
							produtoDescricao, 
							produtoEstoque, 
							FormatarValor.formatarDoube(Double.parseDouble(produtoPreco)), 
							atributosCustomizaveis
							)
					)
					
			);
			
			System.out.println("Produtos inseridos: " + (i + 1));
			
			
		}
		
		return listaIdsProdutosCadstrados;
		
	}


	private List<AtributoCustomizavel> gerarAtributosCustomizaveis() {
		
		List<AtributoCustomizavel> listaAtributosCustomizaveis = new ArrayList<AtributoCustomizavel>();
		
		for (int j = 0; j < 2; j++) {
			
			String atributoNome = faker.lorem().word();
			
			String atributoValor = faker.lorem().word();
			
			listaAtributosCustomizaveis.add(new AtributoCustomizavel(atributoNome, atributoValor));
			
		}
		
		return listaAtributosCustomizaveis;
		
	}

}
