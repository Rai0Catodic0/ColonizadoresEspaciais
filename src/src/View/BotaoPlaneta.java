package View;

import excecoes.InvalidPlaneta;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BotaoPlaneta extends Button {

    ImageView planetaImg;


    public BotaoPlaneta(String imhpath, int[] positions) throws RuntimeException{
        super();
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

    public void Brilhar(){
        ImageView fundo = new ImageView(new Image("images/highlight.png"));
        planetaImg.setBlendMode(BlendMode.DIFFERENCE);

        Group blend = new Group(
                planetaImg,
                fundo
        );

        this.setGraphic(blend);

    }


}
