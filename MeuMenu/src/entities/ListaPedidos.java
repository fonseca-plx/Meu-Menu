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

    // TODO add metodos realizarPedido() e consultarPedidos()
}
