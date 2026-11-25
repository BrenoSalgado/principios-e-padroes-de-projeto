import java.util.Locale;

public class Auditoria implements ObservadorVenda
{
    private final Vendedor vendedor;

    public Auditoria(Vendedor vendedor)
    {
        this.vendedor = vendedor;
    }

    public void atualizar()
    {
        System.out.println("Auditoria notificada: " + vendedor.getUltimoVeiculoVendido()
                + " — registrado em log com valor " + String.format(Locale.forLanguageTag("pt-BR"), "R$ %,.2f", vendedor.getUltimoPrecoFinal()));
    }
}
