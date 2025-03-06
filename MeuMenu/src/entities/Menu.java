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

    // remove um prato do Menu a partir do seu id
    public void excluirPrato(int id) {
        Prato pratoParaRemover = null;
        for (Prato p : pratos) {
            if (p.getId() == id) {
                pratoParaRemover = p;
                break;
            }
        }
        if (pratoParaRemover != null) {
            pratos.remove(pratoParaRemover);
            System.out.println("Prato removido do menu: " + pratoParaRemover.getNome());
        } else {
            System.out.println("Prato não encontrado!");
        }
    }

    public void consultarMenu() {
        if (pratos.isEmpty()) {
            System.out.println("O menu está vazio.");
        } else {
            System.out.println("Menu do Restaurante:");
            for (Prato p : pratos) {
                System.out.println("- " + p.getNome() + " | Preço: R$ " + p.getPreco());
            }
        }
    }

    // Buscar um prato pelo nome
    public Prato buscarPratoPorNome(String nome) {
        for (Prato prato : pratos) {
            if (prato.getNome().equalsIgnoreCase(nome)) {
                return prato;
            }
        }
        return null;
    }

    // Buscar um prato pelo ID
    public Prato buscarPratoPorId(int id) {
        for (Prato prato : pratos) {
            if (prato.getId() == id) {
                return prato;
            }
        }
        return null;
    }
}
