package br.com.digital.innovation.one.avancado.processamento_assincrono_paralelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class FutureExemplo {
	
	private static final ExecutorService threadPool = Executors.newFixedThreadPool(3);

	public static void main(String[] args) throws InterruptedException {
		Casa casa = new Casa(new Quarto());
		List<Future<String>> futuros = new CopyOnWriteArrayList<>(casa.obterAfazeresDoComodo().stream()
				.map(atividade -> threadPool.submit(() -> {
					try {
						return atividade.realizar();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return null;
				}))
				.collect(Collectors.toList()));
		
		while(true){
			int numeroDeAtividadesNaoRealizados = 0;
			for(Future<?> futuro : futuros) {
				if(futuro.isDone()) {
					try {
						System.out.println("Parabéns você terminou de "+futuro.get());
						futuros.remove(futuro);
					} catch (InterruptedException | ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					numeroDeAtividadesNaoRealizados++;
				}
			}
			
			if(futuros.stream().allMatch(Future::isDone)) {
				break;
			}
			
			System.out.println("Numero de atividades não realizados :: "+numeroDeAtividadesNaoRealizados);
			Thread.sleep(500);
		}
		
		threadPool.shutdown();
	}

}

class Casa{
	
	private List<Comodo> comodos;
	
	Casa(Comodo... comodos){
		this.comodos = Arrays.asList(comodos);
	}
	
	List<Atividade> obterAfazeresDoComodo(){
		return this.comodos.stream().map(Comodo::obterAfazeresDoComodo)
						.reduce(new ArrayList<Atividade>(), (pivo,atividades) -> {
							pivo.addAll(atividades);
							return pivo;
						});
	}
}

interface Atividade{
	String realizar() throws InterruptedException;
}

abstract class Comodo{
	abstract List<Atividade> obterAfazeresDoComodo();
}

class Quarto extends Comodo{

	@Override
	List<Atividade> obterAfazeresDoComodo() {
		return Arrays.asList(
				this::arrumarACama,
				this::varrerOQuarto,
				this::arrumarGuardaRoupa);
	}
	
	private String arrumarGuardaRoupa() throws InterruptedException {
		Thread.sleep(5000);
		String arr = "Arrumar o guarda roupa";
		System.out.println(arr);
		return arr;};
	
	private String varrerOQuarto() throws InterruptedException {
		Thread.sleep(7000);
		String varr = "Varrer o quarto";
		System.out.println(varr);
		return varr;};
	
	private String arrumarACama() throws InterruptedException {
		Thread.sleep(10000);
		String arr = "Arrumar a cama";
		System.out.println(arr);
		return arr;};
	
}
