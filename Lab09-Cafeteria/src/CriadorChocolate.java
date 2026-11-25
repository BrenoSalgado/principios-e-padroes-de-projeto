// CriadorChocolate.java (Adaptação do Desafio Extra)
public class CriadorChocolate extends CriadorPedido {
    public Command criarPedido(Cozinheiro cozinheiro) {
        return new PedidoChocolate(cozinheiro);
    }
}