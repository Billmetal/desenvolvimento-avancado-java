package br.com.digital.innovation.one.avancado.interfaces_funcionais;

import java.util.function.Supplier;

public class Suppliers {

	public static void main(String[] args) {
		
		Supplier<Pessoa> supplier = Pessoa::new;
		
		System.out.println(supplier.get());
	}

}

class Pessoa {
	
	private String nome;
	private Integer idade;
	
	public Pessoa() {
		this.nome = "Roberto";
		this.idade = 30;
	}
	
	@Override
	public String toString() {
		return "nome : "+nome+", idade : "+idade;
	}
}
