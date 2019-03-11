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
		
		Date dataInicial = null;
		try {
			dataInicial = new Date(simpleDateFormat.parse("01-01-2018").getTime());
			
			throw new Exception("sfdsdfsdf");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
		Date dataFinal = new Date(System.currentTimeMillis());
		
		Faker faker = new Faker(new Locale("pt-BR"));

		String name = faker.name().fullName(); // Miss Samanta Schmidt
		Date date = faker.date().between(dataInicial, dataFinal);
		String streetAddress = faker.address().city(); // 60018 Sawayn Brooks Suite 449
		String preco = faker.commerce().price();
		
		
//		System.out.println(name);
		System.out.println(simpleDateFormat.format(date));
//		System.out.println(new java.sql.Date(date.getTime()));
//		System.out.println(new Date(1000000));
//		System.out.println(new Date(System.currentTimeMillis()));
//		System.err.println(d3);
//		System.out.println(streetAddress);
//		System.out.println(preco);
		
		

		
	}

}
