# Projeto-POO

### Sistema de Gerenciamento de Restaurantes (Meu Menu)

```mermaid
classDiagram
    class Menu {
        -List~Prato~ pratos
        +adicionarPrato(prato: Prato) void
        +excluirPrato(prato: Prato) void
        +consultarMenu() void
    }

    class Prato {
        -int id
        -String nome
        -double preco
        -String descricao
        -List~IngredienteQuantidade~ ingredientes
        +obterIngredientes() void
        +calcularCusto() double
        +consultar() void
    }

    class Ingrediente {
        -int id
        -String nome
        -String unidade
        -double custoPorUnidade
    }

    class IngredienteQuantidade {
        -Ingrediente ingrediente
        -double quantidade
    }

    class ListaClientes {
        -List~Cliente~ clientes
        +cadastrarCliente(cliente: Cliente) void
        +excluirCliente(cliente: Cliente) void
        +consultarListaClientes() void
    }

    class Cliente {
        -int id
        -String nome
        -String telefone
        -String email
    }

    class Pedido {
        -int id
        -Cliente cliente
        -List~Prato~ pratos
        -double total
        -String status
        +adicionarPrato() void
        +removerPrato() void
        +calcularTotal() double
        +alterarStatus() void
    }

    class Estoque {
        -List~ItemEstoque~ itens
        +adicionarItem() void
        +consultarItem() ItemEstoque
        +consumirItem() void
    }

    class ItemEstoque {
        -int id
        -String nome
        -double quantidade
        -String unidade
        +adicionarQuantidade() void
        +reduzirQuantidade() void
    }

    class StatusPedido {
        <<enum>>
        PENDENTE
        EM_PREPARACAO
        PRONTO
        ENTREGUE
    }

    Prato "N" -- "N" IngredienteQuantidade : possui
    IngredienteQuantidade "N" -- "1" Ingrediente : referencia
    Pedido "N" -- "M" Prato : contém
    Pedido "1" -- "1" StatusPedido : possui
    Cliente "1" -- "N" Pedido : realiza
    Estoque "1" -- "N" ItemEstoque : gerencia
    Ingrediente "N" -- "1" ItemEstoque : corresponde
```
