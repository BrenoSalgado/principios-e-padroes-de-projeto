// PedidoChocolate.java (Etapa 4 - Desafio Extra)
public class PedidoChocolate implements Command {
    private Cozinheiro cozinheiro;

    public PedidoChocolate(Cozinheiro cozinheiro) {
        this.cozinheiro = cozinheiro;
    }


    public void execute() {
        cozinheiro.prepararChocolate();
    }

    public void undo() {
        cozinheiro.cancelarChocolate();
    }
}