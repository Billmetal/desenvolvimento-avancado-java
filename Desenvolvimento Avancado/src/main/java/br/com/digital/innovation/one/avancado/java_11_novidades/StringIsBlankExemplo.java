package br.com.digital.innovation.one.avancado.java_11_novidades;

public class StringIsBlankExemplo {

	public static void main(String[] args) {
		
		String espaco = "              ";
		System.out.println(espaco == null || espaco.length() == 0 || espaco.chars().allMatch(c -> c == ' '));
		
		System.out.println(espaco.isBlank());
	}

}
