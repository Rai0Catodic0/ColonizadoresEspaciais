package View;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CenaFinal extends Group {

    CenaFinal(){
        super();
        Image background = new Image("images/backgroundsetUp.jpeg");
        ImageView backgroundView = new ImageView(background);
        Text mensagem = new Text("Fulano ganhou");
        mensagem.setFont(Font.font("Verdana", 40));
        mensagem.setFill(Color.AQUA);
        mensagem.setLayoutX(0);
        mensagem.setLayoutY(300);
        this.getChildren().add(backgroundView);
        this.getChildren().add(mensagem);
    }

}
