package Itens;

import Tabuleiro.Tabuleiro;

import java.util.Random;

public class Satelite extends Item implements ISatelite {

    public Satelite(int i, int j, String repre, Tabuleiro tab){
        super(i,j,repre,tab);
    }


    @Override
    public int lutar(){
        Random gerador = new Random();
        int[] result =  gerador.ints(3,0,6).toArray();
        int max = 0;
        for(int i = 0; i<result.length; i++){
            max = result[i];
        }
        return max;
    }

    @Override
    public Item ConstruirNave(char type) {
        Item nave;
        if(type == 'g'){
            nave = new NaveGuerra(this.i, this.j, this.representacao, this.tabuleiro);
        }
        else{
            nave = new NaveColonizadora(this.i, this.j, this.representacao, this.tabuleiro);
        }
        return  nave;
    }
}
