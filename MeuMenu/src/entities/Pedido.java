package entities;

import java.util.List;

public class Pedido {

    private int id;
    private Cliente cliente;
    private List<Prato> pratos;
    private double total;
    private StatusPedido status;

    public Pedido(int id, Cliente cliente, List<Prato> pratos) {
        this.id = id;
        this.cliente = cliente;
        this.pratos = pratos;
        this.total = calcularTotal();
        this.status = StatusPedido.PENDENTE; // status inicial
    }

    // calcular o total do pedido
    public double calcularTotal() {
        double soma = 0.0;
        for (Prato prato : pratos) {
            soma += prato.getPreco();
        }
        return soma;
    }

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
    }

    // TODO adicionar metodos adicionarPrato(), removerPrato() e criar enum para gerenciar status do pedido
    // Criar objeto do tipo Menu que permita adicionar pratos que já existem no cardapio ao Pedido buscando pelo id ou nome do prato e remover lista de pratos
    // Ao adicionar novo prato ao pedido ou remover prato do pedido deve-se atualizar o atributo total
}