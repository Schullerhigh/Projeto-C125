package PokedexAplicacoes;
import PokedexInterface.Pokemon;
import PokemonExceptions.NomeNaoEncontradoException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// Gerenciador da Pokedex
public class PokedexManager {
    private Map<Integer, Pokemon> pokedex;
    private static final String ARQUIVO_POKEDEX = "pokedex.txt";

    public PokedexManager() {
        pokedex = new HashMap<>();
        carregarPokedex();
    }

    public Pokemon buscarPokemonPorNome(String nome) throws NomeNaoEncontradoException {
        for (Pokemon pokemon : pokedex.values()) {
            if (pokemon.getNome().equalsIgnoreCase(nome)) {
                return pokemon;
            }
        }
        throw new NomeNaoEncontradoException("Pokemon não encontrado");
    }

    public void adicionarPokemon(Pokemon pokemon) {
        pokedex.put(pokemon.getId(), pokemon);
        salvarPokedex();
    }

    public void removerPokemonPorNome(String nome) {
        try {
            buscarPokemonPorNome(nome);
            pokedex.entrySet().removeIf(entry -> entry.getValue().getNome().equalsIgnoreCase(nome));
            System.out.println("Pokemom removido da pokedex com sucesso!");
            salvarPokedex();
        } catch (NomeNaoEncontradoException e) {
            System.err.println(e.getMessage());
        }
    }

    public void exibirTodosPokemons() {
        for (Pokemon pokemon : pokedex.values()) {
            System.out.println("ID: " + pokemon.getId());
            System.out.println("Nome: " + pokemon.getNome());
            System.out.println("Tipo: " + pokemon.getTipo());
            System.out.println("Fraqueza: " + pokemon.getFraqueza());
            System.out.println("---------------");
        }
    }

    private void carregarPokedex() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_POKEDEX))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String tipo = dados[2];
                String fraqueza = dados[3];

                Pokemon pokemon = new PokemonBase(id, nome, tipo, fraqueza) {};
                pokedex.put(id, pokemon);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar a Pokedex: " + e.getMessage());
        }
    }

    private void salvarPokedex() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_POKEDEX))) {
            for (Pokemon pokemon : pokedex.values()) {
                writer.write(pokemon.getId() + "," + pokemon.getNome() + "," + pokemon.getTipo() + "," + pokemon.getFraqueza());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar a Pokedex: " + e.getMessage());
        }
    }

}
