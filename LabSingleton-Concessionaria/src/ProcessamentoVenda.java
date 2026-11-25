public abstract class ProcessamentoVenda {
    protected Vendedor vendedor;
    protected Documento documento;

    public ProcessamentoVenda(Vendedor vendedor, Documento documento) {
        this.vendedor = vendedor;
        this.documento = documento;
    }

    public final void executarVenda(String veiculo, double preco) {
        prepararVenda();
        double precoFinal = vendedor.calcularPrecoFinal(preco);
        vendedor.concluirVenda(veiculo, precoFinal);
        documento.setConteudo("Comprovante de Venda: " + veiculo + " | Total: " + precoFinal);
        documento.desenha();
        documento.imprime();
    }

    protected abstract void prepararVenda();
}