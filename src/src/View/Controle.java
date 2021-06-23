package View;


import Itens.Item;
import Tabuleiro.Tabuleiro;
import Tabuleiro.Jogador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.util.Random;

public class Controle {
    private Tabuleiro tab;
    private Jogador verde;
    private Jogador azul;
    private BarraLateral barraLateral;
    private boolean construiu = false;
    private boolean passouVez = false;
    private Group root;
    private Button botaoPassarVez;
    private int vez;
    private TabuleiroGrafico tabg;
    private int numeroinicio;
    //1 = azul
    //0 = verde


    public Controle(Tabuleiro tab, Group root) {
        this.tab = tab;
        this.numeroinicio = 0;
        verde = tab.getJogadorVerde();
        azul = tab.getJogadorAzul();
        barraLateral = new BarraLateral(root);
        this.root = root;
        botaoPassarVez = new Button();
        botaoPassarVez.setText("Paasar a vez");
        botaoPassarVez.setLayoutY(50);
        botaoPassarVez.setLayoutX(0);
        botaoPassarVez.setFont(Font.font("Verdana", 15));
        botaoPassarVez.getStylesheets().add(getClass().getResource("styleBotaoSelecao.css").toExternalForm());
        botaoPassarVez.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TrocarVez();
            }
        });
        Random sortearInicio = new Random();
        vez = sortearInicio.nextInt(2);
        System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKK vez do jogador: "+vez);
    }

    public void setTabuleiroGrafico(TabuleiroGrafico tabg){
        this.tabg = tabg;
    }

    public void TrocarVez(){
        numeroinicio ++;
        if(numeroinicio ==2){
            numeroinicio = 0;
            tab.gerarRecurso();
            System.out.println("recurso gerado");
        }
        if(!JogoRodando()){
            System.out.println("ACABOU");
            tabg.Esconder();
        }
        else if(vez == 0){
            barraLateral.Esconder(verde);
            barraLateral.Desenhar(azul);
            vez = 1;
        } else if(vez == 1){
            barraLateral.Esconder(azul);
            barraLateral.Desenhar(verde);
            vez = 0;
        }
    }

    public int getVez(){
        return vez;
    }

    public Jogador getVezJogador(){
        if (getVez() == 1) {
            return azul;
        }
        return verde;
    }

    public Jogador getVezProximoJogador(){
        if (getVez() == 1) {
            return verde;
        }
        return azul;
    }

    public boolean ValidarConstrucao(String objeto){
        int [] status = getVezJogador().status();
        if(objeto.equals("naveGuerra") && status[0]>=1 && status[1]>=1 && status[2]>=1){
            //getVezJogador().RemoverRecurso();
            return true;

        } else if(objeto.equals("naveColonizadora") && status[0]>=1 && status[2]>=1){
            getVezJogador().RemoverRecurso();
            return true;
        }
        return true;
    }

    public void Construir(int planetaClicado, String objeto){
        boolean construir = ValidarConstrucao(objeto);
        if(construir){
            Item construido = tab.Construir(planetaClicado, objeto);
            getVezJogador().AdicionarItem(new Object[] {0,construido});
            barraLateral.Esconder(getVezJogador());
            barraLateral.Desenhar(getVezJogador());
            TrocarVez();
        }
    }

    public void AtualizarJogador(){

    }

    public boolean Mover(int planetaClicado, int planetaRecebeAcao, String objeto){
        Object[] resultado = tab.Mover(planetaClicado, planetaRecebeAcao, objeto);
        if(resultado[0].equals(-1)){
            getVezJogador().ExcluirItem(resultado);
            barraLateral.Esconder(getVezJogador());
            barraLateral.Desenhar(getVezJogador());
           TrocarVez();
           return true;
        }
        else if(resultado[0].equals(-2)){
            getVezProximoJogador().ExcluirItem(resultado);
            barraLateral.Esconder(getVezJogador());
            barraLateral.Desenhar(getVezJogador());
            TrocarVez();
            return true;
        } else if(resultado[0].equals(0)){
            TrocarVez();
            return true;
        }
        return false;
    }

    private boolean JogoRodando(){
        if(azul.getQtdItens()<=0 || verde.getQtdItens()<=0 || azul.getPontuacao()>=12 || verde.getPontuacao()>=12){
            return false;
        }
        return true;
    }

    public void IniciarJogo(){
        root.getChildren().add(botaoPassarVez);
        barraLateral.IniciarBarra();
        barraLateral.Desenhar(getVezJogador());
    }

}
