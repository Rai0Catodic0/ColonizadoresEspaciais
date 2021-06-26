package View;


import Itens.Item;
import Tabuleiro.Jogador;
import Tabuleiro.Planeta;
import Tabuleiro.Tabuleiro;
import excecoes.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class Controle {
    private Tabuleiro tab;
    private Jogador verde;
    private Jogador azul;
    private boolean construiu = false;
    private boolean passouVez = false;
    private Group rootJogo;
    private int vez;
    private int numeroinicio;
    private Text avisos;
    //1 = azul
    //0 = verde
    private Stage stage;
    private Scene cenaInicial;
    private Scene cenaJogo;


    public Controle(Stage stage) {

        stage.setTitle("Conquistadores do Espaço");
        this.stage=stage;

         SetupScene rootInicial = new SetupScene();
         cenaInicial = new Scene(rootInicial,2399,1199);
         rootInicial.setControle(this);

        Group rootJogo = new Group();
        Group sub = new Group();
        cenaJogo = new Scene(rootJogo,2399,1199);

        Image background = new Image("images/background.png");
        ImageView backgroundView = new ImageView(background);
        rootJogo.getChildren().add(backgroundView);
        rootJogo.getChildren().add(sub);


        tab = new Tabuleiro();
        BarraSelecao barraSelecao  = new BarraSelecao(rootJogo, this);
        TabuleiroGrafico tabg = new TabuleiroGrafico(rootJogo, barraSelecao);

        //Linka Tile com planeta
        Planeta planetas[][] = tab.getPlanetas();
        int k = 0;
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5;j++){
                if(planetas[i][j]!=null){
                    planetas[i][j].setTile(tabg.botoes.get(k));
                    planetas[i][j].addPropertyChangeListener(tabg.botoes.get(k));
                    k++;
                }
            }
        }

        barraSelecao.IniciarBarra();
        tabg.Desenhar();

        stage.setScene(cenaInicial);

        avisos = new Text("");
        avisos.setLayoutX(700);
        avisos.setLayoutY(50);
        avisos.setFont(Font.font("Verdana", 20));
        avisos.setFill(Color.WHITE);

        JogadorGrafico jogGraficoAzul = new JogadorGrafico(rootJogo,"blue",500,50);
        JogadorGrafico jogGraficoVerde = new JogadorGrafico(rootJogo,"green",300,50);

        this.numeroinicio = 0;

        azul = new Jogador("a");
        verde = new Jogador("v");

        verde.setItens(tab.getItensB().get(0));
        verde.setItens(tab.getItensB().get(1));
        azul.setItens(tab.getItensA().get(0));
        azul.setItens(tab.getItensA().get(1));

        azul.pontuacao = 4;
        verde.pontuacao = 4;

        azul.addPropertyChangeListener(jogGraficoAzul);
        verde.addPropertyChangeListener(jogGraficoVerde);

        this.rootJogo = rootJogo;

        Random sortearInicio = new Random();

        vez = sortearInicio.nextInt(2);
        if(vez==1){
            azul.TrocarVez();
        } else {
            verde.TrocarVez();
        }

        stage.show();
    }

    public void PassarVez(){
        avisos.setText(getVezJogador().nome+" passou a vez! Agora é a vez de "+getVezProximoJogador().nome);
        TrocarVez();
    }


    public void TrocarVez(){
        getVezJogador().ResetarMovimentos();
        azul.TrocarVez();
        verde.TrocarVez();
        numeroinicio ++;
        if(numeroinicio ==2){
            numeroinicio = 0;
            tab.gerarRecurso();
            azul.setRecursos();
            verde.setRecursos();
        }

        if(!JogoRodando()){
            FinalizarJogo();
        }
        else if(vez == 0){
            vez = 1;
        } else if(vez == 1){
            vez = 0;
        }
    }

    public void FinalizarJogo(){

        String corVencedor;
        String nomeVencedor;
        String nomePerdedor;
        int pontosVencedor;
        int pontosPerdedor;

        if(azul.getQtdItens()<=0 || azul.getPontuacao()>=12 ){
            corVencedor = azul.repre;
            nomeVencedor = azul.nome;
            nomePerdedor = verde.nome;
            pontosVencedor = azul.getPontuacao();
            pontosPerdedor = verde.getPontuacao();
        } else {
            corVencedor = verde.repre;
            nomeVencedor = verde.nome;
            nomePerdedor = azul.nome;
            pontosVencedor = verde.getPontuacao();
            pontosPerdedor = azul.getPontuacao();
        }

        Scene cenaFinal = new Scene(new CenaFinal(corVencedor, nomeVencedor, nomePerdedor, pontosVencedor, pontosPerdedor),2399,1199);
        stage.setScene(cenaFinal);
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
        Item construido = tab.Construir(planetaClicado, objeto);
        if(construido!=null){
            getVezJogador().AdicionarItem(new Object[] {0,construido});
            TrocarVez();
            return true;
        }return false;
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
            avisos.setText(getVezJogador().nome+", você perdeu essa batalha! Boa defesa, "+getVezProximoJogador().nome);
        }
        else if(resultado[0].equals(-2)){
            getVezProximoJogador().ExcluirItem(resultado);
            avisos.setText(getVezJogador().nome+", você ganhou essa batalha!");
        }
        System.out.println("MOVEU TODOS: "+getVezJogador().MoveuTodosItens()+getVezJogador().nome);
        if(resultado[0].equals(1)){
            avisos.setText("Movimento Inválido. Mova para planetas vizinhos, siga as linhas!");
            return false;
        } else if(getVezJogador().MoveuTodosItens()){
            avisos.setText("Parabéns, "+getVezJogador().nome+ "moveu todos os seus itens! Agora é a vez de "+ getVezProximoJogador().nome);
            TrocarVez();
            return true;
        } else {
            avisos.setText("Movimento bem sucedido, "+getVezJogador().nome+"! Você pode mover o resto dos seus itens ou construir!");
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
       azul.IniciarJogador();
       verde.IniciarJogador();
       rootJogo.getChildren().add(avisos);
       stage.setScene(cenaJogo);
    }

    public void setAzulNome(String nome){
        azul.setNome(nome);
    }

    public void setVerdeNome(String nome){
        verde.setNome(nome);
    }


}
