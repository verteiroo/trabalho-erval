
package model;

import java.util.ArrayList;

public class Treinador {

    private String nome;
    private ArrayList<Pokemon> equipe;
    private int pokebolas;

    private int pontuacao;

    public Treinador(String nome) {
        this.nome = nome;
        this.equipe = new ArrayList<>();
        this.pokebolas = 5;
        this.pontuacao = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getPokebolas() {
        return pokebolas;
    }

    public boolean usarPokebola() {
        if (pokebolas <= 0) {
            return false;
        }

        pokebolas--;
        return true;
    }

    public boolean temEspacoNaEquipe() {
        return equipe.size() < 6;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void adicionarPontos(int pontos) {
        if (pontos > 0) {
            pontuacao += pontos;
        }
    }

    public boolean adicionarPokemon(Pokemon pokemon) {
        if (pokemon == null) {
            return false;
        }

        if (!temEspacoNaEquipe()) {
            System.out.println("Sua equipe está cheia!");
            return false;
        }

        equipe.add(pokemon);
        return true;
    }

    public Pokemon getPokemonPrincipal() {
        if (equipe.isEmpty()) {
            return null;
        }

        return equipe.get(0);
    }

    public void mostrarEquipe() {
        System.out.println();
        System.out.println("======= SUA EQUIPE =======");

        if (equipe.isEmpty()) {
            System.out.println("Sua equipe está vazia!");
            return;
        }

        for (int i = 0; i < equipe.size(); i++) {
            Pokemon pokemon = equipe.get(i);

            System.out.println(
                (i + 1) + " - "
                + pokemon.getNome()
                + " | Nível: " + pokemon.getNivel()
                + " | Vida: " + pokemon.getVida()
                + "/" + pokemon.getVidaMaxima()
            );
        }

        System.out.println("==========================");
    }
}
