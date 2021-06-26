package View;

import Tabuleiro.Tabuleiro;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {
    public BarraSelecao barraSelecao;
    public Tabuleiro tab;

    @Override
    public void start(Stage stage) throws Exception{

        Controle controle = new Controle(stage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}