package main;

import java.util.Scanner;
import util.GeradorInimigos;
import model.Pokemon;
import model.Pikachu;
import model.Charmander;
import model.Squirtle;
import service.BatalhaService;
import model.PokemonSelvagem;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean jogoRodando = true;

        while (jogoRodando) {

            System.out.println();
            System.out.println("==============================");
            System.out.println("            POKÉMON ");
            System.out.println("==============================");
            System.out.println("1 - Novo Jogo");
            System.out.println("2 - Continuar Jogo");
            System.out.println("3 - Ranking");
            System.out.println("4 - Pokédex");
            System.out.println("5 - Instruções");
            System.out.println("6 - Sair");
            System.out.println("Escolha uma opção: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {

                    case 1:
                        novoJogo(scanner);
                        break;

                    case 2:
                        System.out.println();
                        System.out.println("O sistema de continuar jogo ainda não foi criado.");
                        break;

                    case 3:
                        System.out.println();
                        System.out.println("O ranking ainda será desenvolvido.");
                        break;

                    case 4:
                        mostrarPokedex();
                        break;

                    case 5:
                        mostrarInstrucoes();
                        break;

                    case 6:
                        System.out.println();
                        System.out.println("Saindo do Pokémon...");
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
        System.out.println("========= NOVO JOGO ==========");

        System.out.println("Digite seu nome de treinador: ");
        String nomeTreinador = scanner.nextLine();

        System.out.println();
        System.out.println("Olá, " + nomeTreinador + "!");
        System.out.println("Escolha seu Pokémon inicial:");
        System.out.println();
        System.out.println("1 - Pikachu");
        System.out.println("2 - Charmander");
        System.out.println("3 - Squirtle");
        System.out.println("Escolha: ");

        try {

            int escolha = Integer.parseInt(scanner.nextLine());

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
                    System.out.println();
                    System.out.println("Pokémon inválido!");
                    return;
            }

            System.out.println();
            System.out.println("========================");
            System.out.println("   POKÉMON ESCOLHIDO");
            System.out.println("========================");

            System.out.println("Treinador: " + nomeTreinador);

            System.out.println("Pokémon: " + pokemonEscolhido.getNome());

            pokemonEscolhido.mostrarStatus();

            System.out.println();
            System.out.println("Sua aventura vai começar!...");

            BatalhaService batalhaService = new BatalhaService();

            PokemonSelvagem inimigo = GeradorInimigos.gerarInimigo();

            batalhaService.iniciarBatalha(
                pokemonEscolhido,
                inimigo,
                scanner
            );

            System.out.println();
            System.out.println("Pegue todos os pokémons que você conseguir!");
        } catch (NumberFormatException e) {

            System.out.println();
            System.out.println("Digite apenas números!");
        }
    }

    public static void mostrarPokedex() {
        System.out.println();
        System.out.println("============= POKÉDEX ==============");
        System.out.println();
        System.out.println("001 - Pikachu");
        System.out.println("002 - Charmander");
        System.out.println("003 - Squirtle");
        System.out.println();
        System.out.println("Mais pokémon serão adicionados futuramente.");
    }

    public static void mostrarInstrucoes() {
        System.out.println();
        System.out.println("============= INTRUÇÕES ============");
        System.out.println();
        System.out.println("1. Comece um novo jogo.");
        System.out.println("2. Escolha seu Pokémon inicial.");
        System.out.println("3. Enfrente Pokémons selvagens.");
        System.out.println("4. Ataque seus adversários.");
        System.out.println("5. Ganhe experiência.");
        System.out.println("6. Evolua seu Pokémon.");
        System.out.println("7. Consiga a maior pontuação possível.");
    }
}