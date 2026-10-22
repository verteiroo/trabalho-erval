package main;

import java.util.Scanner;

import model.Pokemon;
import model.Pikachu;
import model.Charmander;
import model.Squirtle;
import model.Treinador;

import service.JogoService;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean jogoRodando = true;

        while (jogoRodando) {

            System.out.println();
            System.out.println("==============================");
            System.out.println("        POKÉMON PCD");
            System.out.println("==============================");
            System.out.println("1 - Novo Jogo");
            System.out.println("2 - Continuar Jogo");
            System.out.println("3 - Ranking");
            System.out.println("4 - Pokédex");
            System.out.println("5 - Instruções");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            try {

                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {

                    case 1:
                        novoJogo(scanner);
                        break;

                    case 2:
                        System.out.println();
                        System.out.println(
                            "O sistema de salvamento ainda será desenvolvido."
                        );
                        break;

                    case 3:
                        System.out.println();
                        System.out.println(
                            "O ranking ainda será desenvolvido."
                        );
                        break;

                    case 4:
                        mostrarPokedex();
                        break;

                    case 5:
                        mostrarInstrucoes();
                        break;

                    case 6:
                        System.out.println();
                        System.out.println(
                            "Obrigado por jogar Pokémon PCD!"
                        );

                        jogoRodando = false;
                        break;

                    default:
                        System.out.println();
                        System.out.println("Opção inválida!");
                }

            } catch (NumberFormatException e) {

                System.out.println();
                System.out.println("Digite apenas números!");
            }
        }

        scanner.close();
    }

    public static void novoJogo(Scanner scanner) {

        System.out.println();
        System.out.println("==============================");
        System.out.println("          NOVO JOGO");
        System.out.println("==============================");

        System.out.print("Digite o nome do treinador: ");

        String nomeTreinador = scanner.nextLine().trim();

        if (nomeTreinador.isEmpty()) {
            System.out.println("O nome não pode ficar vazio!");
            return;
        }

        System.out.println();
        System.out.println("Olá, " + nomeTreinador + "!");
        System.out.println();
        System.out.println("Escolha seu Pokémon inicial:");
        System.out.println();
        System.out.println("1 - Pikachu ⚡");
        System.out.println("2 - Charmander 🔥");
        System.out.println("3 - Squirtle 💧");
        System.out.print("Escolha: ");

        int escolha;

        try {
            escolha = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Digite apenas números!");
            return;
        }

        Pokemon pokemonEscolhido;

        switch (escolha) {

            case 1:
                pokemonEscolhido = new Pikachu();
                break;

            case 2:
                pokemonEscolhido = new Charmander();
                break;

            case 3:
                pokemonEscolhido = new Squirtle();
                break;

            default:
                System.out.println("Pokémon inválido!");
                return;
        }

        System.out.println();
        System.out.println("==============================");
        System.out.println("      POKÉMON ESCOLHIDO!");
        System.out.println("==============================");

        System.out.println("Treinador: " + nomeTreinador);

        pokemonEscolhido.mostrarStatus();

        Treinador treinador = new Treinador(nomeTreinador);

        treinador.adicionarPokemon(pokemonEscolhido);

        System.out.println();
        System.out.println("Você recebeu 5 Pokébolas!");
        System.out.println("Sua aventura vai começar!");

        JogoService jogoService = new JogoService();

        jogoService.iniciarAventura(
            treinador,
            scanner
        );
    }

    public static void mostrarPokedex() {

        System.out.println();
        System.out.println("=========== POKÉDEX ===========");
        System.out.println();
        System.out.println("001 - Pikachu ⚡");
        System.out.println("002 - Charmander 🔥");
        System.out.println("003 - Squirtle 💧");
        System.out.println();
        System.out.println(
            "Mais Pokémon serão adicionados futuramente."
        );
    }

    public static void mostrarInstrucoes() {

        System.out.println();
        System.out.println("========= INSTRUÇÕES =========");
        System.out.println();
        System.out.println("1. Comece um novo jogo.");
        System.out.println("2. Escolha seu Pokémon inicial.");
        System.out.println("3. Enfrente Pokémon selvagens.");
        System.out.println("4. Use ataques e habilidades.");
        System.out.println("5. Capture Pokémon com Pokébolas.");
        System.out.println("6. Ganhe experiência e suba de nível.");
        System.out.println("7. Monte sua equipe Pokémon.");
    }
}
