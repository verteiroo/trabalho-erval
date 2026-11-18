
package service;

import java.util.Scanner;

import model.Pokemon;
import model.PokemonSelvagem;
import model.Treinador;

import repository.RankingRepository;
import repository.JogoSaveRepository;

import util.GeradorInimigos;

public class JogoService {

    private BatalhaService batalhaService;
    private RankingRepository rankingRepository;
    private JogoSaveRepository saveRepository;

    public JogoService() {
        batalhaService = new BatalhaService();
        rankingRepository = new RankingRepository();
        saveRepository = new JogoSaveRepository();
    }

    public void iniciarAventura(Treinador treinador, Scanner scanner) {
        iniciarAventura(treinador, scanner, 1);
    }

    public void iniciarAventura(
            Treinador treinador,
            Scanner scanner,
            int batalhaInicial) {

        Pokemon jogador = treinador.getPokemonPrincipal();

        if (jogador == null) {
            System.out.println("O treinador não possui Pokémon!");
            return;
        }

        System.out.println();
        System.out.println("==============================");
        System.out.println("       INÍCIO DA AVENTURA");
        System.out.println("==============================");

        for (int numeroBatalha = batalhaInicial;
             numeroBatalha <= 5;
             numeroBatalha++) {

            System.out.println();
            System.out.println("===== BATALHA " + numeroBatalha + " =====");

            saveRepository.salvar(treinador, numeroBatalha);

            PokemonSelvagem inimigo =
                    GeradorInimigos.gerarInimigo();

            System.out.println();
            System.out.println("Um "
                    + inimigo.getNome()
                    + " apareceu!");

            boolean venceu = batalhaService.iniciarBatalha(
                    jogador,
                    inimigo,
                    treinador,
                    scanner
            );

            if (!venceu) {

                System.out.println("A aventura terminou.");

                rankingRepository.salvarPontuacao(
                        treinador.getNome(),
                        treinador.getPontuacao()
                );

                saveRepository.excluirSalvamento();
                return;
            }

            saveRepository.salvar(treinador, numeroBatalha + 1);

            System.out.println();
            System.out.println(
                    "Prepare-se para o próximo desafio!"
            );
        }

        System.out.println();
        System.out.println("==============================");
        System.out.println("     AVENTURA CONCLUÍDA!");
        System.out.println("==============================");

        treinador.adicionarPontos(50);

        rankingRepository.salvarPontuacao(
                treinador.getNome(),
                treinador.getPontuacao()
        );

        saveRepository.excluirSalvamento();

        jogador.mostrarStatus();
        treinador.mostrarEquipe();
    }
}
