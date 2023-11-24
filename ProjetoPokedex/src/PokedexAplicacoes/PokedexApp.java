package PokedexAplicacoes;
import PokedexInterface.Pokemon;
import PokemonExceptions.NomeNaoEncontradoException;
import java.util.Scanner;

// Aplicação principal
public class PokedexApp {
    public static void main(String[] args) {
        PokedexManager pokedexManager = new PokedexManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("#####################################################");
        System.out.println(" Seja bem vindo de volta treinador! O que deseja fazer?");

        while (true) {
            exibirMenu();
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    // Pesquisar Pokémon por nome
                    System.out.println("Digite o nome do Pokémon a ser pesquisado:");
                    String nomeProcurado = scanner.nextLine();
                    Pokemon pokemonEncontrado = null;
                    try {
                        pokemonEncontrado = pokedexManager.buscarPokemonPorNome(nomeProcurado);
                        System.out.println("ID: " + pokemonEncontrado.getId());
                        System.out.println("Nome: " + pokemonEncontrado.getNome());
                        System.out.println("Tipo: " + pokemonEncontrado.getTipo());
                        System.out.println("Fraqueza: " + pokemonEncontrado.getFraqueza());
                    } catch (NomeNaoEncontradoException e) {
                        System.err.println(e.getMessage());
                    }

                    break;

                case "2":
                    // Adicionar Pokémon
                    System.out.println("Digite o ID do Pokémon:");
                    int idNovoPokemon = Integer.parseInt(scanner.nextLine());

                    System.out.println("Digite o nome do Pokémon:");
                    String nomeNovoPokemon = scanner.nextLine();

                    System.out.println("Digite o tipo do Pokémon:");
                    String tipoNovoPokemon = scanner.nextLine();

                    System.out.println("Digite a fraqueza do Pokémon:");
                    String fraquezaNovoPokemon = scanner.nextLine();

                    Pokemon novoPokemon = new PokemonBase(idNovoPokemon, nomeNovoPokemon, tipoNovoPokemon, fraquezaNovoPokemon) {};
                    pokedexManager.adicionarPokemon(novoPokemon);
                    System.out.println("Pokémon adicionado com sucesso.");
                    break;

                case "3":
                    // Remover Pokémon
                    System.out.println("Digite o nome do Pokémon a ser removido:");
                    String nomeRemover = scanner.nextLine();
                    pokedexManager.removerPokemonPorNome(nomeRemover);
                    break;

                case "4":
                    // Exibir todos os Pokémon
                    pokedexManager.exibirTodosPokemons();
                    break;

                case "5":
                    // Sair
                    System.out.println("Saindo do programa. Até logo!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("    ");
        System.out.println("1. Pesquisar Pokémon");
        System.out.println("2. Adicionar Pokémon");
        System.out.println("3. Remover Pokémon");
        System.out.println("4. Exibir todos os Pokémon");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }
}
