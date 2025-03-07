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

    public IngredienteQuantidade buscarIngredientePorId(int id) {
        for (IngredienteQuantidade iq : ingredientes) {
            if (iq.getIngrediente().getId() == id) {
                return iq;
            }
        }
        return null;
    }

    // Consome ingredientes ao adicionar um prato ao pedido
    public boolean consumirIngredientes(Prato prato) {
        // verificar se todos os ingredientes do prato existem e estão em quantidade suficiente no estoque para fazer o prato
        for (IngredienteQuantidade iq : prato.getIngredientes()) {
            IngredienteQuantidade ingredienteNoEstoque = buscarIngredientePorId(iq.getIngrediente().getId());

            if (ingredienteNoEstoque == null || ingredienteNoEstoque.getQuantidade() < iq.getQuantidade()) {
                System.out.println("Estoque insuficiente para preparar: " + prato.getNome());
                return false;
            }
        }

        // Se todos os ingredientes estiverem disponíveis, consumir do estoque
        for (IngredienteQuantidade iq : prato.getIngredientes()) {
            IngredienteQuantidade ingredienteNoEstoque = buscarIngredientePorId(iq.getIngrediente().getId());
            ingredienteNoEstoque.reduzirQuantidade(iq.getQuantidade());
        }

        System.out.println("Ingredientes consumidos para o prato: " + prato.getNome());
        return true;
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

    // Reabastece o estoque e verifica se há pratos que podem ficar disponíveis novamente
    public void reabastecerEstoque(int id, double quantidade, Menu menu) {
        IngredienteQuantidade ingredienteParaReabastecer = buscarIngredientePorId(id);

        if (ingredienteParaReabastecer != null) {
            ingredienteParaReabastecer.aumentarQuantidade(quantidade);
            System.out.printf("%s reabastecido(a) no estoque. Quantidade atualizada: %.2f %s%n",
                    ingredienteParaReabastecer.getIngrediente().getNome(),
                    ingredienteParaReabastecer.getQuantidade(),
                    ingredienteParaReabastecer.getIngrediente().getUnidade());

            // Atualiza a disponibilidade dos pratos após o reabastecimento
            menu.atualizarDisponibilidade(this);
        } else {
            System.out.println("Ingrediente não encontrado no estoque.");
        }
    }
}
