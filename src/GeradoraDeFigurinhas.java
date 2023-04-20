import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    /**
     * @throws IOException
     * 
     */
    public void cria (InputStream imagemOrinalNet, String nomeArquivo ) throws IOException{ //String pathResultFigurinha
        //Paths
        String pathResultFigurinha = "result/" + nomeArquivo;

        //Leitura da imagem
         //try,catch
        BufferedImage imagemOriginal = ImageIO.read(imagemOrinalNet);

        //Cria nova imagem em memoria com transparencia e tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        
        //copiar imagem original para novo imagem (em memoria)
        Graphics graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null, null);

        //Setar Fonte
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 55);
        graphics.setFont(fonte);
        //frase na imagem
        graphics.setColor(Color.RED);
        graphics.drawString("Eu... Eu bebi água", 25, novaAltura - 100);

        //escrever a nova imagem em arquivo
        File criarPasta = new File(pathResultFigurinha); //Caso não tenha a pasta "saida" ele irá criar
        if(!criarPasta.exists()){
            criarPasta.mkdirs();
        }
        ImageIO.write(novaImagem,"png", new File (pathResultFigurinha));

    }

}
