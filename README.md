# Projeto-POO

### Sistema de Gerenciamento de Restaurantes (Meu Menu)

```mermaid
classDiagram
    class Menu {
        -List~Prato~ pratos
        +adicionarPrato(prato: Prato) void
        +excluirPrato(id: int) void
        +atualizarDisponibilidade(estoque: Estoque) void
        +getPratosDisponiveis() List~Prato~
        +consultarMenu() void
        +buscarPratoPorNome(nome: String) Prato
        +buscarPratoPorId(id: int) Prato
    }

    class Prato {
        -int id
        -String nome
        -double preco
        -String descricao
        -List~IngredienteQuantidade~ ingredientes
        -StatusPrato status
        +atualizarStatus(estoque: Estoque) void
        +isDisponivel() boolean
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
        +aumentarQuantidade(quantidade: double) void
        +reduzirQuantidade(quantidade: double) void
    }

    class ListaClientes {
        -List~Cliente~ clientes
        +cadastrarCliente(cliente: Cliente) void
        +excluirCliente(id: int) void
        +consultarClientes() void
        +buscarClientePorId(id: int) Cliente
    }

    class Cliente {
        -int id
        -String nome
        -String telefone
        -String email
    }

    class ListaPedidos {
        -List~Pedido~ pedidos
        +realizarPedido(pedido: Pedido) void
        +buscarPedidoPorId(id: int) Pedido
        +consultarPedidos() void
    }

    class Pedido {
        -int id
        -Cliente cliente
        -List~Prato~ pratos
        -double total
        -StatusPedido status
        -Estoque estoque
        +adicionarPrato(menu: Menu, nomePrato: String) boolean
        +alterarStatus(novoStatus: StatusPedido) void
        +consultar() void
    }

    class Estoque {
        -List~IngredienteQuantidade~ ingredientes
        +consumirIngredientes(prato: Prato) boolean
        +adicionarIngrediente(ingrediente: IngredienteQuantidade) void
        +reabastecerEstoque(id: int, quantidade: double, menu: Menu) void
        +buscarIngredientePorId(id: int) IngredienteQuantidade
        +consultarEstoque() void
    }

    class StatusPedido {
        <<enum>>
        PENDENTE
        EM_PREPARACAO
        PRONTO
        ENTREGUE
        CANCELADO
    }

    class StatusPrato {
        <<enum>>
        DISPONIVEL
        INDISPONIVEL
    }

    Menu "1" -- "N" Prato : gerencia
    ListaClientes "1" -- "N" Cliente : gerencia
    ListaPedidos "1" -- "N" Pedido : gerencia
    Prato "N" -- "N" IngredienteQuantidade : possui
    Prato "1" -- "1" StatusPrato : possui
    IngredienteQuantidade "N" -- "1" Ingrediente : referencia
    Pedido "N" -- "M" Prato : contém
    Pedido "1" -- "1" StatusPedido : possui
    Cliente "1" -- "N" Pedido : realiza
    Estoque "1" -- "N" IngredienteQuantidade : gerencia
    Pedido --> Estoque : usa
```
