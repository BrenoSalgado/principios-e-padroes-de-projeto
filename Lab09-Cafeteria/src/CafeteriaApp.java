public class CafeteriaApp {
    public static void main(String[] args) {
        Cozinheiro cozinheiro = new Cozinheiro();
        Garcom garcom = new Garcom();

        System.out.println("=== 1. USANDO SIMPLE FACTORY ===");
        Command pedido1 = PedidoFactory.criarPedido("CAFE", cozinheiro);
        garcom.setPedido(pedido1);
        garcom.levarPedido();
        garcom.cancelarUltimoPedido();

        Command pedidoExtra1 = PedidoFactory.criarPedido("CHOCOLATE", cozinheiro);
        garcom.setPedido(pedidoExtra1);
        garcom.levarPedido();

        System.out.println("\n=== 2. USANDO FACTORY METHOD ===");
        CriadorPedido criadorCha = new CriadorCha();
        Command pedido2 = criadorCha.criarPedido(cozinheiro);
        garcom.setPedido(pedido2);
        garcom.levarPedido();
        garcom.cancelarUltimoPedido();

        CriadorPedido criadorChocolate = new CriadorChocolate();
        Command pedidoExtra2 = criadorChocolate.criarPedido(cozinheiro);
        garcom.setPedido(pedidoExtra2);
        garcom.levarPedido();
    }
}