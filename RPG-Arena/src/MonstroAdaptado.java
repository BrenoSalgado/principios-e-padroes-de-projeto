// MonstroAdaptado.java
public class MonstroAdaptado extends Personagem {
    private MonstroAntigo monstroAntigo;

    public MonstroAdaptado(String nome) {
        super(nome, 120);
        this.monstroAntigo = new MonstroAntigo();
    }

    public void atacar() {
        monstroAntigo.executarGolpeAntigo();
    }
}