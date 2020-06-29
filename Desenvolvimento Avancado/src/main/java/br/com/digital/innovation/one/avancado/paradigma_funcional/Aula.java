package br.com.digital.innovation.one.avancado.paradigma_funcional;

import java.util.function.UnaryOperator;

public class Aula {

	public static void main(String[] args) {
		UnaryOperator<Integer> calcularValorVezesTrinta = valor -> valor * 3; // conceito do paradigma funcional
		int valor  = 10;
		System.out.println("O resultado é :: "+ calcularValorVezesTrinta.apply(10));
	}

}
