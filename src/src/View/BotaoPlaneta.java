package View;

import excecoes.InvalidPlaneta;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BotaoPlaneta extends Button {


    public BotaoPlaneta(String imhpath, int[] positions) throws RuntimeException{
        super();
        ImageView planetaImg ;
        try {
            planetaImg = new ImageView(new Image(imhpath));
        }catch (RuntimeException e){
            throw new InvalidPlaneta(imhpath);
        }
        this.setLayoutY(30);
        this.setLayoutX(30);
        this.setGraphic(planetaImg);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }


}
