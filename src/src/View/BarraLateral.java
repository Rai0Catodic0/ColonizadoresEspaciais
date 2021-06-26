package View;

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
     ImageView imageViewWarStarship;
     ImageView imageViewColonizationStarship;
     ImageView imageViewSatelite;
     ImageView imageViewFuel;
     ImageView imageViewBullet;
     ImageView imageViewMetal;



    public BarraLateral(Group root, String cor){
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

        Image warStarshipImage = new Image("images/naveguerra"+cor+".png");
        imageViewWarStarship = new ImageView(warStarshipImage);
        imageViewWarStarship.setX(5);
        imageViewWarStarship.setY(139.5);


        // colonizadora
        Image colonizationStarshipImage = new Image("images/navecolonizadora"+cor+".png");
        imageViewColonizationStarship = new ImageView(colonizationStarshipImage);
        imageViewColonizationStarship.setX(5);
        imageViewColonizationStarship.setY(200.5);

        // satelite
        Image sateliteImage = new Image("images/satelite"+cor+".png");
        imageViewSatelite = new ImageView(sateliteImage);
        imageViewSatelite.setX(5);
        imageViewSatelite.setY(261.5);


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


    public void Desenhar(){
        root.getChildren().add(lateralBar);
        root.getChildren().add(metal);
        root.getChildren().add(fuel);
        root.getChildren().add(bullet);
        root.getChildren().add(warStarship);
        root.getChildren().add(colonizationStarship);
        root.getChildren().add(satelite);
        root.getChildren().add(imageViewColonizationStarship);
        root.getChildren().add(imageViewWarStarship);
        root.getChildren().add(imageViewSatelite);
        root.getChildren().add(imageViewMetal);
        root.getChildren().add(imageViewBullet);
        root.getChildren().add(imageViewFuel);
    }

    public void update(int[] status) {
        //metal,municao,combustivel,satelite,guerra,colonizacao
        //System.out.println("Estou sendo atualizada");
        metal.setText(String.valueOf(status[0]));
        fuel.setText(String.valueOf(status[2]));
        bullet.setText(String.valueOf(status[1]));
        warStarship.setText(String.valueOf(status[4]));
        colonizationStarship.setText(String.valueOf(status[5]));
        satelite.setText(String.valueOf(status[3]));
    }

    public void Esconder(){
        root.getChildren().remove(lateralBar);
        root.getChildren().remove(metal);
        root.getChildren().remove(fuel);
        root.getChildren().remove(bullet);
        root.getChildren().remove(warStarship);
        root.getChildren().remove(colonizationStarship);
        root.getChildren().remove(satelite);
        root.getChildren().remove(imageViewColonizationStarship);
        root.getChildren().remove(imageViewWarStarship);
        root.getChildren().remove(imageViewSatelite);
        root.getChildren().remove(imageViewMetal);
        root.getChildren().remove(imageViewBullet);
        root.getChildren().remove(imageViewFuel);
    }
}
