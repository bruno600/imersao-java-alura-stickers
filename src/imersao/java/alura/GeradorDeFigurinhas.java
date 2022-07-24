package imersao.java.alura;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {

	public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

		// leitura da imagem
		// InputStream inputStream = new FileInputStream(new File(System.getProperty("user.dir") + "/entrada/filme-maior.jpg" ));
		// InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMGEzN2VkMmUtMGM1Zi00Y2U1LTlkMDktMTlhMTdmYzZmZDlhXkEyXkFqcGdeQXVyODEyNjEwMDk@").openStream();
		BufferedImage imagemOriginal = ImageIO.read(inputStream);

		// cria nova imagem em memória com transparência e com tamanho novo
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 200;
		BufferedImage newImage = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

		// copiar a imagem original pra a nova imagem (em memória)
		Graphics2D graphics = (Graphics2D) newImage.getGraphics();
		graphics.drawImage(imagemOriginal, 0, 0, null);
		
		// configurar a fonte
		var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
		graphics.setColor(Color.BLUE);
		graphics.setFont(fonte);

		// escrever uma frase na nova imagem
		graphics.drawString("ELE MORRE NO FINAL", 10, novaAltura - 100);

		// escrever a nova imagem em um arquivo
		File saida = new File("saida");
		if (!saida.exists()) {
			saida.mkdirs();
		}
		// problema no windows com o nome do arquivo não poder haver alguns caracteres (\/*:?<>|)
		String [] novoNome = nomeArquivo.split("[:-?]");
		nomeArquivo = String.join("", novoNome);

		ImageIO.write(newImage, "png", new File(System.getProperty("user.dir").concat("/saida/"+nomeArquivo)));
	}

}
