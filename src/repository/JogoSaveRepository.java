
package repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Properties;

import model.*;

public class JogoSaveRepository {

    private final Path arquivo =
            Path.of("dados", "salvamento.properties");

    private int batalhaSalva = 1;

    public boolean existeSalvamento() {
        return Files.exists(arquivo);
    }

    public int getBatalhaSalva() {
        return batalhaSalva;
    }

    public boolean salvar(Treinador treinador, int batalhaAtual) {

        Properties dados = new Properties();
        

        String nomeCodificado = Base64.getEncoder()
                .encodeToString(
                    treinador.getNome().getBytes(StandardCharsets.UTF_8)
                );

        dados.setProperty("nome", nomeCodificado);
        dados.setProperty("pokebolas",
                String.valueOf(treinador.getPokebolas()));
        dados.setProperty("pontuacao",
                String.valueOf(treinador.getPontuacao()));
        dados.setProperty("batalha",
                String.valueOf(batalhaAtual));

        dados.setProperty("quantidadePokemon",
                String.valueOf(treinador.getEquipe().size()));

        for (int i = 0; i < treinador.getEquipe().size(); i++) {

            Pokemon pokemon = treinador.getEquipe().get(i);

            String prefixo = "pokemon." + i + ".";

            dados.setProperty(prefixo + "especie",
                    pokemon.getClass().getSimpleName());

            dados.setProperty(prefixo + "vida",
                    String.valueOf(pokemon.getVida()));

            dados.setProperty(prefixo + "vidaMaxima",
                    String.valueOf(pokemon.getVidaMaxima()));

            dados.setProperty(prefixo + "ataque",
                    String.valueOf(pokemon.getAtaque()));

            dados.setProperty(prefixo + "defesa",
                    String.valueOf(pokemon.getDefesa()));

            dados.setProperty(prefixo + "nivel",
                    String.valueOf(pokemon.getNivel()));

            dados.setProperty(prefixo + "experiencia",
                    String.valueOf(pokemon.getExperiencia()));

            dados.setProperty(prefixo + "nome", pokemon.getNome());
        }

        try {
            Files.createDirectories(arquivo.getParent());

            try (var writer = Files.newBufferedWriter(
                    arquivo, StandardCharsets.UTF_8)) {

                dados.store(writer, "Salvamento Pokemon PCD");
            }

            return true;

        } catch (IOException e) {
            System.out.println("Erro ao salvar jogo: " + e.getMessage());
            return false;
        }
    }

    public Treinador carregar() {

        if (!existeSalvamento()) {
            System.out.println("Nenhum jogo salvo encontrado!");
            return null;
        }

        Properties dados = new Properties();

        try (var reader = Files.newBufferedReader(
                arquivo, StandardCharsets.UTF_8)) {

            dados.load(reader);

            String nome = new String(
                    Base64.getDecoder().decode(
                        dados.getProperty("nome")
                    ),
                    StandardCharsets.UTF_8
            );

            int pokebolas = Integer.parseInt(
                    dados.getProperty("pokebolas"));

            int pontuacao = Integer.parseInt(
                    dados.getProperty("pontuacao"));

            batalhaSalva = Integer.parseInt(
                    dados.getProperty("batalha"));

            int quantidade = Integer.parseInt(
                    dados.getProperty("quantidadePokemon"));

            if (batalhaSalva < 1 || batalhaSalva > 5
                    || quantidade < 1 || quantidade > 6) {
                throw new IllegalArgumentException(
                        "Dados de salvamento inválidos.");
            }

            Treinador treinador = new Treinador(nome);

            treinador.restaurarRecursos(pokebolas, pontuacao);

            for (int i = 0; i < quantidade; i++) {

                String prefixo = "pokemon." + i + ".";

                String especie = dados.getProperty(
                        prefixo + "especie");

                Pokemon pokemon = criarPokemon(especie, dados, prefixo);

                if (pokemon == null) {
                    throw new IllegalArgumentException(
                            "Espécie desconhecida: " + especie);
                }

                pokemon.restaurarEstado(
                        Integer.parseInt(dados.getProperty(prefixo + "vida")),
                        Integer.parseInt(dados.getProperty(prefixo + "vidaMaxima")),
                        Integer.parseInt(dados.getProperty(prefixo + "ataque")),
                        Integer.parseInt(dados.getProperty(prefixo + "defesa")),
                        Integer.parseInt(dados.getProperty(prefixo + "nivel")),
                        Integer.parseInt(dados.getProperty(prefixo + "experiencia"))
                );

                treinador.adicionarPokemon(pokemon);
            }

            System.out.println("Jogo carregado com sucesso!");
            return treinador;

        } catch (IOException | IllegalArgumentException e) {

            System.out.println(
                    "Erro ao carregar salvamento: " + e.getMessage());

            return null;
        }
    }

    private Pokemon criarPokemon(
            String especie,
            Properties dados,
            String prefixo) {

        switch (especie) {

            case "Pikachu":
                return new Pikachu();

            case "Charmander":
                return new Charmander();

            case "Squirtle":
                return new Squirtle();

            case "PokemonSelvagem":

                return new PokemonSelvagem(
                        dados.getProperty(prefixo + "nome", "Desconhecido"),
                        Integer.parseInt(dados.getProperty(prefixo + "vidaMaxima")),
                        Integer.parseInt(dados.getProperty(prefixo + "ataque")),
                        Integer.parseInt(dados.getProperty(prefixo + "defesa"))
                );

            default:
                return null;
        }
    }

    public void excluirSalvamento() {

        try {
            Files.deleteIfExists(arquivo);

        } catch (IOException e) {
            System.out.println(
                    "Erro ao excluir salvamento: " + e.getMessage());
        }
    }
}
