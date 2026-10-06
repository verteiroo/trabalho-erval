package service;

import java.util.Scanner;

import model.Pokemon;
import model.PokemonSelvagem;

import util.GeradorInimigos;

public class JogoService {

    private BatalhaService batalhaService;

    public JogoService() {
        batalhaService = new BatalhaService();
    }

    public void iniciarAventura(Pokemon jogador, Scanner scanner) {

        System.out.println();
        System.out.println("==============================");
        System.out.println("       INÍCIO DA AVENTURA");
        System.out.println("==============================");

        for (int numeroBatalha = 1; numeroBatalha <= 5; numeroBatalha++) {

            System.out.println();
            System.out.println("===== BATALHA " + numeroBatalha + " =====");

            PokemonSelvagem inimigo =
                    GeradorInimigos.gerarInimigo();

            System.out.println();
            System.out.println("Um "
                    + inimigo.getNome()
                    + " apareceu!");

            boolean venceu =
                    batalhaService.iniciarBatalha(
                            jogador,
                            inimigo,
                            scanner
                    );

            if (!venceu) {

                System.out.println();
                System.out.println("A aventura terminou.");

                return;
            }

            System.out.println();
            System.out.println(
                    "Prepare-se para o próximo desafio!"
            );
        }

        System.out.println();
        System.out.println("==============================");
        System.out.println("     AVENTURA CONCLUÍDA!");
        System.out.println("==============================");

        jogador.mostrarStatus();
    }
}