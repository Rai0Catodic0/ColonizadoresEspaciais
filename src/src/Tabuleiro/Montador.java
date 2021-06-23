package Tabuleiro;

/*import java.util.Random;
import Itens.Item;
import Itens.NaveColonizadora;
import Itens.Satelite;

public class Montador {

    private String SortearPlaneta(){
        Random sortear = new Random();
        int sorteado = sortear.nextInt(3);
        System.out.println(sorteado);
        if(sorteado==0){
            return "metal";
        }
        else if(sorteado==1){
            return "combustivel";
        }
        else {
            return "municao";
        }
    }

    private Planeta[][] InstanciarPlanetas(){

        Planeta planetas[][] = new Planeta[5][5];

        for(int i = 0; i<5; i++){
            for(int j = 0; j<5;j++){
                planetas[i][j]=null;
            }
        }
        planetas[0][1] = new Planeta(0,1,0,SortearPlaneta());
        planetas[0][2] = new Planeta(0,2,1,SortearPlaneta());
        planetas[0][3] = new Planeta(0,3,2,SortearPlaneta());
        planetas[1][0] = new Planeta(1,0,3,SortearPlaneta());
        planetas[1][2] = new Planeta(1,2,4,SortearPlaneta());
        planetas[1][4] = new Planeta(1,4,5,SortearPlaneta());
        planetas[2][0] = new Planeta(2,0,6,SortearPlaneta());
        planetas[2][1] = new Planeta(2,1,7,SortearPlaneta());
        planetas[2][3] = new Planeta(2,3,8,SortearPlaneta());
        planetas[2][4] = new Planeta(2,4,9,SortearPlaneta());
        planetas[3][0] = new Planeta(3,0,10,SortearPlaneta());
        planetas[3][2] = new Planeta(3,2,11,SortearPlaneta());
        planetas[3][4] = new Planeta(3,4,12,SortearPlaneta());
        planetas[4][1] = new Planeta(4,1,13,SortearPlaneta());
        planetas[4][2] = new Planeta(4,2,14,SortearPlaneta());
        planetas[4][3] = new Planeta(4,3,15,SortearPlaneta());
        return planetas;
    }

    private int[] SortearPosicaoJogador(){
        int colunas1[] = {1,2,3};
        int colunas2[] = {0,2,4};
        int colunas3[] = {0,1,3,4};
        Random sortear = new Random();
        int sorteadoLinha = sortear.nextInt(5);
        int sorteadoColuna;
        if(sorteadoLinha==2){
            sorteadoColuna = colunas3[sortear.nextInt(4)];
        } else if(sorteadoLinha==0 || sorteadoLinha==4) {
            sorteadoColuna = colunas1[sortear.nextInt(3)];
        } else {
            sorteadoColuna = colunas2[sortear.nextInt(3)];
        }
        return new int[] {sorteadoLinha, sorteadoColuna};
    }

    public Controle MontarJogo(){
        Planeta planetas[][] = InstanciarPlanetas();


        int posicaoJogador1[] = SortearPosicaoJogador();
        int posicaoJogador2[] = {-1,-1};
        do{
            posicaoJogador2 = SortearPosicaoJogador();
        } while (posicaoJogador1[0]==posicaoJogador2[0] && posicaoJogador1[1]==posicaoJogador2[1]);



        System.out.println("ESSA É A POSIÇÃO DOS JOGADORES"+posicaoJogador1[0]+posicaoJogador1[1]+posicaoJogador2[0]+posicaoJogador2[1]);



        Jogador jogador1 = new Jogador(posicaoJogador1[0], posicaoJogador1[1]);
        Jogador jogador2 = new Jogador(posicaoJogador2[0], posicaoJogador2[1]);





        Controle controle = new Controle(jogador1, jogador2);
        return controle;
    }
}
*/