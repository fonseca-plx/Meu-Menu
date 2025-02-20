package entities;

// Classe auxiliar entre a classe Prato e a classe Ingrediente
public class IngredienteQuantidade {

    private Ingrediente ingrediente;
    private double quantidade; // quantidade desse ingrediente para um determinado prato

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

    // apenas a quantidade do ingrediente pode ser alterada
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}
