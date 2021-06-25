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


    public Object[] Mover(int idDestino, int idOrigem, String itemMovidoTexto){
        // 0 => eh valido, 1 => nao é valido , -1 => morreu de quem mexeu , -2 => morreu de quem recebeu ;

        int movimentoValido = 1;
        Planeta destino = AcharPlaneta(idDestino);
        Planeta origem = AcharPlaneta(idOrigem);
        Item itemMovido = origem.hasItem(itemMovidoTexto); //ver se tem item para ser movido

        if(itemMovido==null || itemMovido.movido == true){  //item movido não existe no planeta origem ou já foi movido na rodada
            return new Object[] {movimentoValido};
        }
        if(!origem.isVizinho(idDestino)){             //planetas não são vizinhos
            return new Object[] {movimentoValido};
        }

        if(destino.AvaliarSituacaoIntruso(itemMovido)==0){  //item movido sem luta
            movimentoValido = 0;
            origem.Remover(itemMovido);
            destino.Inserir(itemMovido);
            itemMovido.movido = true;
            return new Object[] {movimentoValido};
        }

        if(destino.AvaliarSituacaoIntruso(itemMovido)==1){  //Havera luta
            itemMovido.movido = true;
            Object resultado[] = destino.Lutar(itemMovido);
            if(resultado[0].equals(-1)){
                origem.Remover(itemMovido);
            } else if(resultado[0].equals(-2)){
                origem.Remover(itemMovido);
                destino.RemoverTodosItens();
                destino.Inserir(itemMovido);
            }
            return resultado;
        }

        return new Object[] {movimentoValido};   //não há possibilidade de inserir o item -> destino.AvaliarSituacaoIntruso(itemMovido)==2


    }

    public Item Construir(int id, String objeto){
        Planeta p = AcharPlaneta(id);
        Item construido = p.Construir(objeto);
        return construido;
    }

    // getrs e setrs

    public Object[] lutarNaveGuerra(Planeta origem, Planeta destino){
        Item atacante = origem.hasItem(NaveGuerra.class);
        int forcaAtaque = atacante.lutar();
        int forcaDefesaMax = 0;
        Object[] resultado = null;
        List<Item> desfensores = destino.getItens();
        for(Item item : desfensores){
            int forcaDefasa = item.lutar();
            if(forcaDefesaMax < forcaDefasa){
                forcaDefesaMax = forcaDefasa;
            }
        }
        if(forcaAtaque > forcaDefesaMax){
            resultado = new Object[1+ desfensores.size()];
            resultado[0] = -2;
            int i = 1;
            atacante = origem.Remover(atacante.getType());
            atacante.movido = true;
            destino.Inserir(atacante);
            for (Item item : desfensores){
                resultado[i] = destino.Remover(item.getType());
                i++;
            }
        }
        else{
            resultado = new Object[2];
            resultado[0] = -1;
            resultado[1] = origem.Remover(atacante.getType());
        }
        return resultado;
    }


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
