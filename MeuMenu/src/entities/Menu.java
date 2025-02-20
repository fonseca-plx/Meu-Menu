package entities;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Prato> pratos = new ArrayList<>();

    public void cadastrar(Prato prato) {
        pratos.add(prato);
    }

    public void excluir(Prato prato) {
        pratos.remove(prato);
    }

}
