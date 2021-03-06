package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class SetupScene extends Group {

    String jogador1, jogador2;
    TextField nomePlayer1;
    TextField nomePlayer2;
    Button btnSubmit;
    ImageView backgroundView;
    Text message;
    Controle controle;

    SetupScene(){
        super();
        this.setLayoutY(0);
        this.setLayoutX(0);
        this.btnSubmit = new Button("Start");
        this.nomePlayer1 = new TextField("Player 1 name ");
        this.nomePlayer2 = new TextField("Player 2 name");
        this.message = new Text("PREPARE-SE PARA COLONIZAR!");

        //message Style
        this.message.setFont(Font.font(30));
        this.message.setLayoutX(740);
        this.message.setLayoutY(210);
        this.message.setFill(Color.WHITE);

        //textfield style
        this.nomePlayer1.setLayoutY(400);
        this.nomePlayer1.setLayoutX(700);
        this.nomePlayer1.setMinHeight(50);
        this.nomePlayer1.setMinWidth(100);
        this.nomePlayer2.setLayoutY(400);
        this.nomePlayer2.setLayoutX(1100);
        this.nomePlayer2.setMinHeight(50);
        this.nomePlayer2.setMinWidth(100);

        //btn style
        this.btnSubmit.setLayoutY(500);
        this.btnSubmit.setLayoutX(930);
        this.btnSubmit.setMinHeight(50);
        this.btnSubmit.setMinWidth(100);
        this.btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               controle.setAzulNome(nomePlayer2.getText());
               controle.setVerdeNome(nomePlayer1.getText());
               controle.IniciarJogo();
            }
        });

//background image
Image background = new Image("images/fundo.jpg");
this.backgroundView = new ImageView(background);


        this.Desenhar();
    }

    public void Desenhar(){
        this.getChildren().add(this.backgroundView);
        this.getChildren().add(this.message);
        this.getChildren().add(this.nomePlayer1);
        this.getChildren().add(this.nomePlayer2);
        this.getChildren().add(this.btnSubmit);
    }

    public void Remover(){
        this.getChildren().remove(this.backgroundView);
        this.getChildren().remove(this.message);
        this.getChildren().remove(this.nomePlayer1);
        this.getChildren().remove(this.nomePlayer2);
        this.getChildren().remove(this.btnSubmit);

    }

    public void setControle(Controle controle){
        this.controle = controle;
    }

    public Button getBtnSubmit(){
        return btnSubmit;
    }
}
