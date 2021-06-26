package View;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CenaFinal extends Group {

    CenaFinal(String corVencedor, String nomeVencedor, String nomePerdedor, int pontosVencedor, int pontosPerdedor){
        super();
        Image background = new Image("images/backgroundsetUp.jpeg");
        ImageView backgroundView = new ImageView(background);
        Text mensagem = new Text(nomeVencedor+" ganhou com "+pontosVencedor+" pontos! Parabéns astronauta! "+nomePerdedor+", não fique triste, você fez "+pontosPerdedor+" pontos, na próxima você consegue mais!");
        mensagem.setFont(Font.font("Verdana", 20));
        mensagem.setFill(Color.AQUA);
        mensagem.setLayoutX(0);
        mensagem.setLayoutY(300);
        this.getChildren().add(backgroundView);
        this.getChildren().add(mensagem);
    }

}
