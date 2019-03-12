package br.leo.lojaSeuManuel.util;

public class FormatarValor {
	
	public static double formatarDoube(double valor) {
		
		String resultado = String.format("%.2f", valor);
		
		return Double.parseDouble(resultado);
		
	}

}
