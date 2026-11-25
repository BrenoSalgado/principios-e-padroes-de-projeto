public class ApresentacaoDetalhada implements EstrategiaApresentacao {
    public String apresentar(String noticia) {
        return "DETALHES: " + noticia;
    }
}