package PokedexAplicacoes;
import PokedexInterface.Pokemon;

public abstract class PokemonBase implements Pokemon {
    private int id;
    private String nome;
    private String tipo;
    private String fraqueza;

    public PokemonBase(int id, String nome, String tipo, String fraqueza) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.fraqueza = fraqueza;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public String getFraqueza() {
        return fraqueza;
    }
}
