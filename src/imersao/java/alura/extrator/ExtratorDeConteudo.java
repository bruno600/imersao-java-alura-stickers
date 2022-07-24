package imersao.java.alura.extrator;

import java.util.List;

import imersao.java.alura.Conteudo;

public interface ExtratorDeConteudo {

	List<Conteudo> extraiConteudos(String json);
	
}
