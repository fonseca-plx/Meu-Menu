package entities;

import java.util.ArrayList;
import java.util.List;

public class Estoque {

    private List<IngredienteQuantidade> ingredientes; // ingredientes e suas quantidades

    public Estoque() {
        this.ingredientes = new ArrayList<>();
    }

    public List<IngredienteQuantidade> getIngredientes() {
        return ingredientes;
    }

    // add um novo ingrediente ao estoque
    public void adicionarIngrediente(IngredienteQuantidade ingrediente) {
        ingredientes.add(ingrediente);
        System.out.println("Ingrediente adicionado ao estoque: " + ingrediente.getIngrediente().getNome());
    }

    // consultar ingredientes no estoque
    public void consultarEstoque() {
        if (ingredientes.isEmpty()) {
            System.out.println("O estoque está vazio.");
        } else {
            System.out.println("Ingredientes no estoque:");
            for (IngredienteQuantidade iq : ingredientes) {
                System.out.printf("- %s | Quantidade: %.2f %s %n", iq.getIngrediente().getNome(), iq.getQuantidade(), iq.getIngrediente().getUnidade());
            }
        }
    }

    // aumentar a quantidade de um determinado ingrediente
    public void reabastecerEstoque(int id, double quantidade) {
        IngredienteQuantidade ingredienteParaReabastecer = null;
        for (IngredienteQuantidade iq : ingredientes) {
            if (iq.getIngrediente().getId() == id) {
                ingredienteParaReabastecer = iq;
                break;
            }
        }
        if (ingredienteParaReabastecer != null) {
            ingredienteParaReabastecer.aumentarQuantidade(quantidade);
            System.out.printf("%s reabastecido(a) no estoque. Quantidade atualizada: %.2f %s%n", ingredienteParaReabastecer.getIngrediente().getNome(), ingredienteParaReabastecer.getQuantidade(), ingredienteParaReabastecer.getIngrediente().getUnidade());
        } else {
            System.out.println("Ingrediente não encontrado no estoque.");
        }
    }
}
