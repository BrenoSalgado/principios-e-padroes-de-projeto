public class Teste2 {
    public static void main(String[] args) {
        LeFigaroDigital jornal = new LeFigaroDigital();
        EstrategiaApresentacao resumida = new ApresentacaoResumida();
        EstrategiaApresentacao detalhada = new ApresentacaoDetalhada();

        AppCelular ana = new AppCelular("Ana", jornal, resumida);
        AppWeb bruno = new AppWeb("Bruno", jornal, detalhada);

        jornal.inscrever(ana);
        jornal.inscrever(bruno);

        System.out.println("\n> Publicando Notícia 1...");
        jornal.novaNoticia("Resultado das urnas é publicado.");

        System.out.println("\n> Ana muda para visão detalhada...");
        ana.setEstrategia(detalhada);

        System.out.println("> Bruno cancela assinatura...");
        jornal.cancelar(bruno);

        System.out.println("\n> Publicando Notícia 2...");
        jornal.novaNoticia("Flávio bolsonaro vence a eleição presidencial.");
    }
}