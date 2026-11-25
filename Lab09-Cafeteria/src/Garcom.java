public class Garcom {
    private Command pedido;
    private Command ultimoPedido;

    public void setPedido(Command pedido) {
        this.pedido = pedido;
    }

    public void levarPedido() {
        pedido.execute();
        ultimoPedido = pedido;
    }

    public void cancelarUltimoPedido() {
        ultimoPedido.undo();
    }
}