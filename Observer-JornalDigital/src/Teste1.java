public class Teste1 {
    public static void main(String[] args) {
        LeFigaroDigital jornal = new LeFigaroDigital();

        EstrategiaApresentacao resumida = new ApresentacaoResumida();
        EstrategiaApresentacao detalhada = new ApresentacaoDetalhada();

        AssinanteApp joao = new AppCelular("João", jornal, resumida);
        AssinanteApp maria = new AppTablet("Maria", jornal, detalhada);
        AssinanteApp carlos = new AppWeb("Carlos", jornal, resumida);

        jornal.inscrever(joao);
        jornal.inscrever(maria);
        jornal.inscrever(carlos);

        System.out.println("> Jornal publica: Tempestade provoca alagamento da Rondom.");
        jornal.novaNoticia("Tempestade provoca da Rondom.");
    }
}