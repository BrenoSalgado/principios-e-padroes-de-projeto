public class Financeiro implements ObservadorVenda {
    private Vendedor vendedor;

    public Financeiro(Vendedor vendedor) {
        this.vendedor = vendedor;
    }


    public void atualizar() {
        System.out.println("Financeiro notificado: " + vendedor.getUltimoVeiculoVendido() + " - Valor: " + vendedor.getUltimoPrecoFinal());
    }
}