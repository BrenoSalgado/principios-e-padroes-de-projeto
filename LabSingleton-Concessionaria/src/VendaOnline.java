public class VendaOnline extends ProcessamentoVenda {
    public VendaOnline(Vendedor vendedor, Documento documento) {
        super(vendedor, documento);
    }

    protected void prepararVenda() {
        System.out.println("\n--- Preparando venda ONLINE via e-commerce ---");
    }
}