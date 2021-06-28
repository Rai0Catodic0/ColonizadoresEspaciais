package Interfaces;

import Itens.Item;

public interface IJogador extends ITrocarVez {
    void setItem(Item item);
    void setPontuacao(int pontuacao);
    int getPontuacao();
    void updateRecursos();
    int getQtdItens();
    void IniciarJogador();
    void setNome(String nome);
}
