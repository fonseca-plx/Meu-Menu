package entities;

import java.util.ArrayList;
import java.util.List;

public class ListaPedidos {

    private List<Pedido> pedidos;

    public ListaPedidos() {
        this.pedidos = new ArrayList<>();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void realizarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void adicionarPratos(int idPedido, Prato prato, int quantidade) {
        Pedido pedido = buscarPedidoPorId(idPedido);
        if (pedido != null) {
            for (int i = 0; i < quantidade; i++) {
                pedido.adicionarPrato(prato);
            }
        } else {
            System.out.println("\nPedido não encontrado!");
        }
    }

    public void consultarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("O registro de pedidos está vazio.");
        } else {
            System.out.println("Pedidos:\n");
            for (Pedido pedido : pedidos) {
                System.out.println("-----------------------------------");
                pedido.consultar();
            }
            System.out.println("-----------------------------------");
        }
    }

    public Pedido buscarPedidoPorId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }
}
