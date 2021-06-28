package Itens;

import Interfaces.INave;
import Tabuleiro.Tabuleiro;

public class Nave extends Item implements INave {

    public Nave(int i, int j, String repre, Tabuleiro tab){
        super(i,j,repre,tab);

    }

}
