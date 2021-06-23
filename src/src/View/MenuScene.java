package View;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class MenuScene  extends Group {

    Button jogar;
    Button sair;
    Text titulo;
    ImageView backgroundView;

    MenuScene(){
        super();
        this.setLayoutY(0);
        this.setLayoutX(0);
        this.jogar = new Button("jogar");
        this.sair = new Button("sair");

        //Titulo
        this.titulo = new Text("Colonizadores do Espaço");
        this.titulo.setFont(Font.font(70));
        this.titulo.setLayoutY(300);
        this.titulo.setLayoutX(510);
        this.titulo.setFill( Color.MEDIUMPURPLE);

        //jogar
        this.jogar.setMinWidth(200);
        this.jogar.setMinHeight(70);
        this.jogar.setLayoutX(850);
        this.jogar.setLayoutY(500);

        //sair
        this.sair.setMinWidth(200);
        this.sair.setMinHeight(70);
        this.sair.setLayoutX(850);
        this.sair.setLayoutY(600);

        //background image
        Image background = new Image("images/backgroundsetUp.jpeg");
        this.backgroundView = new ImageView(background);
    }

    public void  desenhar(){
        this.getChildren().add(this.backgroundView);
        this.getChildren().add(this.jogar);
        this.getChildren().add(this.sair);
        this.getChildren().add(this.titulo);

    }

    public void  remover(){
        this.getChildren().remove(this.backgroundView);
        this.getChildren().remove(this.jogar);
        this.getChildren().remove(this.sair);
        this.getChildren().remove(this.titulo);
    }
}
