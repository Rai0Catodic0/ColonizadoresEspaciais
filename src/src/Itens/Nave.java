package Itens;

import Tabuleiro.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Nave extends Item implements INave{

    public Nave(int i, int j, String repre, Tabuleiro tab){
        super(i,j,repre,tab);
    }

}
