package View;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Instruncoes {
    Instruncoes(Group root){
        Text naveCol = new Text("1 metal, 1 combustível");
        naveCol.setFont(Font.font("Verdana", 20));
        naveCol.setFill(Color.WHITE);
        naveCol.setLayoutX(1650);
        naveCol.setLayoutY(230);

        Text naveGuerra = new Text("1 metal, 1 combustível, 1 munição");
        naveGuerra.setFont(Font.font("Verdana", 15));
        naveGuerra.setFill(Color.WHITE);
        naveGuerra.setLayoutX(1650);
        naveGuerra.setLayoutY(280);

        Text Satelite = new Text("2 metais, 1 munição");
        Satelite.setFont(Font.font("Verdana", 20));
        Satelite.setFill(Color.WHITE);
        Satelite.setLayoutX(1650);
        Satelite.setLayoutY(330);

        Text metalPlaneta = new Text("Fornecem metal");
        metalPlaneta.setFont(Font.font("Verdana", 20));
        metalPlaneta.setFill(Color.WHITE);
        metalPlaneta.setLayoutX(1650);
        metalPlaneta.setLayoutY(170);

        Text municaoPlaneta = new Text("Fornecem munição");
        municaoPlaneta.setFont(Font.font("Verdana", 20));
        municaoPlaneta.setFill(Color.WHITE);
        municaoPlaneta.setLayoutX(1650);
        municaoPlaneta.setLayoutY(120);

        Text combustivelPlaneta = new Text("Fornecem combustível");
        combustivelPlaneta.setFont(Font.font("Verdana", 20));
        combustivelPlaneta.setFill(Color.WHITE);
        combustivelPlaneta.setLayoutX(1650);
        combustivelPlaneta.setLayoutY(70);

        Image planetacombustivel0 = new Image("images/planetacombustivel0.png");
        ImageView planetacombustivel0View = new ImageView(planetacombustivel0);
        planetacombustivel0View.setX(1500);
        planetacombustivel0View.setY(20);
        planetacombustivel0View.setScaleX(0.5);
        planetacombustivel0View.setScaleY(0.5);

        Image planetacombustivel1 = new Image("images/planetacombustivel1.png");
        ImageView planetacombustivel1View = new ImageView(planetacombustivel1);
        planetacombustivel1View.setX(1550);
        planetacombustivel1View.setY(20);
        planetacombustivel1View.setScaleX(0.5);
        planetacombustivel1View.setScaleY(0.5);

        Image planetamunicao0 = new Image("images/planetamunicao0.png");
        ImageView planetamunicao0View = new ImageView(planetamunicao0);
        planetamunicao0View.setX(1500);
        planetamunicao0View.setY(70);
        planetamunicao0View.setScaleX(0.5);
        planetamunicao0View.setScaleY(0.5);

        Image planetamunicao1 = new Image("images/planetamunicao1.png");
        ImageView planetamunicao1View = new ImageView(planetamunicao1);
        planetamunicao1View.setX(1550);
        planetamunicao1View.setY(70);
        planetamunicao1View.setScaleX(0.5);
        planetamunicao1View.setScaleY(0.5);

        Image planetametal0 = new Image("images/planetametal0.png");
        ImageView planetametal0View = new ImageView(planetametal0);
        planetametal0View.setX(1500);
        planetametal0View.setY(120);
        planetametal0View.setScaleX(0.5);
        planetametal0View.setScaleY(0.5);

        Image planetametal1 = new Image("images/planetametal1.png");
        ImageView planetametal1View = new ImageView(planetametal1);
        planetametal1View.setX(1550);
        planetametal1View.setY(120);
        planetametal1View.setScaleX(0.5);
        planetametal1View.setScaleY(0.5);

        Image navecolonizadoraa = new Image("images/navecolonizadoraa.png");
        ImageView navecolonizadoraaView = new ImageView(navecolonizadoraa);
        navecolonizadoraaView.setX(1520);
        navecolonizadoraaView.setY(200);

        Image navecolonizadorav = new Image("images/navecolonizadorav.png");
        ImageView navecolonizadoravView = new ImageView(navecolonizadorav);
        navecolonizadoravView.setX(1570);
        navecolonizadoravView.setY(200);

        Image naveguerraa = new Image("images/naveguerraa.png");
        ImageView naveguerraaView = new ImageView(naveguerraa);
        naveguerraaView.setX(1520);
        naveguerraaView.setY(250);

        Image naveguerrav = new Image("images/naveguerrav.png");
        ImageView naveguerravView = new ImageView(naveguerrav);
        naveguerravView.setX(1570);
        naveguerravView.setY(250);

        Image satelitea = new Image("images/satelitea.png");
        ImageView sateliteaView = new ImageView(satelitea);
        sateliteaView.setX(1520);
        sateliteaView.setY(300);

        Image satelitev = new Image("images/satelitev.png");
        ImageView satelitevView = new ImageView(satelitev);
        satelitevView.setX(1570);
        satelitevView.setY(300);

        root.getChildren().add(planetacombustivel0View);
        root.getChildren().add(planetacombustivel1View);
        root.getChildren().add(planetametal0View);
        root.getChildren().add(planetametal1View);
        root.getChildren().add(planetamunicao0View);
        root.getChildren().add(planetamunicao1View);
        root.getChildren().add(metalPlaneta);
        root.getChildren().add(municaoPlaneta);
        root.getChildren().add(combustivelPlaneta);
        root.getChildren().add(navecolonizadoraaView);
        root.getChildren().add(navecolonizadoravView);
        root.getChildren().add(naveguerraaView);
        root.getChildren().add(naveguerravView);
        root.getChildren().add(sateliteaView);
        root.getChildren().add(satelitevView);
        root.getChildren().add(naveCol);
        root.getChildren().add(naveGuerra);
        root.getChildren().add(Satelite);
    }


}
