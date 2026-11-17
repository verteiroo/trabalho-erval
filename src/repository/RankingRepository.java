
package repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankingRepository {

    private final Path arquivo =
            Path.of("dados", "ranking.txt");

    public void salvarPontuacao(String nome, int pontos) {

        try {

            Files.createDirectories(arquivo.getParent());

            String nomeSeguro = nome.replace(";", ",");

            String registro = nomeSeguro + ";" + pontos;

            Files.write(
                arquivo,
                List.of(registro),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );

            System.out.println("Pontuação salva no ranking!");

        } catch (IOException e) {

            System.out.println(
                "Erro ao salvar ranking: " + e.getMessage()
            );
        }
    }

    public void mostrarRanking() {

        if (!Files.exists(arquivo)) {
            System.out.println("Ainda não existe um ranking!");
            return;
        }

        List<String> linhas;

        try {
            linhas = Files.readAllLines(
                arquivo,
                StandardCharsets.UTF_8
            );

        } catch (IOException e) {
            System.out.println("Erro ao carregar ranking.");
            return;
        }

        List<String[]> jogadores = new ArrayList<>();

        for (String linha : linhas) {

            String[] dados = linha.split(";");

            if (dados.length != 2) {
                continue;
            }

            try {

                Integer.parseInt(dados[1]);

                jogadores.add(dados);

            } catch (NumberFormatException e) {
                // Ignora registros inválidos.
            }
        }

        jogadores.sort(
            Comparator.comparingInt(
                (String[] jogador) ->
                    Integer.parseInt(jogador[1])
            ).reversed()
        );

        System.out.println();
        System.out.println("========= RANKING =========");

        if (jogadores.isEmpty()) {
            System.out.println("Nenhuma pontuação registrada.");
            return;
        }

        int limite = Math.min(10, jogadores.size());

        for (int i = 0; i < limite; i++) {

            String[] jogador = jogadores.get(i);

            System.out.println(
                (i + 1) + " - "
                + jogador[0] + " ........ "
                + jogador[1] + " pontos"
            );
        }

        System.out.println("===========================");
    }
}
