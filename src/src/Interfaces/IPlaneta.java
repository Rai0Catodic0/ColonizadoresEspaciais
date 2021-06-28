package Interfaces;

import Itens.Item;

import java.util.List;


public interface IPlaneta extends IGerarRecurso, IConstruir {
    void Remover(Item item);
    boolean Inserir(Item item);
    List<Item> getItens();
    boolean  isVizinho(int idVizinho);
}
