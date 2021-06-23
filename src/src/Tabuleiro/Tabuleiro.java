package Tabuleiro;

import Itens.Item;
import Itens.NaveColonizadora;
import Itens.NaveGuerra;
import Itens.Satelite;
import Recursos.Recursos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tabuleiro {

    private Planeta[][] planetas;
    private int metal, municao, combustivel;
    public Jogador azul;
    public Jogador verde;

    public Jogador getJogadorAzul(){
        return azul;
    }
    public Jogador getJogadorVerde(){
        return verde;
    }
    public Tabuleiro() {

        //Instanciar Planetas
        planetas = InstanciarPlanetas();
        this.setarvizinho(this.planetas);
           this.metal = 0;
           this.municao = 0;
           this.combustivel = 0;
        //Definir Posição dos Jogadores
        int posicaoJogador1[] = SortearPosicaoJogador();
        int posicaoJogador2[] = {-1,-1};
        do{
            posicaoJogador2 = SortearPosicaoJogador();
        } while (posicaoJogador1[0]==posicaoJogador2[0] && posicaoJogador1[1]==posicaoJogador2[1]);

        //Instanciar Jogadores em suas posições
        Jogador jogador1 = new Jogador("a");//posicaoJogador1[0], posicaoJogador1[1]
        Jogador jogador2 = new Jogador("v");//posicaoJogador2[0], posicaoJogador2[1]
        this.azul = jogador1;
        this.verde = jogador2;

        //Instanciar Itens para jogadores
        Item nave1 = new NaveColonizadora(posicaoJogador1[0], posicaoJogador1[1], jogador1.repre,this);
        Item satelite1 = new Satelite(posicaoJogador1[0], posicaoJogador1[1], jogador1.repre,this);
        Item nave2 = new NaveColonizadora(posicaoJogador2[0], posicaoJogador2[1], jogador2.repre,this);
        Item satelite2 = new Satelite(posicaoJogador2[0], posicaoJogador2[1], jogador2.repre,this);

        //Colocar Itens no tabuleiro
        this.Inserir(nave1);
        this.Inserir(nave2);
        this.Inserir(satelite1);
        this.Inserir(satelite2);

        //Atualizar itens dos jogadores
        jogador1.setItens(nave1, satelite1);
        jogador2.setItens(nave2, satelite2);
    }

    /**Funções auxiliares do Construtor**/

    private String SortearPlaneta(){
        Random sortear = new Random();
        List<String> recursos = new ArrayList<>();
        if(this.metal<7){
            recursos.add("metal");
        }
        if(this.combustivel<5){
            recursos.add("combustivel");
        }
        if(this.municao<4){
            recursos.add("municao");
        }
        int bound = recursos.size();
        int sorteado = sortear.nextInt(bound);
        String recursoSorteado = recursos.get(sorteado);
        if(recursoSorteado.equals("municao")){
            this.municao++;
        } else if(recursoSorteado.equals("metal")){
            this.metal++;
        } else {
            this.combustivel++;
        }
        return recursoSorteado;
    }

    private Planeta[][] InstanciarPlanetas(){

        Planeta planetas[][] = new Planeta[5][5];

        for(int i = 0; i<5; i++){
            for(int j = 0; j<5;j++){
                planetas[i][j]=null;
            }
        }

        //400
        //300
        int[] ids = gerarIds();
        planetas[0][1] = new Planeta(0,1,140,600, ids[0], SortearPlaneta());
        planetas[0][2] = new Planeta(0,2,040,900,ids[1],SortearPlaneta());
        planetas[0][3] = new Planeta(0,3,120,1200,ids[2],SortearPlaneta());
        planetas[1][0] = new Planeta(1,0,300,400,ids[3],SortearPlaneta());
        planetas[1][2] = new Planeta(1,2,295,910,ids[4],SortearPlaneta());
        planetas[1][4] = new Planeta(1,4,296,1350,ids[5],SortearPlaneta());
        planetas[2][0] = new Planeta(2,0,452,306,ids[6],SortearPlaneta());
        planetas[2][1] = new Planeta(2,1,453,650,ids[7],SortearPlaneta());
        planetas[2][3] = new Planeta(2,3,455,1206,ids[8],SortearPlaneta());
        planetas[2][4] = new Planeta(2,4,450,1555,ids[9],SortearPlaneta());
        planetas[3][0] = new Planeta(3,0,630,440,ids[10],SortearPlaneta());
        planetas[3][2] = new Planeta(3,2,531,911,ids[11],SortearPlaneta());
        planetas[3][4] = new Planeta(3,4,635,1355,ids[12],SortearPlaneta());
        planetas[4][1] = new Planeta(4,1,770,610,ids[13],SortearPlaneta());
        planetas[4][2] = new Planeta(4,2,740,920,ids[14],SortearPlaneta());
        planetas[4][3] = new Planeta(4,3,750,1210,ids[15],SortearPlaneta());
        return planetas;

    }

    private void setarvizinho(Planeta planetas[][]){
        planetas[0][1].setIdVizinhos(new int[] {planetas[1][0].id,planetas[0][2].id});
        planetas[0][2].setIdVizinhos(new int[] {planetas[0][1].id,planetas[1][2].id,planetas[0][3].id});
        planetas[0][3].setIdVizinhos(new int[] {planetas[0][2].id,planetas[1][4].id});
        planetas[1][0].setIdVizinhos(new int[] {planetas[2][0].id,planetas[0][1].id});
        planetas[1][2].setIdVizinhos(new int[] {planetas[0][2].id,planetas[3][2].id,});
        planetas[1][4].setIdVizinhos(new int[] {planetas[0][3].id,planetas[2][4].id});
        planetas[2][0].setIdVizinhos(new int[] {planetas[1][0].id,planetas[2][1].id,planetas[3][0].id});
        planetas[2][1].setIdVizinhos(new int[] {planetas[2][0].id,planetas[2][3].id});
        planetas[2][3].setIdVizinhos(new int[] {planetas[2][1].id,planetas[2][4].id});
        planetas[2][4].setIdVizinhos(new int[] {planetas[2][3].id,planetas[1][4].id,planetas[3][4].id});
        planetas[3][0].setIdVizinhos(new int[] {planetas[2][0].id,planetas[4][1].id});
        planetas[3][2].setIdVizinhos(new int[] {planetas[1][2].id,planetas[4][2].id});
        planetas[3][4].setIdVizinhos(new int[] {planetas[2][4].id,planetas[4][3].id});
        planetas[4][1].setIdVizinhos(new int[] {planetas[3][0].id,planetas[4][2].id});
        planetas[4][2].setIdVizinhos(new int[] {planetas[4][1].id,planetas[3][2].id,planetas[4][3].id});
        planetas[4][3].setIdVizinhos(new int[] {planetas[4][2].id,planetas[3][4].id});
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


    /**Funções ativas no Jogo*/

    public void Inserir(Item item){
        this.planetas[item.i][item.j].Inserir(item);
    }

    public void Remover(Item item) {
        //FIXME talvez de erro

        List<Integer> position = item.getPosition();
        this.planetas[position.get(0)][position.get(1)].Remover(item);

    }


    public void gerarRecurso(){
        Random gerador = new Random();
        Recursos r = null ;
        int sorteado = gerador.nextInt(16);
        System.out.println("planeta"+sorteado);
        int copia;
        if(sorteado > 8){
            copia = sorteado-8;
            System.out.println(copia);
            this.AcharPlaneta(copia).GerarRecursos();
        }
        else if(sorteado <8){
            copia = sorteado+8;
            System.out.println(copia);
            this.AcharPlaneta(copia).GerarRecursos();
        }

    }

    private Planeta AcharPlaneta(int id){
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5;j++){
                if(planetas[i][j]!=null && planetas[i][j].id == id){
                    return planetas[i][j];
                }
            }
        }
        return null;
    }


    public Object[] Mover(int idDestino, int idOrigem, String itemMovido){
        // 0 => eh valido, 1 => nao é valido , -1 => morreu de quem mexeu , -2 => morreu de quem recebeu ;
        int movimentoValido = 1;
        Planeta destino = AcharPlaneta(idDestino);
        Item item;
        Item itemdestruido = null;
        Planeta origem = AcharPlaneta(idOrigem);
        if(origem.isVizinho(idDestino)){
            System.out.println("nao é nos ids");
            switch (itemMovido){
                case "naveGuerra":
                        item = origem.hasItem(NaveGuerra.class);
                        int atacante  = item.lutar();
                    if(destino.hasItem(Satelite.class)!=null){
                        // guerra X sateilite
                        Satelite s = (Satelite) destino.hasItem(Satelite.class);
                        //FIXME preciso da instancia do item , vamo ter que modificar as coisas ?
                        int defensor = s.lutar();
                        if(atacante > defensor){
                            movimentoValido = -2;
                            itemdestruido = s;
                            System.out.println("nave ganhou");
                            destino.Remover("satelite");
                        }
                        else{
                            movimentoValido = -1;
                        }
                    }
                    else if(destino.hasItem(NaveGuerra.class)!=null){
                        //luta nave guerra X nave guerra
                        NaveGuerra n = (NaveGuerra) destino.hasItem(NaveGuerra.class);
                        int defensor = n.lutar();
                        if(atacante > defensor){
                            movimentoValido = -2;
                            itemdestruido = n;
                            destino.Remover("naveGuerra");
                        }
                        else{
                            movimentoValido = -1;
                        }
                    }else if(destino.hasItem(NaveColonizadora.class)!=null){
                        //luta nave guerra X satelite
                        NaveColonizadora n = (NaveColonizadora) destino.hasItem(NaveColonizadora.class);
                        int defensor = n.lutar();
                        if(atacante > defensor){
                            movimentoValido = -2;
                            itemdestruido = n;
                            destino.Remover("naveColonizadora");
                        }
                        else{
                            movimentoValido = -1;
                        }
                    }else{
                        movimentoValido = 0;
                    }

                    break;
                case "naveColonizadora":
                    if(destino.hasItem(NaveColonizadora.class)!=null){
                        movimentoValido = 1;
                    }
                    else{
                        movimentoValido = 0;
                    }
                    break;
            }
        }
        System.out.println("ESSE é o planeta origem "+origem);
        System.out.println("Essa é a lista do planeta origem, "+origem.getItens());
            System.out.println("movimento: "+movimentoValido);
        if(movimentoValido == 0){
            item = origem.Remover(itemMovido);
            destino.Inserir(itemMovido, item);
            return new Object[] {movimentoValido};
        }
        else if(movimentoValido == -1){
            item = origem.Remover(itemMovido);
            destino.Inserir(itemMovido, item);
            return new Object[] {movimentoValido, itemdestruido};
        }
        else  if(movimentoValido == -2){
            item = origem.Remover(itemMovido);
            destino.Inserir(item);
            return new Object[] {movimentoValido, item};
        }
        return  new Object[] {movimentoValido};
    }

    public Item Construir(int id, String objeto){
        Planeta p = AcharPlaneta(id);
        Item construido = p.Construir(objeto);
        return construido;
    }

    // getrs e setrs

    public Planeta[][] getPlanetas() {
        return planetas;
    }

    private int[] gerarIds(){
        Random gerador = new Random();
        List<Integer> ids = new ArrayList<>();
        while (ids.size()<16){
            int sorteado = gerador.nextInt(16);
            if(Collections.frequency(ids, sorteado)<1){
                ids.add(sorteado);
            }
        }
        System.out.println(ids);
        int[] saida = ids.stream().mapToInt(i->i).toArray();
        return saida;
    }


}
