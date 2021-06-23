package Tabuleiro;

import Itens.Item;

public interface ITabuleiro {
    String Apresentar();
    boolean Inserir(Item item);
    void Remover(Item item);
    void Movimentar();
    void Lutar(); }
