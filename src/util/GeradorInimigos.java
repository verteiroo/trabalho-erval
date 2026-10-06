package util;

import java.util.Random;
import model.PokemonSelvagem;

public class GeradorInimigos {
    private static Random random = new Random();

    public static PokemonSelvagem gerarInimigo() {
        int numero = random.nextInt(4);

        switch (numero) {
            case 0:
                return new PokemonSelvagem(
                    "Rattata",
                    50,
                    12,
                    4
                );

            case 1:
                return new PokemonSelvagem(
                    "Zubat",
                    45,
                    14,
                    3
                );

            case 2:
                return new PokemonSelvagem(
                    "Geodude",
                    70,
                    10,
                    10
                );

            case 3:
                return new PokemonSelvagem(
                    "Gastly",
                    40
                    ,16
                    ,2
                );

            default:
                return new PokemonSelvagem(
                    "Rattata",
                    50,
                    12,
                    4
            );
        }
    }
}