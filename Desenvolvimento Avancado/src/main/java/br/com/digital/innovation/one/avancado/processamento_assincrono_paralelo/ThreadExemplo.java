package br.com.digital.innovation.one.avancado.processamento_assincrono_paralelo;

public class ThreadExemplo {
	
	public static void main(String[] args) {
		
		GeradorPDF geradorPDF = new GeradorPDF();
		
		BarraDeCarregamento barraCarregamento = new BarraDeCarregamento(geradorPDF);
		
		geradorPDF.start();
		barraCarregamento.start();
		
	}

}


class GeradorPDF extends Thread{

	@Override
	public void run() {
		try {
			System.out.println("Iniciando ...");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("PDF gerado !!!");
	}
	
}


class BarraDeCarregamento extends Thread{
	
	private Thread geradorPdf;
	
	public BarraDeCarregamento(Thread geradorPdf) {
		this.geradorPdf = geradorPdf;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!geradorPdf.isAlive()) {
				break;
			}
			System.out.println("Loading ...");
		}
	}
	
}
