package br.com.digital.innovation.one.avancado.interfaces_funcionais;

import java.util.function.Function;

public class Funcoes {

	public static void main(String[] args) {
		
		Function<String,String> retornaNomeAoContrario = texto -> new StringBuilder(texto).reverse().toString();
		
		Function<String,Integer> converterStringParaInteiroDobrando = string -> Integer.valueOf(string) * 2;
		
		System.out.println(retornaNomeAoContrario.apply("Samanta"));
		
		System.out.println(converterStringParaInteiroDobrando.apply("150"));
	}

}
