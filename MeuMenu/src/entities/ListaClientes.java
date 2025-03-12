package entities;

import java.util.ArrayList;
import java.util.List;

public class ListaClientes {

    private List<Cliente> clientes;

    public ListaClientes() {
        this.clientes = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.printf("%nCliente %s adicionado a lista de clientes.%n", cliente.getNome());
    }

    public void excluirCliente(Cliente cliente) {
        clientes.remove(cliente);
        System.out.printf("%nCliente %s excluido da lista.%n", cliente.getNome());
    }

    public void consultarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("A lista de clientes está vazia.");
        } else {
            System.out.println("Lista de Clientes:\n");
            for (Cliente c : clientes) {
                System.out.println("- ID: " + c.getId() + " | Nome: " + c.getNome() + " | Telefone: " + c.getTelefone() + " | E-mail: " + c.getEmail());
            }
        }
    }

    public Cliente buscarClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }
}
