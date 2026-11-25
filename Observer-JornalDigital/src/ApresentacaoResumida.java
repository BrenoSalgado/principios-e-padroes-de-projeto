public class ApresentacaoResumida implements EstrategiaApresentacao {
    public String apresentar(String noticia) {
        String resumo = noticia.length() > 35 ? noticia.substring(0, 35) + "..." : noticia;
        return "RESUMO: " + resumo;
    }
}