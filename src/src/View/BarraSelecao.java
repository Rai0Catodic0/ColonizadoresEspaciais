package View;

import Itens.Item;
import Itens.NaveColonizadora;
import Itens.NaveGuerra;
import Itens.Satelite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import java.util.List;

public class BarraSelecao {

    private boolean naveColonizadoraSelecionada = false;
    private boolean naveGuerraSelecionada = false;
    private boolean sateliteSelecionado = false;
    private Button botaoMover;
    private Button botaoConstruirSatelite;
    private Button botaoConstruirNaveGuerra;
    private Button botaoConstruirNaveColonizacao;
    private Button botaoNaveColonizadora;
    private Button botaoNaveGuerra;
    private Button botaoSatelite;
    private Controle controle;
    private Button botaoPassarVez;

    private boolean mover = false;
    private boolean construirNaveGuerra = false;
    private boolean construirNaveColonizadora = false;
    private boolean construirSatelite = false;

    private int planetaRecebeAcao;
    private int planetaClicado;

    private Group root;

    public BarraSelecao(Group root, Controle controle){
        this.root = root;
        this.controle = controle;
        CriarCena();
    }

    private ImageView GerarGraphic(String tipo){
        int vez = controle.getVez();
        ImageView imageView;
        if(vez == 1){
            Image image = new Image("images/"+tipo+"a.png");
            imageView = new ImageView(image);
        } else {
            Image image = new Image("images/"+tipo+"v.png");
            imageView = new ImageView(image);
        }
        return imageView;
    }


    private void CriarCena(){
        //Botao Passar Vez
        botaoPassarVez = new Button();
        botaoPassarVez.setText("Paasar a vez");
        botaoPassarVez.setLayoutY(50);
        botaoPassarVez.setLayoutX(0);
        botaoPassarVez.setFont(Font.font("Verdana", 15));
        botaoPassarVez.getStylesheets().add(getClass().getResource("styleBotaoSelecao.css").toExternalForm());
        botaoPassarVez.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controle.PassarVez();
                Esconder();
            }
        });

        //Botao Mover
        botaoMover = new Button();
        botaoMover.setText("Mover");
        botaoMover.setLayoutY(539.5);
        botaoMover.setLayoutX(300);
        botaoMover.setFont(Font.font("Verdana", 15));
        botaoMover.getStylesheets().add(getClass().getResource("styleBotaoSelecao.css").toExternalForm());
        botaoMover.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mover = true;
                planetaRecebeAcao = planetaClicado;
            }
        });

        //Botao Construir satélite
        botaoConstruirSatelite = new Button();
        botaoConstruirSatelite.setText("Construir Satelite");
        botaoConstruirSatelite.setLayoutY(539.5);
        botaoConstruirSatelite.setLayoutX(500);
        botaoConstruirSatelite.setFont(Font.font("Verdana", 15));
        botaoConstruirSatelite.getStylesheets().add(getClass().getResource("styleBotaoSelecao.css").toExternalForm());
        botaoConstruirSatelite.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                construirSatelite = true;
                if(controle.Construir(planetaClicado, "satelite")){
                    Esconder();
                }
            }
        });

        //Botao Construir Nave de Guerra
        botaoConstruirNaveGuerra = new Button();
        botaoConstruirNaveGuerra.setText("Construir Nave de Guerra");
        botaoConstruirNaveGuerra.setLayoutY(539.5);
        botaoConstruirNaveGuerra.setLayoutX(700);
        botaoConstruirNaveGuerra.setFont(Font.font("Verdana", 15));
        botaoConstruirNaveGuerra.getStylesheets().add(getClass().getResource("styleBotaoSelecao.css").toExternalForm());
        botaoConstruirNaveGuerra.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                construirNaveGuerra = true;
                if(controle.Construir(planetaClicado, "naveGuerra")){
                    Esconder();
                }
            }
        });

        //Botao Construir Nave de Colonização
        botaoConstruirNaveColonizacao = new Button();
        botaoConstruirNaveColonizacao.setText("Construir Nave de Colonizacao");
        botaoConstruirNaveColonizacao.setLayoutY(539.5);
        botaoConstruirNaveColonizacao.setLayoutX(300);
        botaoConstruirNaveColonizacao.setFont(Font.font("Verdana", 15));
        botaoConstruirNaveColonizacao.getStylesheets().add(getClass().getResource("styleBotaoSelecao.css").toExternalForm());
        botaoConstruirNaveColonizacao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                construirNaveColonizadora = true;
                if(controle.Construir(planetaClicado, "naveColonizadora")){
                    Esconder();
                }
            }
        });

        //Botao Nave colonizadora
        botaoNaveColonizadora = new Button();
        botaoNaveColonizadora.setText("Nave Colonizadora");
        botaoNaveColonizadora.setLayoutY(539.5);
        botaoNaveColonizadora.setLayoutX(0);
        botaoNaveColonizadora.setFont(Font.font("Verdana", 15));
        botaoNaveColonizadora.setGraphic(GerarGraphic("navecolonizadora"));
        botaoNaveColonizadora.getStylesheets().add(getClass().getResource("styleBotaoSelecao.css").toExternalForm());
        botaoNaveColonizadora.setMinWidth(210);
        botaoNaveColonizadora.setAlignment(Pos.BASELINE_LEFT);
        botaoNaveColonizadora.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                naveGuerraSelecionada = false;
                sateliteSelecionado = false;
                naveColonizadoraSelecionada=!naveColonizadoraSelecionada;
                ColocarBotoes();
            }
        });

        //Botao Nave de Guerra
        botaoNaveGuerra = new Button();
        botaoNaveGuerra.setText("Nave de Guerra");
        botaoNaveGuerra.setLayoutY(599.5);
        botaoNaveGuerra.setLayoutX(0);
        botaoNaveGuerra.setFont(Font.font("Verdana", 15));
        botaoNaveGuerra.setGraphic(GerarGraphic("naveguerra"));
        botaoNaveGuerra.getStylesheets().add(getClass().getResource("styleBotaoSelecao.css").toExternalForm());
        botaoNaveGuerra.setMinWidth(210);
        botaoNaveGuerra.setAlignment(Pos.BASELINE_LEFT);
        botaoNaveGuerra.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                naveColonizadoraSelecionada = false;
                sateliteSelecionado = false;
                naveGuerraSelecionada=!naveGuerraSelecionada;
                ColocarBotoes();
            }
        });


        //Botao Nave de Satelite
        botaoSatelite = new Button();
        botaoSatelite.setText("Satélite");
        botaoSatelite.setLayoutY(659.5);
        botaoSatelite.setLayoutX(0);
        botaoSatelite.setFont(Font.font("Verdana", 15));
        botaoSatelite.setGraphic(GerarGraphic("satelite"));
        botaoSatelite.getStylesheets().add(getClass().getResource("styleBotaoSelecao.css").toExternalForm());
        botaoSatelite.setMinWidth(210);
        botaoSatelite.setAlignment(Pos.BASELINE_LEFT);
        botaoSatelite.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                naveColonizadoraSelecionada = false;
                naveGuerraSelecionada = false;
                sateliteSelecionado=!sateliteSelecionado;
                ColocarBotoes();
            }
        });
    }

    public boolean EhMinhaVez(Item item){
        int vez = controle.getVez();
        if(vez==1 && item.representacao.equals("a")){
            return true;
        } else if(vez==0 && item.representacao.equals("v")){
            return true;
        }
        return false;
    }

    private void ColocarBotoes(){
        if((naveColonizadoraSelecionada || naveGuerraSelecionada)&&!root.getChildren().contains(botaoMover)){
            root.getChildren().add(botaoMover);
        }
        if(!naveColonizadoraSelecionada && !naveGuerraSelecionada){
            root.getChildren().remove(botaoMover);
        }
        if(naveColonizadoraSelecionada){
            root.getChildren().add(botaoConstruirSatelite);
        }
        if(!naveColonizadoraSelecionada){
            root.getChildren().remove(botaoConstruirSatelite);
        }
        if(sateliteSelecionado){
            root.getChildren().add(botaoConstruirNaveColonizacao);
            root.getChildren().add(botaoConstruirNaveGuerra);
        } else {
            root.getChildren().remove(botaoConstruirNaveColonizacao);
            root.getChildren().remove(botaoConstruirNaveGuerra);
        }
    }

    public void Esconder(){

        //mover = false;
        //naveColonizadoraSelecionada = false;
        //naveGuerraSelecionada = false;
        //sateliteSelecionado = false;

       // construirSatelite = false;
        //construirNaveGuerra = false;
       //222 construirNaveColonizadora = false;


        root.getChildren().remove(botaoConstruirNaveColonizacao);
        root.getChildren().remove(botaoConstruirNaveGuerra);
        root.getChildren().remove(botaoConstruirSatelite);

        root.getChildren().remove(botaoNaveColonizadora);
        root.getChildren().remove(botaoNaveGuerra);
        root.getChildren().remove(botaoSatelite);

        root.getChildren().remove(botaoMover);
    }

    public void DesenharBarras(List<Item> itens){
        System.out.println("Itens do desenhar barras: "+itens);
        double y = 539.5;
        int itemNaveGuerra = 0;
        int itemNaveCol = 0;
        int itemSat = 0;
        Esconder();
        for(Item item : itens){
            System.out.println("Esse é o iten do loop desenhar Barras"+item);
            if(item instanceof NaveGuerra && itemNaveGuerra ==0 && EhMinhaVez(item) ){
                itemNaveGuerra = 1;
                botaoNaveGuerra.setLayoutY(y);
                botaoNaveGuerra.setGraphic(GerarGraphic("naveguerra"));
                root.getChildren().add(botaoNaveGuerra);
                y+=60;
                System.out.println("item no if nave de guerra");
            } else if (item instanceof NaveColonizadora && itemNaveCol == 0 && EhMinhaVez(item)){
                itemNaveCol = 1;
                botaoConstruirNaveColonizacao.setLayoutY(y);
                botaoNaveColonizadora.setGraphic(GerarGraphic("navecolonizadora"));
                root.getChildren().add(botaoNaveColonizadora);
                y+=60;
                System.out.println("item no item nave col");
            } else if(item instanceof Satelite && itemSat == 0 && EhMinhaVez(item)) {
                itemSat = 1;
                botaoSatelite.setLayoutY(y);
                botaoSatelite.setGraphic(GerarGraphic("satelite"));
                root.getChildren().add(botaoSatelite);
                y+=60;
                System.out.println("Item no item satelte");
            }
        }
    }

    public void ClicouPlaneta(int id, List<Item> itens){
        planetaClicado = id;
        if(mover && naveColonizadoraSelecionada){
            if(controle.Mover(planetaClicado, planetaRecebeAcao, "naveColonizadora")){
                naveColonizadoraSelecionada = false;
                mover = false;
                Esconder();
            }
            System.out.println("mover nave de colonização");
        } else if(mover && naveGuerraSelecionada){
            if(controle.Mover(planetaClicado, planetaRecebeAcao, "naveGuerra")){
                mover = false;
                naveGuerraSelecionada = false;
                Esconder();
            }
            System.out.println("mover nave de guerra");

        } else if (!mover){
            DesenharBarras(itens);
            System.out.println("Desenhar Barras");
        }
    }

    public void IniciarBarra(){
        root.getChildren().add(botaoPassarVez);
    }
}
