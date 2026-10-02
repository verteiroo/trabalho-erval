package model;

public class Pikachu extends Pokemon {

    public Pikachu() {
        super("Pikachu", 70, 20, 8);
    }

    @Override
    public void usarHabilidade(Pokemon alvo) {

        int dano = 35;

        System.out.println("⚡ Pikachu usou Choque do Trovão!");
        System.out.println("Causou " + dano + " de dano.");

        alvo.receberDano(dano);
    }
}
