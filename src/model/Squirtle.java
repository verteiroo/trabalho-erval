package model;

public class Squirtle extends Pokemon {
    
    public Squirtle() {
        super("Squirtle", 75, 18, 10);
    }

    @Override 
    public void usarHabilidade(Pokemon alvo) {

        int dano = 32;

        System.out.println("💧 Squirtle usou Jato d'Água!");
        System.out.println("Causou " + dano + " de dano.");

        alvo.receberDano(dano);
    }
}
