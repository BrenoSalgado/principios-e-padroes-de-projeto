// FabricaPersonagens.java
public class FabricaPersonagens {
    public Personagem criarPersonagem(String tipo, String nome) {
        switch (tipo.toLowerCase()) {
            case "guerreiro":
                return new Guerreiro(nome);
            case "mago":
                return new Mago(nome);
            case "arqueiro":
                return new Arqueiro(nome);
            case "monstro":
                return new MonstroAdaptado(nome);
            default:
                System.out.println("Tipo de personagem desconhecido: " + tipo);
                return null;
        }
    }
}