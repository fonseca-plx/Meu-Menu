package entities;

import java.util.List;

public class Prato {

    private int id;
    private String nome;
    private double preco;
    private String descricao;

    private List<IngredienteQuantidade> ingredientes; // lista de ingredientes com suas respectivas quantidades

    // o usuário pode optar por criar um prato sem passar a descrição no momento em que criar o prato
    public Prato(int id, String nome, double preco, List<IngredienteQuantidade> ingredientes) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.ingredientes = ingredientes;
    }

    public Prato(int id, String nome, double preco, String descricao, List<IngredienteQuantidade> ingredientes) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.ingredientes = ingredientes;
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
    // TODO criar uma sobrecarga que permita criar um prato passando ingredientes que já existem no Estoque (lógica semelhante a do pedido)
}
