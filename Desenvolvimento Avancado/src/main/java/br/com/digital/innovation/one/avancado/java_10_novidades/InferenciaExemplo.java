package br.com.digital.innovation.one.avancado.java_10_novidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class InferenciaExemplo {

	public static void main(String[] args){
		//printarSoma(5,5,5);
		connectAndPrintURLJavaOracle();
	}
	
	private static void connectAndPrintURLJavaOracle(){
		try {
		var url = new URL("https://docs.oracle.com/javase/10/language/");
		var urlConnection = url.openConnection();
		
			try(var bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));){
				System.out.println(bufferedReader.lines().collect(Collectors.joining()).replaceAll(">", ">\n"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void printarSoma(int... numeros) {
		var soma = 0;
		if(numeros.length > 0) {
			for(var numero : numeros) {
				soma += numero;
			}
			System.out.println("A soma é ::"+soma);
		}
	}

}
