package br.leo.lojaSeuManuel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.github.javafaker.Faker;

public class MainUtil {
	
	public static void main(String[] args) throws Exception {
		
		Locale brasil = new Locale("pt", "BR");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", brasil);
		
		Date dataInicial = new Date(simpleDateFormat.parse("01-01-2018").getTime());
			
		Date dataFinal = new Date(System.currentTimeMillis());
		
		Faker faker = new Faker(new Locale("pt-BR"));
		
		for (int i = 0; i < 100; i++) {
			
			String name = faker.name().nameWithMiddle(); // Miss Samanta Schmidt
			Date date = faker.date().between(dataInicial, dataFinal);
			String streetAddress = faker.address().city(); // 60018 Sawayn Brooks Suite 449
			String preco = faker.commerce().material();
			String produto = faker.commerce().productName();
			String produtoDescricao = faker.lorem().word();
			int valor = faker.number().numberBetween(10, 49);
			
			valor = (int) (valor / 10);
			
			
//			System.out.println(produtoDescricao);
//		System.out.println(simpleDateFormat.format(date));
//		System.out.println(new java.sql.Date(date.getTime()));
//		System.out.println(new Date(1000000));
//		System.out.println(new Date(System.currentTimeMillis()));
//		System.err.println(d3);
//		System.out.println(streetAddress);
//			System.out.println(produto);
//			System.out.println(preco + "\n");
			
			System.out.println("Valor: " + valor);
			
			
			
		}
		
		
	}

}
