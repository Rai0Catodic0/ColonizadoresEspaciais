package View;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BotaoPlaneta extends Button {

    public BotaoPlaneta(String imhpath, int[] positions){
        super();
        ImageView planetaImg = new ImageView(new Image(imhpath));
        this.setLayoutY(30);
        this.setLayoutX(30);
        this.setGraphic(planetaImg);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }


}
