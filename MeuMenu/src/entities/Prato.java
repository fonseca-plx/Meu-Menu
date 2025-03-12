package entities;

import entities.enums.StatusPrato;

import java.util.ArrayList;
import java.util.List;

public class Prato {

    private int id;
    private String nome;
    private double preco;
    private String descricao;
    private List<IngredienteQuantidade> ingredientes; // lista de ingredientes com suas respectivas quantidades
    private StatusPrato status;

    // o usuário cria um prato com ingredientes que já existem no estoque
    public Prato(int id, String nome, double preco, String descricao, Estoque estoque, List<Integer> idsIngredientes, List<Double> quantidades) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.status = StatusPrato.DISPONIVEL;
        this.ingredientes = new ArrayList<>();
        for (int i = 0; i < idsIngredientes.size(); i++) {
            Integer idIngrediente = idsIngredientes.get(i);
            IngredienteQuantidade ingredienteNoEstoque = estoque.buscarIngredientePorId(idIngrediente);
            if (ingredienteNoEstoque != null) {
                Double quantidade = quantidades.get(i);
                IngredienteQuantidade ingredienteParaPrato = new IngredienteQuantidade(ingredienteNoEstoque.getIngrediente(), quantidade); // novo objeto IngredienteQuantidade para não alterar a quantidade do ingrediente no estoque
                ingredientes.add(ingredienteParaPrato);
            } else {
                throw new IllegalArgumentException(String.format("ID %d não encontrado. Consulte o Estoque novamente!", idIngrediente));
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
        System.out.println("ID: " + id);
        System.out.println("Prato: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("Preço: R$ " + String.format("%.2f", preco));
        System.out.println("Custo de produção: R$ " + String.format("%.2f", calcularCusto()));
        obterIngredientes();
    }

    // TODO criar um método que permita atualizar as quantidades de um determinado ingrediente em um prato e um método que permita adicionar um novo ingrediente a lista
}
