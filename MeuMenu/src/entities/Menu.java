package entities;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Prato> pratos;

    public Menu() {
        this.pratos = new ArrayList<>();
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public void adicionarPrato(Prato prato) {
        pratos.add(prato);
        System.out.println("Prato adicionado ao menu: " + prato.getNome());
    }

    public void excluirPrato(int id) {
        Prato pratoParaRemover = null;
        for (Prato prato : pratos) {
            if (prato.getId() == id) {
                pratoParaRemover = prato;
                break;
            }
        }
        if (pratoParaRemover != null) {
            pratos.remove(pratoParaRemover);
            System.out.printf("O prato %s foi removido do Menu.%n", pratoParaRemover.getNome());
        } else {
            System.out.println("Prato não encontrado.");
        }
    }

    public void atualizarDisponibilidade(Estoque estoque) {
        for (Prato prato : pratos) {
            prato.atualizarStatus(estoque);
        }
    }

    // lista apenas os pratos disponíveis
    public List<Prato> getPratosDisponiveis() {
        List<Prato> pratosDisponiveis = new ArrayList<>();
        for (Prato prato : pratos) {
            if (prato.isDisponivel()) {
                pratosDisponiveis.add(prato);
            }
        }
        return pratosDisponiveis;
    }

    public void consultarMenu() {
        if (pratos.isEmpty()) {
            System.out.println("O menu está vazio.");
        } else {
            System.out.println("Menu do Restaurante:");
            for (Prato prato : getPratosDisponiveis()) {
                System.out.println("- " + prato.getNome() + " | R$ " + prato.getPreco());
            }
        }
    }

    // Buscar um prato pelo nome
    public Prato buscarPratoPorNome(String nome) {
        for (Prato prato : pratos) {
            if (prato.getNome().equalsIgnoreCase(nome) && prato.isDisponivel()) {
                return prato;
            }
        }
        return null;
    }

    // Buscar um prato pelo ID
    public Prato buscarPratoPorId(int id) {
        for (Prato prato : pratos) {
            if (prato.getId() == id && prato.isDisponivel()) {
                return prato;
            }
        }
        return null;
    }
}
