package model;

public abstract class Pokemon {

    private String nome;
    private int vida;
    private int vidaMaxima;
    private int defesa;
    private int nivel;
    private int experiencia;
    private int ataque;

    public Pokemon(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = 1;
        this.experiencia = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getNivel() {
        return nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void atacar(Pokemon alvo) {
        int dano = Math.max(1, ataque - alvo.getDefesa());

        System.out.println(nome + "Atacou!");
        System.out.println("Causou " + dano + " de dano.");

        alvo.receberDano(dano);
    }

    public void receberDano(int dano) {
        vida -= dano;

        if (vida < 0) {
            vida = 0;
        }
    }

    public void recuperarVida(int quantidade) {
        vida += quantidade;

        if (vida > vidaMaxima) {
            vida = vidaMaxima;
        }
    }

    public void ganharExperiencia(int quantidade) {

        experiencia += quantidade;

        System.out.println(nome + " ganhou " + quantidade + " XP.");

        if (experiencia >= 100) {
            subirNivel();
        }
    }

    public void subirNivel() {

        nivel++;
        experiencia = 0;

        vidaMaxima += 20;
        vida = vidaMaxima;

        ataque += 5;
        defesa += 5;

        System.out.println();
        System.out.println("⭐" + nome + " suviu para o nível " + nivel + "!");
    }

    public void mostrarStatus() {
        
        System.out.println("===== STATUS =====");
        System.out.println("Pokémon: " + nome);
        System.out.println("Nível: " + nivel);
        System.out.println("Vida: " + vida + "/" + vidaMaxima);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defesa: " + defesa);
        System.out.println("XP: " + experiencia);
    }

    public abstract void usarHabilidade(Pokemon alvo);
}