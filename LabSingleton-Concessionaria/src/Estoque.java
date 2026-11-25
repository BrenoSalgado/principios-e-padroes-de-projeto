public class Estoque implements ObservadorVenda {
    private Vendedor vendedor;

    public Estoque(Vendedor vendedor) {
        this.vendedor = vendedor;
    }


    public void atualizar() {
        System.out.println("Estoque notificado: " + vendedor.getUltimoVeiculoVendido());
    }
}