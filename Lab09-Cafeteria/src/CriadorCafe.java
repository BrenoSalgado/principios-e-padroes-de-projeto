// CriadorCafe.java
public class CriadorCafe extends CriadorPedido {
    public Command criarPedido(Cozinheiro cozinheiro) {
        return new PedidoCafe(cozinheiro);
    }
}