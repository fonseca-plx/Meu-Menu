package entities;

public class Ingrediente {

    private int id;
    private String nome;
    private String unidade;
    private double custoPorUnidade;

    public Ingrediente(int id, String nome, String unidade, double custoPorUnidade) {
        this.id = id;
        this.nome = nome;
        this.unidade = unidade;
        this.custoPorUnidade = custoPorUnidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public double getCustoPorUnidade() {
        return custoPorUnidade;
    }

    public void setCustoPorUnidade(double custoPorUnidade) {
        this.custoPorUnidade = custoPorUnidade;
    }
}
