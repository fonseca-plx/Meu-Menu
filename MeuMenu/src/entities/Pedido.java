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
        }
    }

    public boolean adicionarPrato(Menu menu, String nomePrato) {
        Prato prato = menu.buscarPratoPorNome(nomePrato);
        if (prato != null && estoque.consumirIngredientes(prato)) {
            pratos.add(prato);
            total += prato.getPreco();
            System.out.println("Prato adicionado ao pedido: " + prato.getNome());
            return true;
        }
        System.out.println("Prato indisponível ou não encontrado.");
        return false;
    }

    // TODO criar metodo adicionarPratos() passando uma lista de pratos ao invés de passar apenas um prato por vez

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
}