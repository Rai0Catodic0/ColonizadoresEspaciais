package View;


import Itens.Item;
import Tabuleiro.Jogador;
import Tabuleiro.Tabuleiro;
import excecoes.*;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Random;

public class Controle {
    private Tabuleiro tab;
    private Jogador verde;
    private Jogador azul;
    private BarraLateral barraLateral;
    private boolean construiu = false;
    private boolean passouVez = false;
    private Group root;
    private int vez;
    private TabuleiroGrafico tabg;
    private int numeroinicio;
    private Text avisos;
    //1 = azul
    //0 = verde
    private ProgressBar barraPontuacao;
    private ProgressBar progressoAzul;
    private Text nomeAzul;
    private Text nomeVerde;
    private Text pontosAzul;
    private Text pontosverde;


    public Controle(Tabuleiro tab, Group root) {

        avisos = new Text("");
        avisos.setLayoutX(700);
        avisos.setLayoutY(50);
        avisos.setFont(Font.font("Verdana", 20));
        avisos.setFill(Color.WHITE);
        barraPontuacao = new ProgressBar(0);
        barraPontuacao.setLayoutX(300);
        barraPontuacao.setLayoutY(50);
        barraPontuacao.setStyle("-fx-accent: green");

        progressoAzul = new ProgressBar(0);
        progressoAzul.setLayoutX(500);
        progressoAzul.setLayoutY(50);
        progressoAzul.setStyle("-fx-accent: blue");



        this.tab = tab;
        this.numeroinicio = 0;
        verde = tab.getJogadorVerde();
        azul = tab.getJogadorAzul();
        barraLateral = new BarraLateral(root);
        this.root = root;
        Random sortearInicio = new Random();
        vez = sortearInicio.nextInt(2);
        pontosAzul = new Text(String.valueOf(0));
        pontosAzul.setLayoutX(545);
        pontosAzul.setLayoutY(66);
        pontosAzul.setFont(Font.font("Verdana", 18));
        pontosverde = new Text((String.valueOf(0)));
        pontosverde.setLayoutX(345);
        pontosverde.setLayoutY(66);
        pontosverde.setFont(Font.font("Verdana", 18));
        nomeAzul = new Text(azul.nome);
        nomeAzul.setLayoutX(500);
        nomeAzul.setLayoutY(85);
        nomeAzul.setFont(Font.font("Verdana", 18));
        nomeAzul.setFill(Color.WHITE);
        nomeVerde = new Text(verde.nome);
       nomeVerde.setLayoutX(300);
       nomeVerde.setLayoutY(85);
       nomeVerde.setFont(Font.font("Verdana", 18));
       nomeVerde.setFill(Color.WHITE);
    }

    public void setTabuleiroGrafico(TabuleiroGrafico tabg){
        this.tabg = tabg;
    }

    public void TrocarVez(){
        getVezJogador().ResetarMovimentos();
        numeroinicio ++;
        if(numeroinicio ==2){
            numeroinicio = 0;
            tab.gerarRecurso();
        }
        if(!JogoRodando()){
            tabg.Esconder();
        }
        else if(vez == 0){
            barraLateral.Esconder(verde);
            barraLateral.Desenhar(azul);
            double progresso = getVezJogador().pontuacao/12.0;
            barraPontuacao.setProgress(progresso);
            pontosverde.setText(String.valueOf(getVezJogador().pontuacao));
            vez = 1;
        } else if(vez == 1){
            barraLateral.Esconder(azul);
            barraLateral.Desenhar(verde);
            double progresso = getVezJogador().pontuacao/12.0;
            pontosAzul.setText(String.valueOf(getVezJogador().pontuacao));
            progressoAzul.setProgress(progresso);
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

    public void ValidarConstrucao(String objeto)throws RuntimeException{
        int [] status = getVezJogador().status();
        if(objeto.equals("naveGuerra") && status[0]>=1 && status[1]>=1 && status[2]>=1){
            getVezJogador().RemoverRecurso(objeto);
            return;
        } else if(objeto.equals("naveColonizadora") && status[0]>=1 && status[2]>=1){
            getVezJogador().RemoverRecurso(objeto);
            return;
        }else if(status[0]>=2 && status[1]>=1){
            getVezJogador().RemoverRecurso(objeto);
            return;
        }else{
            System.out.println("EROO AO VALIDAR");
            throw new NotEnoughRecursos(objeto);
        }
    }

    public boolean Construir(int planetaClicado, String objeto){
        try{
            ValidarConstrucao(objeto);
        }catch (NotEnoughRecursos notEnoughRecursos){
            avisos.setText(getVezJogador().nome+","+notEnoughRecursos.getMessage());
            System.out.println(notEnoughRecursos.getMessage());
            return false;
        }
        System.out.println("FODaSE");
        Item construido = tab.Construir(planetaClicado, objeto);
        if(construido!=null){
            getVezJogador().AdicionarItem(new Object[] {0,construido});
            barraLateral.Esconder(getVezJogador());
            barraLateral.Desenhar(getVezJogador());
            TrocarVez();
            return true;
        }return false;
    }

    public void AtualizarJogador(){

    }

    public boolean Mover(int planetaClicado, int planetaRecebeAcao, String objeto){
        avisos.setText("");
        Object[] resultado = null;
        try{
          resultado = tab.Mover(planetaClicado, planetaRecebeAcao, objeto);
        } catch (ItemAlreadyMoved itemAlreadyMoved){
            avisos.setText("Movimento Inválido. Essa nave já foi movida, escolha outra !");
            return false;
        }catch (MovementOUtOfReach movementOUtOfReach){
            avisos.setText("Movimento Inválido. Mova para planetas vizinhos, siga as linhas!");
            return  false;
        }catch (MovementBlockedByNaveColonizadora movementBlockedByNaveColonizadora){
            avisos.setText("Movimento Inválido. Naves colonizadoras não podem atacar outras naves, faça outro movimento !");
            return false;
        }
        if(resultado[0].equals(-1)){
            getVezJogador().ExcluirItem(resultado);
            barraLateral.Esconder(getVezJogador());
            barraLateral.Desenhar(getVezJogador());
            avisos.setText(getVezJogador().nome+", você perdeu essa batalha! Boa defesa, "+getVezProximoJogador().nome);
        }
        else if(resultado[0].equals(-2)){
            getVezProximoJogador().ExcluirItem(resultado);
            barraLateral.Esconder(getVezJogador());
            barraLateral.Desenhar(getVezJogador());
            avisos.setText(getVezJogador().nome+", você ganhou essa batalha!");
        }
        if(resultado[0].equals(1)){
            avisos.setText("Movimento Inválido. Mova para planetas vizinhos, siga as linhas!");
            return false;
        } else if(getVezJogador().MoveuTodosItens()){
            avisos.setText("Parabéns, "+getVezJogador().nome+ "moveu todos os seus itens! Agora é a vez de "+ getVezProximoJogador().nome);
            TrocarVez();
            return true;
        } else {
            avisos.setText("Movimento bem sucedido, "+getVezJogador()+"! Você pode mover o resto dos seus itens ou construir!");
            return true;
        }

    }

    private boolean JogoRodando(){
        if(azul.getQtdItens()<=0 || verde.getQtdItens()<=0 || azul.getPontuacao()>=12 || verde.getPontuacao()>=12){
            return false;
        }
        return true;
    }

    public void IniciarJogo(){
        avisos.setText("O jogo começou! "+ getVezJogador().nome+", é a sua vez!");
        root.getChildren().add(nomeVerde);
        root.getChildren().add(nomeAzul);
        root.getChildren().add(avisos);
        root.getChildren().add(progressoAzul);
        root.getChildren().add(barraPontuacao);
        root.getChildren().add(pontosAzul);
        root.getChildren().add(pontosverde);
        barraLateral.IniciarBarra();
        barraLateral.Desenhar(getVezJogador());
    }

}
