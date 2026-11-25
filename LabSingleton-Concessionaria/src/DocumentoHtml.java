public class DocumentoHtml implements Documento {
    private String conteudo;


    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }


    public void desenha() {
        System.out.println("Desenhando HTML no console: " + conteudo);
    }


    public void imprime() {
        System.out.println("Imprimindo HTML...");
    }
}