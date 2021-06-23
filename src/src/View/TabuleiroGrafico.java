package View;

import Tabuleiro.Tabuleiro;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class TabuleiroGrafico  {
    Tabuleiro tab;
    Group root;
    List<Tile> botoes = new ArrayList<>();

    public TabuleiroGrafico(Group root, BarraSelecao barraSelecao){
        this.root = root;
        for(int i =0; i<16;i++){
            botoes.add(new Tile(barraSelecao));
        }
    }

    public boolean Desenhar(){
        for (Tile botao : botoes) {
            this.root.getChildren().add(botao);
        }
        return true;
    }

    public boolean Esconder(){
        for (Tile botao : botoes) {
            this.root.getChildren().remove(botao);
        }
        return true;
    }

}
