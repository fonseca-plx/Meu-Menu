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
        System.out.printf("Cliente %s adicionado a lista de clientes.%n", cliente.getNome());
    }

    public void excluirCliente(int id) {
        Cliente clienteParaRemover = null;
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                clienteParaRemover = c;
                break;
            }
        }
        if (clienteParaRemover != null) {
            clientes.remove(clienteParaRemover);
            System.out.printf("Cliente %s excluido da lista.%n", clienteParaRemover.getNome());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void consultarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("A lista de clientes está vazia.");
        } else {
            System.out.println("Lista de Clientes:");
            for (Cliente c : clientes) {
                System.out.println("- " + c.getNome() + " | Telefone: " + c.getTelefone() + " | E-mail: " + c.getEmail());
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
