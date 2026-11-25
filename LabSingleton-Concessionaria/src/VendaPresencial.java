public class VendaPresencial extends ProcessamentoVenda {
    public VendaPresencial(Vendedor vendedor, Documento documento) {
        super(vendedor, documento);
    }


    protected void prepararVenda() {
        System.out.println("\n--- Preparando venda PRESENCIAL no balcão ---");
    }
}