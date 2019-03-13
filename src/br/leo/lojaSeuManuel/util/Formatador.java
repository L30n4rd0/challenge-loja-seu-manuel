package br.leo.lojaSeuManuel.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Formatador {
	
	public static double formatarDoubeParaDoisDecimais(double valor) {
		
		return formatarDouble(valor, 2);
		
	}
	
	public static double formatarDouble(double valor, int casasDecimais) {
	    
		if (casasDecimais < 0) throw new IllegalArgumentException();

	    BigDecimal bigDecimal = new BigDecimal(valor);
	    
	    bigDecimal = bigDecimal.setScale(casasDecimais, RoundingMode.HALF_UP);
	    
	    return bigDecimal.doubleValue();
	}

}
