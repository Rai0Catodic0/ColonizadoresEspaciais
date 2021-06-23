package Itens;

import Tabuleiro.Tabuleiro;

public class NaveColonizadora extends Nave implements INaveColonizadora{
    public NaveColonizadora(int i, int j, String repre, Tabuleiro tab) {

        super(i, j, repre, tab);
    }

    @Override
    public Item ConstruirSatelite() {
        Satelite satelite = new Satelite(this.i, this.j, this.representacao, this.tabuleiro);
        return satelite;
    }

    public Item Construir(char type){
        Item satelite = new Satelite(this.i, this.j, this.representacao, this.tabuleiro);
        return satelite;
    }
}
