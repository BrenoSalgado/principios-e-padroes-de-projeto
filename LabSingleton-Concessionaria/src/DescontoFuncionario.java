
public class DescontoFuncionario implements EstrategiaDesconto
{
    private static final double PERCENTUAL = 0.20;


    public double calcular(double preco)
    {
        return preco * (1 - PERCENTUAL);
    }


    public String getDescricao() {
        return "funcionário (20%)";
    }
}
