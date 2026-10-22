package item;

import java.util.Random;
import model.PokemonSelvagem;

public class Pokebola {
    private Random random = new Random();

    public boolean tentarCapturar(PokemonSelvagem pokemon) {
        double proporcaoVida = (double) pokemon.getVida() / pokemon.getVidaMaxima();

        double chanceCaptura = 0.80 - (proporcaoVida * 0.50);

        return random.nextDouble() < chanceCaptura;
    }
}
