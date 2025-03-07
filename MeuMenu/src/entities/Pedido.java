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
    private Estoque estoque;

    public Pedido(int id, Cliente cliente, Estoque estoque) {
        this.id = id;
        this.cliente = cliente;
        this.pratos = new ArrayList<>();
        this.total = 0.0;
        this.status = StatusPedido.PENDENTE;
        this.estoque = estoque;
    }

    public boolean adicionarPrato(Menu menu, String nomePrato) {
        Prato prato = menu.buscarPratoPorNome(nomePrato);
        if (prato != null) {
            pratos.add(prato);
            total += prato.getPreco();
            estoque.consumirIngredientes(prato);
            System.out.println("Prato adicionado ao pedido: " + prato.getNome());
            return true;
        }
        System.out.println("Prato indisponível ou não encontrado.");
        return false;
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

    // TODO implementar mesma lógica dos pratos para os clientes (buscar o cliente que realizou o pedido na lista de clientes)
    // TODO consumirIngrediente() vai ser um metodo da classe Pedido
}