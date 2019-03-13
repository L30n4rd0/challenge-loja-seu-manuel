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

import br.leo.lojaSeuManuel.modelo.vo.AtributoCustomizavel;
import br.leo.lojaSeuManuel.modelo.vo.ItemPedido;
import br.leo.lojaSeuManuel.modelo.vo.Pedido;
import br.leo.lojaSeuManuel.modelo.vo.Produto;

/**
 * @author leonardo
 *
 */
public class GeradorDados {
	
	private Locale brasil = new Locale("pt", "BR");
	
	private Faker faker = new Faker(brasil);
	
	
	
	
	
	public Pedido gerarNovoPedido(List<Integer> listaIdsProdutosCadstrados) throws Exception {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dataInicial = new Date(simpleDateFormat.parse("2008-01-01").getTime());
		
		Date dataFinal = new Date(System.currentTimeMillis());
		
		String pedidoNomeComprador= faker.name().nameWithMiddle();
		
		Date pedidoDateCcompra = faker.date().between(dataInicial, dataFinal);
		
		String pedidoCodigo = "ped" + faker.number().numberBetween(100, 500);
		
		String pedidoValorFrete = faker.commerce().price(10, 40);
		
		String pedidoEstado = gerarEstado();
		
		List<ItemPedido> listaItensPedido = gerarListaItensPedido(listaIdsProdutosCadstrados);
		
		return new Pedido(
				pedidoCodigo, 
				new java.sql.Date(pedidoDateCcompra.getTime()), 
				pedidoNomeComprador, 
				pedidoEstado, 
				Formatador.formatarDoubeParaDoisDecimais(Double.parseDouble(pedidoValorFrete)), 
				listaItensPedido
		);
		
	}


	
	
	
	
	
	public Produto gerarNovoProduto() {
		

		String produtoPreco = faker.commerce().price();
		
		String produtoNome = faker.commerce().productName();
		
		int produtoEstoque = faker.number().numberBetween(100, 200);
		
		String produtoCodigo = "prod" + faker.number().numberBetween(100, 999);
		
		String produtoDescricao = faker.lorem().sentence();
		
		List<AtributoCustomizavel> atributosCustomizaveis = gerarAtributosCustomizaveis();

		return new Produto(
				produtoCodigo, 
				produtoNome, 
				produtoDescricao, 
				produtoEstoque, 
				Formatador.formatarDoubeParaDoisDecimais(Double.parseDouble(produtoPreco)), 
				atributosCustomizaveis
				);
		
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
	
	


	
	
	private List<ItemPedido> gerarListaItensPedido(List<Integer> listaIdsProdutosCadstrados) {
		
		List<ItemPedido> listaItensPedido = new ArrayList<ItemPedido>();
		
		int itensPorPedido = faker.number().numberBetween(2, 10);
		
		for (int j = 0; j < itensPorPedido; j++) {
			
			int indiceIdProduto = faker.number().numberBetween(0, listaIdsProdutosCadstrados.size() - 1);
			
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
	

}
