package Tabuleiro;

import Itens.Item;
import Recursos.Recursos;

import java.util.List;


public interface IPlaneta {
    void Remover(Item item);
    void Inserir(Item item);
    void GerarRecursos();
    List<Item> getItens();
    //void addObserver(Jogador j);
}
