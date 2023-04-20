import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Main {
    private static String body;

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e buscar o top 250 filmes
        // String url =
        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
        // // Series
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060"; // filmes
        URI endereco = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> Response = client.send(request, BodyHandlers.ofString());
        String body = Response.body();
        // System.out.println(body);

        // Extrair só os dados que interessam(titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilme = parser.parse(body);
        System.out.println("\n");// Só para dar um espaço na hora da execução

        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
        // Exibir e manipular os dados
        for (int j = 0; j <= 15; j++) { // Exibindo só 15, mas também pode ser um for (Map<String, String> filme :
                                        // listaDeFilme.get(j); )
            Map<String, String> filme = listaDeFilme.get(j); // \u001b[1m transforma em Bold e o \u001b[m reseta;

            String urlImagem = filme.get("image"); //Retira do URL alguns caracteres que garantem a imagem pequena - assim temos imagens em HD
            String titulo = filme.get("title");

            InputStream imagemOriginalNet = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".jpeg";

            geradora.cria(imagemOriginalNet, nomeArquivo);

            System.out.println("\u001b[1m Titulo:\u001b[m \u001b[33m" + filme.get("title") + "\u001b[m");
            System.out.println("\u001b[1m URL do Poster:\u001b[m \u001b[34m" + filme.get("image") + "\u001b[m");
            System.out
                    .println("\u001b[1m Nota imDB:\u001b[m \u001b[32m" + filme.get("imDbRating") + "\u001b[m" + " - ");
            double notaStar = Double.parseDouble(filme.get("imDbRating"));// Transformando o imDBRating que é float em
                                                                          // inteiro
            int numStar = (int) notaStar;

            String star = "";
            for (int i = 0; i < numStar; i++) {
                star = star + "⭐";
            }
            System.out.print(star);
            System.out.println("\n");

        }

    }
}
