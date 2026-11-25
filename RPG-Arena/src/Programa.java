import java.util.Scanner;


public class Programa {
    public static void main(String[] args) {
        FabricaPersonagens fabrica = new FabricaPersonagens();
        ArenaRPG arena = new ArenaRPG(fabrica);
        Scanner scanner = new Scanner(System.in);

        // Cenário 1: Vetor de tipos e criação interativa
        String[] tipos = {"guerreiro", "mago", "arqueiro"};

        System.out.println("=== CRIAÇÃO DE PERSONAGENS (CENÁRIO 1) ===");
        for (String tipo : tipos) {
            System.out.print("Digite o nome para o " + tipo + ": ");
            String nome = scanner.nextLine();

            Personagem p = arena.adicionarPersonagem(tipo, nome);

            // Configurar inicialmente AtaqueNormal fora da fábrica para os comuns
            if (p != null) {
                p.setEstrategiaAtaque(new AtaqueNormal());
            }
        }

        System.out.println("\n--- Executando Ataques da Arena (Cenário 1) ---");
        arena.executarAtaques();

        // Cenário 2: Troca de estratégia do Guerreiro para AtaqueEspecial
        System.out.println("\n=== TESTE DE TROCA DE ESTRATÉGIA (CENÁRIO 2) ===");
        Personagem guerreiroTeste = fabrica.criarPersonagem("guerreiro", "Arthur");
        guerreiroTeste.setEstrategiaAtaque(new AtaqueNormal());

        System.out.print("Com ataque normal: ");
        guerreiroTeste.atacar();

        // Troca para especial na mesma instância
        guerreiroTeste.setEstrategiaAtaque(new AtaqueEspecial());
        System.out.print("Com ataque especial: ");
        guerreiroTeste.atacar();

        // Cenário 3: Solicitar monstro adaptado à fábrica
        System.out.println("\n=== TESTE DO MONSTRO ADAPTADO (CENÁRIO 3) ===");
        Personagem monstro = arena.adicionarPersonagem("monstro", "Goblin Chefão");
        if (monstro != null) {
            monstro.atacar();
        }

        scanner.close();
    }
}