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
        System.out.println("Pedido realizado com sucesso! Número do pedido: " + pedido.getId());
    }

    public void consultarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("O registro de pedidos está vazio.");
        } else {
            System.out.println("Pedidos:");
            System.out.println();
            for (Pedido pedido : pedidos) {
                System.out.println("-----------------------------------");
                pedido.consultar();
            }
        }
    }
}
