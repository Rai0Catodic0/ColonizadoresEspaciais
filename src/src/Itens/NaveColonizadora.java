package Itens;

import Interfaces.INaveColonizadora;
import Tabuleiro.Tabuleiro;

import java.util.Random;

public class NaveColonizadora extends Nave implements INaveColonizadora {
    public NaveColonizadora(int i, int j, String repre, Tabuleiro tab) {


        super(i, j, repre, tab);
    }

    @Override
    public Item ConstruirSatelite() {
        Satelite satelite = new Satelite(this.i, this.j, this.representacao, this.tabuleiro);
        return satelite;
    }


    public int lutar(){
        Random gerador = new Random();
        int max = gerador.nextInt(6);
        return max;
    }
}
