package br.com.digital.innovation.one.avancado.paradigma_funcional;

public class Aula {

	public static void main(String[] args) {
		Funcao1 funcao1 = valor -> {
			System.out.println(valor);
			System.out.println(valor);
		};
		
		funcao1.gerar("João Paulo");
	}

}

@FunctionalInterface
interface Funcao1{
	
	void gerar(String valor);
}
