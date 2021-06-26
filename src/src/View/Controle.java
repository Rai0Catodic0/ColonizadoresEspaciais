package View;


import Itens.Item;
import Tabuleiro.Jogador;
import Tabuleiro.Tabuleiro;
import javafx.scene.Group;
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
    private JogadorGrafico jogGraficoAzul;
    private JogadorGrafico jogGraficoVerde;


    public Controle(Tabuleiro tab, Group root) {

        avisos = new Text("");
        avisos.setLayoutX(700);
        avisos.setLayoutY(50);
        avisos.setFont(Font.font("Verdana", 20));
        avisos.setFill(Color.WHITE);


        jogGraficoAzul = new JogadorGrafico(root,"blue",500,50);
        jogGraficoVerde = new JogadorGrafico(root,"green",300,50);

        this.tab = tab;
        this.numeroinicio = 0;

        azul = new Jogador("a","Isa");
        verde = new Jogador("v", "Pedro");

        verde.setItens(tab.getItensA().get(0));
        verde.setItens(tab.getItensA().get(1));
        azul.setItens(tab.getItensB().get(0));
        azul.setItens(tab.getItensB().get(1));

        azul.addPropertyChangeListener(jogGraficoAzul);
        verde.addPropertyChangeListener(jogGraficoVerde);

        barraLateral = new BarraLateral(root);

        this.root = root;

        Random sortearInicio = new Random();
        vez = sortearInicio.nextInt(2);
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
            getVezJogador().RemoverRecurso(objeto);
            return true;

        } else if(objeto.equals("naveColonizadora") && status[0]>=1 && status[2]>=1){
            getVezJogador().RemoverRecurso(objeto);
            return true;
        }else if(status[0]>=2 && status[1]>=1){
            getVezJogador().RemoverRecurso(objeto);
            return true;
        }
        return false;
    }

    public boolean Construir(int planetaClicado, String objeto){
        boolean construir = ValidarConstrucao(objeto);
        if(construir){
            Item construido = tab.Construir(planetaClicado, objeto);
            if(construido!=null){
                getVezJogador().AdicionarItem(new Object[] {0,construido});
                barraLateral.Esconder(getVezJogador());
                barraLateral.Desenhar(getVezJogador());
                TrocarVez();
                return true;
            } else {
                return false;
            }
        } else {
            avisos.setText(getVezJogador().nome+", você não pode construir aqui!");
            return false;
        }
    }

    public void AtualizarJogador(){

    }

    public boolean Mover(int planetaClicado, int planetaRecebeAcao, String objeto){
        avisos.setText("");
        Object[] resultado = tab.Mover(planetaClicado, planetaRecebeAcao, objeto);
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

        jogGraficoVerde.Desenhar("Pedro");
        jogGraficoAzul.Desenhar("Isa");

        root.getChildren().add(avisos);

        barraLateral.IniciarBarra();
        barraLateral.Desenhar(getVezJogador());
    }

}
