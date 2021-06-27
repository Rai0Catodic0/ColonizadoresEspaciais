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
        Image background;
        if(corVencedor.equals("a")){
            background = new Image("images/azulvence.jpg");
        } else {
            background = new Image("images/verdevence.jpg");
        }
        ImageView backgroundView = new ImageView(background);
        Text mensagem = new Text("Parabéns, "+nomeVencedor+"! Você ganhou com "+pontosVencedor+" pontos!");
        mensagem.setFont(Font.font("Verdana", 30));
        mensagem.setFill(Color.WHITE);
        mensagem.setLayoutX(650);
        mensagem.setLayoutY(100);

        Text mensagemFinal = new Text(nomePerdedor+", você fez "+pontosPerdedor+" pontos. Não fique triste, na próxima você faz mais!");
        mensagemFinal.setFont(Font.font("Verdana", 20));
        mensagemFinal.setFill(Color.WHITE);
        mensagemFinal.setLayoutX(670);
        mensagemFinal.setLayoutY(150);

        this.getChildren().add(backgroundView);
        this.getChildren().add(mensagem);
        this.getChildren().add(mensagemFinal);
    }

}
