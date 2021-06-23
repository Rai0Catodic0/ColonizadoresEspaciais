package View;

import Tabuleiro.Jogador;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class BarraLateral {
    public Group root;
     Rectangle lateralBar;
     Text metal;
     Text fuel;
     Text bullet;
     Text warStarship;
     Text colonizationStarship;
     Text satelite;
     ImageView imageViewWarStarshipVerde;
     ImageView imageViewWarStarshipAzul;
     ImageView imageViewColonizationStarshipAzul;
     ImageView imageViewColonizationStarshipVerde;
     ImageView imageViewSateliteAzul;
     ImageView imageViewSateliteVerde;
     ImageView imageViewFuel;
     ImageView imageViewBullet;
     ImageView imageViewMetal;



    public BarraLateral(Group root){
        this.root = root;

        //Quadrado
        lateralBar = new Rectangle();
        lateralBar.setX(0);
        lateralBar.setY(119.5);
        lateralBar.setWidth(100);
        lateralBar.setHeight(400);
        lateralBar.setFill(Color.GREY);

        //Textos itens
        // metal
        metal = new Text();
        metal.setText(String.valueOf(0));
        metal.setX(70);
        metal.setY(469.5);//409.5
        metal.setFont(Font.font("Verdana", 20));
        metal.setFill(Color.BLACK);


        // combutivel
        fuel = new Text();
        fuel.setText(String.valueOf(0));
        fuel.setX(70);
        fuel.setY(349.5);
        fuel.setFont(Font.font("Verdana", 20));
        fuel.setFill(Color.BLACK);

        //municao
        bullet = new Text();
        bullet.setText(String.valueOf(0));
        bullet.setX(70);
        bullet.setY(409.5);
        bullet.setFont(Font.font("Verdana", 20));
        bullet.setFill(Color.BLACK);

        //guerra
        warStarship = new Text();
        warStarship.setText(String.valueOf(0));//valores[5]
        warStarship.setX(70);
        warStarship.setY(169.5 ); //  349.5
        warStarship.setFont(Font.font("Verdana", 20));
        warStarship.setFill(Color.BLACK);

        //colonizadora
        colonizationStarship = new Text();
        colonizationStarship.setText(String.valueOf(1));
        colonizationStarship.setX(70);
        colonizationStarship.setY(229.5);
        colonizationStarship.setFont(Font.font("Verdana", 20));
        colonizationStarship.setFill(Color.BLACK);

        //satelite
        satelite = new Text();
        satelite.setText(String.valueOf(1));
        satelite.setX(70); // 70
        satelite.setY(289.5); // 469.5
        satelite.setFont(Font.font("Verdana", 20));
        satelite.setFill(Color.BLACK);

        //Imagens dos itens
        //FIXME fazer o getter pro repre do jogador
        // guerra

        Image warStarshipImageAzul = new Image("images/naveguerraa.png");
        imageViewWarStarshipAzul = new ImageView(warStarshipImageAzul);
        imageViewWarStarshipAzul.setX(5);
        imageViewWarStarshipAzul.setY(139.5);

        Image warStarshipImageVerde = new Image("images/naveguerrav.png");
        imageViewWarStarshipVerde = new ImageView(warStarshipImageVerde);
        imageViewWarStarshipVerde.setX(5);
        imageViewWarStarshipVerde.setY(139.5);


        // colonizadora
        Image colonizationStarshipImageAzul = new Image("images/navecolonizadoraa.png");
        imageViewColonizationStarshipAzul = new ImageView(colonizationStarshipImageAzul);
        imageViewColonizationStarshipAzul.setX(5);
        imageViewColonizationStarshipAzul.setY(200.5);

        Image colonizationStarshipImageVerde = new Image("images/navecolonizadorav.png");
        imageViewColonizationStarshipVerde = new ImageView(colonizationStarshipImageVerde);
        imageViewColonizationStarshipVerde.setX(5);
        imageViewColonizationStarshipVerde.setY(200.5);

        // satelite
        Image sateliteImageAzul = new Image("images/satelitea.png");
        imageViewSateliteAzul = new ImageView(sateliteImageAzul);
        imageViewSateliteAzul.setX(5);
        imageViewSateliteAzul.setY(261.5);

        Image sateliteImageVerde = new Image("images/satelitev.png");
        imageViewSateliteVerde = new ImageView(sateliteImageVerde);
        imageViewSateliteVerde.setX(5);
        imageViewSateliteVerde.setY(261.5);

        Image fuelImage = new Image("images/combustivel.png");
        imageViewFuel = new ImageView(fuelImage);
        imageViewFuel.setX(5);
        imageViewFuel.setY(322.5);


        Image bulletImage = new Image("images/municao.png");
        imageViewBullet = new ImageView(bulletImage);
        imageViewBullet.setX(5);
        imageViewBullet.setY(383.5);

        Image metalImage = new Image("images/metal.png");
        imageViewMetal = new ImageView(metalImage);
        imageViewMetal.setX(5);
        imageViewMetal.setY(444.5);
    }

    public void IniciarBarra(){               //desenha o triângulo e as imagnes de recusos apenas uma vez quando inicia
        root.getChildren().add(lateralBar);
        root.getChildren().add(imageViewBullet);
        root.getChildren().add(imageViewFuel);
        root.getChildren().add(imageViewMetal);


    }

    public void Desenhar(Jogador jogador){
        int[] status = jogador.status();

        metal.setText(String.valueOf(status[0]));
        root.getChildren().add(metal);

        fuel.setText(String.valueOf(status[2]));
        root.getChildren().add(fuel);

        bullet.setText(String.valueOf(status[1]));
        root.getChildren().add(bullet);

        warStarship.setText(String.valueOf(status[4]));
        root.getChildren().add(warStarship);

        colonizationStarship.setText(String.valueOf(status[5]));
        root.getChildren().add(colonizationStarship);

        satelite.setText(String.valueOf(status[3]));
        root.getChildren().add(satelite);

        if(jogador.repre.equals("a")){
            root.getChildren().add(imageViewColonizationStarshipAzul);
            root.getChildren().add(imageViewWarStarshipAzul);
            root.getChildren().add(imageViewSateliteAzul);
        } else if (jogador.repre.equals("v")){
            root.getChildren().add(imageViewColonizationStarshipVerde);
            root.getChildren().add(imageViewWarStarshipVerde);
            root.getChildren().add(imageViewSateliteVerde);
        }
    }

    public void Esconder(Jogador jogador){
        root.getChildren().remove(metal);
        root.getChildren().remove(fuel);
        root.getChildren().remove(bullet);
        root.getChildren().remove(warStarship);
        root.getChildren().remove(colonizationStarship);
        root.getChildren().remove(satelite);
        if(jogador.repre.equals("a")){
            root.getChildren().remove(imageViewColonizationStarshipAzul);
            root.getChildren().remove(imageViewWarStarshipAzul);
            root.getChildren().remove(imageViewSateliteAzul);
        } else if (jogador.repre.equals("v")){
            root.getChildren().remove(imageViewColonizationStarshipVerde);
            root.getChildren().remove(imageViewWarStarshipVerde);
            root.getChildren().remove(imageViewSateliteVerde);
        }

    }
}
