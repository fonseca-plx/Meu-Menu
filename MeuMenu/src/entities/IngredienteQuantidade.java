package entities;

// Classe auxiliar entre as classes Prato, Estoque e Ingrediente
public class IngredienteQuantidade {

    private Ingrediente ingrediente;
    private double quantidade; // quantidade desse ingrediente para um determinado prato ou para o estoque

    public IngredienteQuantidade(Ingrediente ingrediente, double quantidade) {
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    // incrementar a quantidade de um ingrediente no estoque ou em um prato
    public void aumentarQuantidade(double quantidade) {
        this.quantidade += quantidade;
    }

    // reduzir quantidade de um ingrediente no estoque ao realizar um pedido ou reduzir quantidade do ingrediente em um prato
    public void reduzirQuantidade(double quantidade) {
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
        } else {
            System.out.println("Erro: Quantidade insuficiente no estoque.");
        }
    }
}
