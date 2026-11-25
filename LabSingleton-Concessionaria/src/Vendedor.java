import java.util.ArrayList;
import java.util.List;

public class Vendedor {
    private static Vendedor instancia;

    private EstrategiaDesconto estrategia;
    private List<ObservadorVenda> observadores;

    private String ultimoVeiculoVendido;
    private double ultimoPrecoFinal;

    private Vendedor() {
        System.out.println("Inicializando Vendedor...");
        this.observadores = new ArrayList<>();
    }

    public static Vendedor getInstance() {
        if (instancia == null) {
            instancia = new Vendedor();
        }
        return instancia;
    }

    public void setEstrategia(EstrategiaDesconto estrategia) {
        this.estrategia = estrategia;
    }

    public double calcularPrecoFinal(double preco) {
        return estrategia.calcular(preco);
    }

    public void adicionarObservador(ObservadorVenda o) {
        observadores.add(o);
    }

    public void removerObservador(ObservadorVenda o) {
        observadores.remove(o);
    }

    public void notificarObservadores() {
        for (ObservadorVenda o : observadores) {
            o.atualizar();
        }
    }

    public String getUltimoVeiculoVendido() {
        return ultimoVeiculoVendido;
    }

    public double getUltimoPrecoFinal() {
        return ultimoPrecoFinal;
    }

    public void concluirVenda(String veiculo, double precoFinal) {
        this.ultimoVeiculoVendido = veiculo;
        this.ultimoPrecoFinal = precoFinal;

        System.out.println("Vendedor registou venda: " + veiculo + " por " + precoFinal);
        notificarObservadores();
    }
}