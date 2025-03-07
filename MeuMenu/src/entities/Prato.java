package entities;

import entities.enums.StatusPrato;

import java.util.List;

public class Prato {

    private int id;
    private String nome;
    private double preco;
    private String descricao;
    private List<IngredienteQuantidade> ingredientes; // lista de ingredientes com suas respectivas quantidades
    private StatusPrato status;

    // o usuário pode optar por criar um prato sem passar a descrição no momento em que criar o prato
    public Prato(int id, String nome, double preco, List<IngredienteQuantidade> ingredientes) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.ingredientes = ingredientes;
        this.status = StatusPrato.DISPONIVEL;
    }

    // o usuário pode criar um prato passando ingredientes novos, que ainda não existem no estoque
    public Prato(int id, String nome, double preco, String descricao, List<IngredienteQuantidade> ingredientes) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.ingredientes = ingredientes;
        this.status = StatusPrato.DISPONIVEL;
    }

    // o usuário pode criar um prato com ingredientes que já existem no estoque
    public Prato(int id, String nome, double preco, String descricao, Estoque estoque, List<Integer> idsIngredientes, double quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.status = StatusPrato.DISPONIVEL;
        for (Integer idIngrediente : idsIngredientes) {
            IngredienteQuantidade ingrediente = estoque.buscarIngredientePorId(idIngrediente);
            if (ingrediente != null) {
                ingrediente.setQuantidade(quantidade); // quantidade necessária para fazer o prato
                ingredientes.add(ingrediente);
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    // preço pode ser atualizado
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    // descrição pode ser atualizada
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<IngredienteQuantidade> getIngredientes() {
        return ingredientes;
    }

    public StatusPrato getStatus() {
        return status;
    }

    // se um dos ingredientes que compoe o prato estiverem em falta no estoque o status será alterado para INDISPONIVEL
    public void atualizarStatus(Estoque estoque) {
        for (IngredienteQuantidade iq : ingredientes) {
            IngredienteQuantidade ingredienteNoEstoque = estoque.buscarIngredientePorId(iq.getIngrediente().getId());

            if (ingredienteNoEstoque == null || ingredienteNoEstoque.getQuantidade() < iq.getQuantidade()) {
                this.status = StatusPrato.INDISPONIVEL;
                return;
            }
        }
        this.status = StatusPrato.DISPONIVEL;
    }

    public boolean isDisponivel() {
        return this.status == StatusPrato.DISPONIVEL;
    }

    // exibir os ingredientes e suas quantidades
    public void obterIngredientes() {
        System.out.println("Ingredientes do prato " + nome + ":");
        for (IngredienteQuantidade iq : ingredientes) {
            System.out.printf("- %s : %.2f %s%n", iq.getIngrediente().getNome(), iq.getQuantidade(), iq.getIngrediente().getUnidade());
        }
    }

    // calcular o custo do prato com base nos ingredientes
    public double calcularCusto() {
        double custoTotal = 0.0;
        for (IngredienteQuantidade iq : ingredientes) {
            custoTotal += iq.getQuantidade() * iq.getIngrediente().getCustoPorUnidade();
        }
        return custoTotal;
    }

    // exibir os detalhes do prato
    public void consultar() {
        System.out.println("Prato: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("Preço: R$ " + preco);
        System.out.println("Custo de produção: R$ " + calcularCusto());
        obterIngredientes();
    }

    // TODO criar um método que permita atualizar as quantidades de um determinado ingrediente em um prato e um método que permita adicionar um novo ingrediente a lista
}
