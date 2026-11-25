// CriadorCha.java
public class CriadorCha extends CriadorPedido {
    public Command criarPedido(Cozinheiro cozinheiro) {
        return new PedidoCha(cozinheiro);
    }
}