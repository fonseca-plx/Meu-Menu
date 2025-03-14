package entities;

import entities.enums.StatusPedido;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private Cliente cliente;
    private List<Prato> pratos;
    private double total;
    private StatusPedido status;
    private Estoque estoque;

    // construtor que possibilita criar um pedido sem informar o cliente
    public Pedido(int id, Estoque estoque) {
        this.id = id;
        this.pratos = new ArrayList<>();
        this.total = 0.0;
        this.status = StatusPedido.PENDENTE;
        this.estoque = estoque;
    }

    // construtor que possibilita criar um pedido informando um novo cliente
    public Pedido(int id, Cliente cliente, Estoque estoque) {
        this.id = id;
        this.cliente = cliente;
        this.pratos = new ArrayList<>();
        this.total = 0.0;
        this.status = StatusPedido.PENDENTE;
        this.estoque = estoque;
    }

    // construtor que possibilita criar um pedido por um cliente já existente
    public Pedido(int id, Estoque estoque, ListaClientes clientes, int idCliente) {
        this.id = id;
        this.pratos = new ArrayList<>();
        this.total = 0.0;
        this.status = StatusPedido.PENDENTE;
        this.estoque = estoque;
        Cliente cliente = clientes.buscarClientePorId(idCliente);
        if (cliente != null) {
            this.cliente = cliente;
        } else {
            throw new IllegalArgumentException("Cliente não encontrado. Considere cadastrá-lo enquanto faz o pedido!");
        }
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public double getTotal() {
        return total;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void adicionarPrato(Prato prato) {
        if (estoque.consumirIngredientes(prato)) {
            pratos.add(prato);
            prato.atualizarStatus(estoque);
            total += prato.getPreco();
            System.out.println("\nPrato adicionado ao pedido: " + prato.getNome());
            System.out.println();
            return;
        }
        System.out.println("Prato indisponível ou não encontrado.");
    }

    // TODO criar metodo adicionarPratos() passando uma lista de pratos ao invés de passar apenas um prato por vez

    // alterar status
    public void alterarStatus(StatusPedido novoStatus) {
        this.status = novoStatus;
        System.out.println("\nStatus do pedido atualizado para: " + status + "\n");
    }

    // exibir os detalhes do pedido
    public void consultar() {
        System.out.println("ID do Pedido: " + id);
        if (cliente != null) {
            System.out.println("Cliente: " + cliente.getNome());
        }
        System.out.println("Total: R$ " + String.format("%.2f", total));
        System.out.println("Status: " + status);
        System.out.println("Detalhes do pedido:");
        for (Prato prato : pratos) {
            System.out.println("- " + prato.getNome() + " | R$ " + String.format("%.2f", prato.getPreco()));
        }
    }
}