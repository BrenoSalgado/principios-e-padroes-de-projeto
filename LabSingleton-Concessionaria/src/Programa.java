public class Programa {
    public static void main(String[] args) {
        Vendedor v1 = Vendedor.getInstance();

        Estoque estoque = new Estoque(v1);
        Financeiro financeiro = new Financeiro(v1);
        v1.adicionarObservador(estoque);
        v1.adicionarObservador(financeiro);

        v1.setEstrategia(new DescontoNormal());

        Documento docHtml = new DocumentoHtml();
        ProcessamentoVenda procPresencial = new VendaPresencial(v1, docHtml);
        procPresencial.executarVenda("Focus 2018", 50000.0);

        v1.setEstrategia(new DescontoPromocional());

        ComponentePdf legado = new ComponentePdf();
        Documento docPdf = new DocumentoPdf(legado);
        ProcessamentoVenda procOnline = new VendaOnline(v1, docPdf);
        procOnline.executarVenda("Versa 2021", 60000.0);

        Vendedor v2 = Vendedor.getInstance();
        System.out.println("\nMesmo vendedor (v1 == v2)? " + (v1 == v2));

        v1.setEstrategia(new DescontoPromocional());
        v1.removerObservador(financeiro);

        ProcessamentoVenda procPresencialC = new VendaPresencial(v1, docHtml);
        procPresencialC.executarVenda("Up 2025", 80000.0);
    }
}
