// Personagem.java
public abstract class Personagem {
    protected String nome;
    protected int vida;
    protected EstrategiaAtaque estrategiaAtaque;

    public Personagem(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
    }

    public void setEstrategiaAtaque(EstrategiaAtaque estrategiaAtaque) {
        this.estrategiaAtaque = estrategiaAtaque;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public void atacar() {
        if (estrategiaAtaque != null) {
            estrategiaAtaque.atacar(nome);
        } else {
            System.out.println(nome + " não tem estratégia de ataque definida!");
        }
    }
}