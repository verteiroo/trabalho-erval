package model;

public class PokemonSelvagem extends Pokemon {

    public PokemonSelvagem(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
    }

    @Override
    public void usarHabilidade(Pokemon alvo) {

        int dano = getAtaque() + 5;

        System.out.println(getNome() + " usou seu ataque especial!");

        alvo.receberDano(dano);
    }
    
}
