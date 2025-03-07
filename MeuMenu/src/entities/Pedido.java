package entities;

import entities.enums.StatusPedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido {

    private int id;
    private Cliente cliente;
    private List<Prato> pratos;
    private double total;
    private StatusPedido status;

    // Construtor que permite criar um pedido informando uma lista de IDs de pratos já adicionados ao Menu
    public Pedido(int id, Cliente cliente, Menu menu, Estoque estoque, List<Integer> idsPratos) {
        this.id = id;
        this.cliente = cliente;
        this.pratos = new ArrayList<>();
        this.status = StatusPedido.PENDENTE;
        for (Integer idPrato : idsPratos) {
            Prato prato = menu.buscarPratoPorId(idPrato);
            if (prato != null) {
                pratos.add(prato);
            }
        }
        calcularTotal();
        consumirIngredientes(estoque);
    }

    // Construtor que permite que um pedido seja realizado por um cliente não cadastrado
    public Pedido(int id, Menu menu, List<Integer> idsPratos) {
        this.id = id;
        this.pratos = new ArrayList<>();
        this.status = StatusPedido.PENDENTE;
        for (Integer idPrato : idsPratos) {
            Prato prato = menu.buscarPratoPorId(idPrato);
            if (prato != null) {
                pratos.add(prato);
            }
        }
        calcularTotal();
    }

    // alterar status
    public void alterarStatus(StatusPedido novoStatus) {
        this.status = novoStatus;
        System.out.println("Status do pedido atualizado para: " + status);
    }

    // exibir os detalhes do pedido
    public void consultar() {
        System.out.println("ID do Pedido: " + id);
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Total: R$ " + total);
        System.out.println("Status: " + status);
        System.out.println("Detalhes do pedido:");
        for (Prato prato : pratos) {
            System.out.println("- " + prato.getNome() + " | R$ " + prato.getPreco());
        }
    }

    // calcular o total do pedido
    private void calcularTotal() {
        total = 0.0;
        for (Prato prato : pratos) {
            total += prato.getPreco();
        }
    }

    private void consumirIngredientes(Estoque estoque) {
        for (Prato prato : pratos) {
            for (IngredienteQuantidade iq : prato.getIngredientes()) {  // obtem a lista de ingredientes de cada prato que compõe o pedido
                for (IngredienteQuantidade iq2 : estoque.getIngredientes()) {
                    if (Objects.equals(iq2.getIngrediente().getNome(), iq.getIngrediente().getNome())) {
                        iq2.reduzirQuantidade(iq.getQuantidade());
                    }
                    System.out.printf("%s consumido(a) do estoque. Quantidade atualizada: %.2f %s%n", iq2.getIngrediente().getNome(), iq2.getQuantidade(), iq2.getIngrediente().getUnidade());
                }
            }
        }
    }

    // TODO implementar mesma lógica dos pratos para os clientes (buscar o cliente que realizou o pedido na lista de clientes)
    // TODO consumirIngrediente() vai ser um metodo da classe Pedido
}