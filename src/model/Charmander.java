package model;

public class Charmander extends Pokemon {
    
    public Charmander() {
        super("Charmander", 65, 23, 7);
    }

    @Override 
    public void usarHabilidade(Pokemon alvo) {

        int dano = 40;

        System.out.println("🔥 Charmander usou Brasa!");
        System.out.println("Causou " + dano + " de dano");

        alvo.receberDano(dano);
    }
}
