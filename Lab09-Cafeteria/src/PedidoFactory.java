public class PedidoFactory {
    public static Command criarPedido(String tipo, Cozinheiro cozinheiro) {
        switch (tipo.toUpperCase()) {
            case "CAFE":
                return new PedidoCafe(cozinheiro);
            case "CHA":
                return new PedidoCha(cozinheiro);
            case "CHOCOLATE": // Adaptação do Desafio Extra
                return new PedidoChocolate(cozinheiro);
            default:
                throw new IllegalArgumentException("Tipo de pedido desconhecido: " + tipo);
        }
    }
}