# Projeto-POO

### Sistema de Gerenciamento de Restaurantes (Meu Menu)

```mermaid

classDiagram
    class Prato {
        +int id
        +string nome
        +double preco
        +string descricao
        +List~Tuple~<Ingrediente, float> ingredientes
        +cadastrar() void
        +consultar() Prato
        +excluir() void
        +atualizar() void
    }

    class Ingrediente {
        +int id
        +string nome
        +string unidade
    }

    class Cliente {
        +int id
        +string nome
        +string telefone
        +string email
        +cadastrar() void
        +consultar() Cliente
        +excluir() void
    }

    class Pedido {
        +int id
        +Cliente cliente
        +List~Prato~ pratos
        +double total
        +string status
        +adicionarPrato() void
        +removerPrato() void
        +calcularTotal() double
        +alterarStatus() void
    }

    class Estoque {
        +List~ItemEstoque~ itens
        +adicionarItem() void
        +consultarItem() ItemEstoque
        +consumirItem() void
    }

    class ItemEstoque {
        +int id
        +string nome
        +double quantidade
        +string unidade
        +adicionarQuantidade() void
        +reduzirQuantidade() void
    }

    Prato "N" -- "M" Ingrediente : possui
    Pedido "N" -- "M" Prato : contém
    Cliente "1" -- "N" Pedido : realiza
    Estoque "1" -- "N" ItemEstoque : gerencia
    Ingrediente "N" -- "1" ItemEstoque : corresponde

```
