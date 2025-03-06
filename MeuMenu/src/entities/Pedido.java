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

    // Construtor que permite criar um pedido informando uma lista de IDs de pratos já adicionados ao Menu
    public Pedido(int id, Cliente cliente, Menu menu, List<Integer> idsPratos) {
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
    }

    // Construtor que permite que um pedido seja realizado por um cliente não informado
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

    // calcular o total do pedido
    private void calcularTotal() {
        total = 0.0;
        for (Prato prato : pratos) {
            total += prato.getPreco();
        }
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
        System.out.println("Detalhes do pedido:");
        for (Prato prato : pratos) {
            System.out.println("- " + prato.getNome() + " | R$ " + prato.getPreco());
        }
    }

    // adicionar um prato ao pedido a partir do menu
    public void adicionarPrato(Menu menu, int idPrato) {
        Prato prato = menu.buscarPratoPorId(idPrato);
        if (prato != null) {
            pratos.add(prato);
            total += prato.getPreco();
            System.out.println("Prato adicionado ao pedido: " + prato.getNome());
        } else {
            System.out.println("Prato não encontrado no menu.");
        }
    }

    // remover um prato do pedido
    public void removerPrato(int idPrato) {
        Prato pratoParaRemover = null;
        for (Prato prato : pratos) {
            if (prato.getId() == idPrato) {
                pratoParaRemover = prato;
                break;
            }
        }
        if (pratoParaRemover != null) {
            pratos.remove(pratoParaRemover);
            total -= pratoParaRemover.getPreco();
            System.out.println("Prato removido do pedido: " + pratoParaRemover.getNome());
        } else {
            System.out.println("Prato não encontrado no pedido.");
        }
    }

    // TODO implementar mesma lógica dos pratos para os clientes (buscar o cliente que realizou o pedido na lista de clientes)
}