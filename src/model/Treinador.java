package model;

import java.util.ArrayList;

public class Treinador {
    private String nome;
    private ArrayList<Pokemon> equipe;

    public Treinador(String nome) {
        this.nome = nome;
        this.equipe = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public boolean adicionarPokemon(Pokemon pokemon) {
        if (pokemon == null) {
            return false;
        }

        if (equipe.size() >= 6) {
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
        System.out.println("========== SUA EQUIPE ===========");

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

        System.out.println("==============================");
    }
}
