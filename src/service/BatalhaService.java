
package service;

import java.util.Scanner;

import model.Pokemon;
import model.PokemonSelvagem;
import model.Treinador;

import item.Pokebola;

public class BatalhaService {

    public boolean iniciarBatalha(
            Pokemon jogador,
            PokemonSelvagem inimigo,
            Treinador treinador,
            Scanner scanner) {

        int pocoes = 2;

        Pokebola pokebola = new Pokebola();

        System.out.println();
        System.out.println("================================");
        System.out.println("          BATALHA!");
        System.out.println("================================");

        System.out.println();
        System.out.println("Um " + inimigo.getNome() + " apareceu!");

        while (jogador.estaVivo() && inimigo.estaVivo()) {

            System.out.println();
            System.out.println("--------------------------------");

            System.out.println(
                jogador.getNome() + " | Vida: "
                + jogador.getVida() + "/"
                + jogador.getVidaMaxima()
            );

            System.out.println(
                inimigo.getNome() + " | Vida: "
                + inimigo.getVida() + "/"
                + inimigo.getVidaMaxima()
            );

            System.out.println("--------------------------------");
            System.out.println("Pokébolas: " + treinador.getPokebolas());
            System.out.println("Poções: " + pocoes);

            System.out.println();
            System.out.println("1 - Atacar");
            System.out.println("2 - Habilidade Especial");
            System.out.println("3 - Usar Poção");
            System.out.println("4 - Lançar Pokébola");
            System.out.println("5 - Fugir");
            System.out.print("Escolha: ");

            try {

                int opcao = Integer.parseInt(scanner.nextLine());

                boolean turnoValido = true;

                switch (opcao) {

                    case 1:
                        jogador.atacar(inimigo);
                        break;

                    case 2:
                        jogador.usarHabilidade(inimigo);
                        break;

                    case 3:

                        if (pocoes > 0) {

                            jogador.recuperarVida(30);
                            pocoes--;

                            System.out.println();
                            System.out.println("Você usou uma Poção!");
                            System.out.println(
                                "Vida atual: "
                                + jogador.getVida() + "/"
                                + jogador.getVidaMaxima()
                            );

                        } else {

                            System.out.println(
                                "Você não possui mais poções!"
                            );
                        }

                        break;

                    case 4:

                        if (!treinador.temEspacoNaEquipe()) {

                            System.out.println(
                                "Sua equipe está cheia! "
                                + "Não é possível capturar mais Pokémon."
                            );

                            break;
                        }

                        if (!treinador.usarPokebola()) {

                            System.out.println(
                                "Você não possui mais Pokébolas!"
                            );

                            break;
                        }

                        System.out.println();
                        System.out.println(
                            "Você lançou uma Pokébola!"
                        );

                        if (pokebola.tentarCapturar(inimigo)) {

                            boolean capturou =
                                    treinador.adicionarPokemon(inimigo);

                            if (capturou) {

                                System.out.println();
                                System.out.println(
                                    "================================"
                                );
                                System.out.println(
                                    "       POKÉMON CAPTURADO!"
                                );
                                System.out.println(
                                    "================================"
                                );

                                System.out.println(
                                    inimigo.getNome()
                                    + " entrou para sua equipe!"
                                );

                                treinador.mostrarEquipe();

                                // A captura encerra a batalha.
                                return true;
                            }

                        } else {

                            System.out.println(
                                inimigo.getNome()
                                + " escapou da Pokébola!"
                            );
                        }

                        break;

                    case 5:

                        System.out.println();
                        System.out.println(
                            "Você fugiu da batalha!"
                        );

                        return false;

                    default:

                        System.out.println(
                            "Opção inválida!"
                        );

                        turnoValido = false;
                }

                // O inimigo reage após uma ação válida,
                // desde que ambos continuem vivos.

                if (turnoValido
                        && inimigo.estaVivo()
                        && jogador.estaVivo()) {

                    System.out.println();
                    System.out.println("O inimigo vai atacar!");

                    inimigo.atacar(jogador);
                }

            } catch (NumberFormatException e) {

                System.out.println(
                    "Digite apenas números!"
                );
            }
        }

        if (!jogador.estaVivo()) {

            System.out.println();
            System.out.println("================================");
            System.out.println("            DERROTA!");
            System.out.println("================================");

            System.out.println(
                jogador.getNome() + " foi derrotado."
            );

            return false;

        } else {

            System.out.println();
            System.out.println("================================");
            System.out.println("            VITÓRIA!");
            System.out.println("================================");

            System.out.println(
                inimigo.getNome() + " foi derrotado!"
            );

            jogador.ganharExperiencia(20);

            return true;
        }
    }
}
