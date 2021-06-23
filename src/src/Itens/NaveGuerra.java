package Itens;

import Tabuleiro.Tabuleiro;
import java.util.Random;

public class NaveGuerra extends Nave implements INave{
    public NaveGuerra(int i, int j, String repre, Tabuleiro tab) {
        super(i, j, repre, tab);
    }
    @Override
    public int lutar(){
        Random gerador = new Random();
        int[] result = gerador.ints(2,0,6).toArray();
        int max = 0;
        for( int i = 0; i<result.length; i ++){
            if( max < result[i]){
                max = result[i];
            }
        }
        return max;
    }
}
