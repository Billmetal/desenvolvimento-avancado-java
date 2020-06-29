package br.com.digital.innovation.one.avancado.paradigma_funcional;

public class FuncaoComLambda {

	public static void main(String[] args) {
		
		Funcao adicionarPrefixoSr = valor -> "Sr. "+valor;
		System.out.println(adicionarPrefixoSr.gerar("João"));
	}

}
