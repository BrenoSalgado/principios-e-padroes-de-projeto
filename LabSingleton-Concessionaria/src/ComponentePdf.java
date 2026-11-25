public class ComponentePdf {
    public void pdfFixaConteudo(String conteudo) {
        System.out.println("ComponentePdf: Fixando conteúdo -> " + conteudo);
    }
    public void pdfPreparaExibicao() {
        System.out.println("ComponentePdf: Preparando exibição");
    }
    public void pdfAtualiza() {
        System.out.println("ComponentePdf: Atualizando tela");
    }
    public void pdfFinalizaExibicao() {
        System.out.println("ComponentePdf: Finalizando exibição");
    }
    public void pdfEnviaImpressora() {
        System.out.println("ComponentePdf: Enviando para impressora física");
    }
}