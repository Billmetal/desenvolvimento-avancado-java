package br.com.digital.innovation.one.avancado.java_11_novidades;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class ClientHttpExemplo {
	
	static ExecutorService executor = Executors.newFixedThreadPool(6, new ThreadFactory() {
		
		@Override
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r);
			System.out.println("Nova Thread criada : " + (thread.isDaemon() ? "Daemon" : "") + " Thread Group : " + thread.getThreadGroup());
			return thread;
		}
	});
	
	public static void main(String[] args) {
		//connectAndPrintURLJavaOracle();
		connectAkamaiHttpClient();
	}
	
	private static void connectAkamaiHttpClient() {
		System.out.println("Running HTTP/2 example ...");
		try {
			HttpClient httpClient = HttpClient.newBuilder()
					.version(HttpClient.Version.HTTP_2)
					.proxy(ProxySelector.getDefault())
					.build();
			
			long start = System.currentTimeMillis();
			
			HttpRequest mainRequest = HttpRequest.newBuilder()
					.uri(URI.create("https://http2.akamai.com/demo/h2_demo_frame.html"))
					.build();
			
			HttpResponse<String> response = httpClient.send(mainRequest,  HttpResponse.BodyHandlers.ofString());
			
			System.out.println("Status Code : "+response.statusCode());
			System.out.println("Headers response : "+response.headers());
			String responseBody = response.body();
			System.out.println(responseBody);
			
			List<Future<?>> future = new ArrayList<>();
			
			responseBody.lines().filter(line -> line.trim().startsWith("<img height"))
							.map(line -> line.substring(line.indexOf("src='") + 5, line.indexOf("'/>")))
							.forEach(image -> {
								Future<?> imgFuture = executor.submit(() -> {
									HttpRequest imgRequest = HttpRequest.newBuilder()
											.uri(URI.create("https://http2.akamai.com" + image))
											.build();
									
									try {
										HttpResponse<String> imgResponse = httpClient.send(imgRequest,  HttpResponse.BodyHandlers.ofString());
										System.out.println("Imagem carregada : "+image+"Status Code : "+imgResponse.statusCode());
										
									} catch (IOException | InterruptedException e) {
										System.out.println("Mensagem de erro durante requisição para recuperar a imagem "+image);
									}
								});
								future.add(imgFuture);
								System.out.println("Submetido um future para imagem : "+image);
							});
			
			future.forEach(f -> {
				try {
					f.get();
				} catch (InterruptedException | ExecutionException e) {
					System.out.println("Erro ao esperar carregar imagem no future");
				}
			});
			
			long fim = System.currentTimeMillis();
			
			System.out.println("Tempo :: " + (fim - start));
			
		} catch(IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}
	}
	
	private static void connectAndPrintURLJavaOracle(){
		HttpRequest request = HttpRequest.newBuilder()
				.GET().uri(URI.create("https://docs.oracle.com/javase/10/language/"))
				.build();
		
		HttpClient httpClient = HttpClient.newHttpClient();
		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			
			System.out.println("Status Code : "+response.statusCode());
			System.out.println("Headers response : "+response.headers());
			System.out.println(response.body());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
