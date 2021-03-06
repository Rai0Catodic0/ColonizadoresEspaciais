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
        botaoPassarVez.setText("Passar a vez");
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
        botaoMover.setLayoutY(930);
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

        //Botao Construir sat??lite
        botaoConstruirSatelite = new Button();
        botaoConstruirSatelite.setText("Construir Satelite");
        botaoConstruirSatelite.setLayoutY(930);
        botaoConstruirSatelite.setLayoutX(500);
        botaoConstruirSatelite.setFont(Font.font("Verdana", 15));
        botaoConstruirSatelite.getStylesheets().add(getClass().getResource("styleBotaoSelecao.css").toExternalForm());
        botaoConstruirSatelite.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                construirSatelite = true;
                controle.Construir(planetaClicado, "satelite");
                Esconder();
            }
        });

        //Botao Construir Nave de Guerra
        botaoConstruirNaveGuerra = new Button();
        botaoConstruirNaveGuerra.setText("Construir Nave de Guerra");
        botaoConstruirNaveGuerra.setLayoutY(930);
        botaoConstruirNaveGuerra.setLayoutX(700);
        botaoConstruirNaveGuerra.setFont(Font.font("Verdana", 15));
        botaoConstruirNaveGuerra.getStylesheets().add(getClass().getResource("styleBotaoSelecao.css").toExternalForm());
        botaoConstruirNaveGuerra.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                construirNaveGuerra = true;
                controle.Construir(planetaClicado, "naveGuerra");
                Esconder();
            }
        });

        //Botao Construir Nave de Coloniza????o
        botaoConstruirNaveColonizacao = new Button();
        botaoConstruirNaveColonizacao.setText("Construir Nave de Colonizacao");
        botaoConstruirNaveColonizacao.setLayoutY(930);
        botaoConstruirNaveColonizacao.setLayoutX(300);
        botaoConstruirNaveColonizacao.setFont(Font.font("Verdana", 15));
        botaoConstruirNaveColonizacao.getStylesheets().add(getClass().getResource("styleBotaoSelecao.css").toExternalForm());
        botaoConstruirNaveColonizacao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                construirNaveColonizadora = true;
                controle.Construir(planetaClicado, "naveColonizadora");
                Esconder();
            }
        });

        //Botao Nave colonizadora
        botaoNaveColonizadora = new Button();
        botaoNaveColonizadora.setText("Nave Colonizadora");
        botaoNaveColonizadora.setLayoutY(930);
        botaoNaveColonizadora.setLayoutX(0);
        botaoNaveColonizadora.setFont(Font.font("Verdana", 15));
        botaoNaveColonizadora.setGraphic(GerarGraphic("navecolonizadora"));
        botaoNaveColonizadora.getStylesheets().add(getClass().getResource("styleBotaoSelecao.css").toExternalForm());
        botaoNaveColonizadora.setPrefWidth(253);
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
        botaoNaveGuerra.setMinWidth(253);
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
        botaoSatelite.setText("Sat??lite");
        botaoSatelite.setLayoutY(930);
        botaoSatelite.setLayoutX(0);
        botaoSatelite.setFont(Font.font("Verdana", 15));
        botaoSatelite.setGraphic(GerarGraphic("satelite"));
        botaoSatelite.getStylesheets().add(getClass().getResource("styleBotaoSelecao.css").toExternalForm());
        botaoSatelite.setPrefWidth(253);
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

        mover = false;
        naveColonizadoraSelecionada = false;
        naveGuerraSelecionada = false;
        sateliteSelecionado = false;

        construirSatelite = false;
        construirNaveGuerra = false;
       construirNaveColonizadora = false;


        root.getChildren().remove(botaoConstruirNaveColonizacao);
        root.getChildren().remove(botaoConstruirNaveGuerra);
        root.getChildren().remove(botaoConstruirSatelite);

        root.getChildren().remove(botaoNaveColonizadora);
        root.getChildren().remove(botaoNaveGuerra);
        root.getChildren().remove(botaoSatelite);

        root.getChildren().remove(botaoMover);
    }

    public void DesenharBarras(List<Item> itens){
        double y = 900;
        int itemNaveGuerra = 0;
        int itemNaveCol = 0;
        int itemSat = 0;
        Esconder();
        for(Item item : itens){
            if(item instanceof NaveGuerra && itemNaveGuerra ==0 && EhMinhaVez(item) ){
                itemNaveGuerra = 1;
                botaoNaveGuerra.setLayoutY(y);
                botaoNaveGuerra.setGraphic(GerarGraphic("naveguerra"));
                root.getChildren().add(botaoNaveGuerra);
                y-=85;
            } else if (item instanceof NaveColonizadora && itemNaveCol == 0 && EhMinhaVez(item)){
                itemNaveCol = 1;
                botaoNaveColonizadora.setGraphic(GerarGraphic("navecolonizadora"));
                botaoNaveColonizadora.setLayoutY(y);
                root.getChildren().add(botaoNaveColonizadora);
                y-=85;
            } else if(item instanceof Satelite && itemSat == 0 && EhMinhaVez(item)) {
                itemSat = 1;
                botaoSatelite.setLayoutY(y);
                botaoSatelite.setGraphic(GerarGraphic("satelite"));
                root.getChildren().add(botaoSatelite);
                y-=85;
            }
        }
    }

    public void ClicouPlaneta(int id, List<Item> itens){
        planetaClicado = id;
        if(mover && naveColonizadoraSelecionada){
            controle.Mover(planetaClicado, planetaRecebeAcao, "naveColonizadora");
                naveColonizadoraSelecionada = false;
                mover = false;
                Esconder();
        } else if(mover && naveGuerraSelecionada){
            controle.Mover(planetaClicado, planetaRecebeAcao, "naveGuerra");
                mover = false;
                naveGuerraSelecionada = false;
                Esconder();

        } else if (!mover){
            DesenharBarras(itens);
        }
    }

    public void IniciarBarra(){
        root.getChildren().add(botaoPassarVez);
    }
}
