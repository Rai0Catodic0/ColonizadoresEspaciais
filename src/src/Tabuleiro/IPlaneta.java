package Tabuleiro;

import Itens.Item;

import java.util.List;


public interface IPlaneta {

    void Remover(Item item);
    boolean Inserir(Item item);
    void GerarRecursos();
    List<Item> getItens();
    //void addObserver(Jogador j);
}
