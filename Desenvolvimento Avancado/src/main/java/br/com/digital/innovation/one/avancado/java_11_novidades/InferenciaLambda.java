package br.com.digital.innovation.one.avancado.java_11_novidades;

import java.util.function.Function;

public class InferenciaLambda {

	public static void main(String[] args) {
		
		Function<Integer,Double> divisaoPorDois = (var numero) -> numero  / 2.0;
		
		System.out.println(divisaoPorDois.apply(12000));
	}
}
