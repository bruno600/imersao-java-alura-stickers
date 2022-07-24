package imersao.java.alura;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import imersao.java.alura.extrator.ExtratorDeConteudo;
import imersao.java.alura.extrator.ExtratorDeConteudoDaNasa;
import imersao.java.alura.extrator.ExtratorDeConteudoDoIMDB;
import imersao.java.alura.extrator.ExtratorDeConteudoLinguagem;

public class App {

	public static void main(String[] args) throws Exception {
		// criando o client HTTP e fazendo uma busca
		
//		String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
//		ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
		
		String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
		ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
		
//		String url = "http://localhost:8080/linguagens";
//		ExtratorDeConteudo extrator = new ExtratorDeConteudoLinguagem();
		
		var http = new ClienteHttp();
		String json = http.buscaDados(url);
		
		// exibir os dados e manipular os dados
		List<Conteudo> conteudos = extrator.extraiConteudos(json);
		
		var geradora = new GeradorDeFigurinhas();
		
		for (int i = 0 ; i < 2; i++) {
			Conteudo conteudo = conteudos.get(i);
			
			InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
			String nomeArquivo = conteudo.titulo() + ".png";
			
			geradora.cria(inputStream, nomeArquivo);
			
			System.out.print("\u001b[47m\u001b[1m Título:\u001b[m ");
			System.out.println(conteudo.titulo());
			
			System.out.println();
		}
		
	}

}
