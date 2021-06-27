package View;

import Itens.Item;
import excecoes.InvalidItemIcon;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Tile extends Pane implements PropertyChangeListener {

    List<Item> items = new ArrayList<>();
    BotaoPlaneta botao;
    ImageView images[];
    String imgpath;
    int positions[];
    int id;
    BarraSelecao barraSelecao;

    public Tile(BarraSelecao barraSelecao) {
        super();
        this.resize(100,100);
        images = new ImageView[3];
        this.barraSelecao = barraSelecao;
    }

    //TODO classe separada pra luta
    // tudo bem classe meio inuteis
    // so uma interface tá show
    public void IniciarTile(){
        try{
            botao = new BotaoPlaneta(imgpath, positions);
        }catch (RuntimeException e){
            //System.out.println("DEU RUIM "+e.getMessage());
            return;
        }
        botao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //System.out.println("NESSE PLANETA TEMOS: "+items);
                System.out.println("o id do planeta é :"+id);
                barraSelecao.ClicouPlaneta(id, items);
            }
        });

        this.getChildren().add(botao);
        try {
            this.Desenhar();
        }catch (Exception e){
            //System.out.println("DEU RUIM"+e.getMessage());
        }
    }

    // metodos pra modificar o tile
    public void Esconder(){

        for(int i = 0; i<3;i++){
            this.getChildren().remove(images[i]);
            images[i] = null;

        }
    }

    public void Desenhar() throws RuntimeException{
        //System.out.println("Esse é a lista de itens q vou desenhra: "+items+" no planeta "+this.id);
        int i = 0;
        int [] positionsX = {0,100,0};
        int [] positionsY = {0,100,100};
        for(Item item : this.items){
            //System.out.println("61"+item);
            //System.out.println(item.getImgPath().contains("imags"));
            try {
                images[i] = new ImageView(new Image(item.getImgPath()));
            } catch (Exception e) {
                throw new InvalidItemIcon(item.getType(),item.getImgPath());
            }
            images[i].setX(positionsX[i]);
            images[i].setY(positionsY[i]);
            this.getChildren().add(images[i]);
            i++;
        }
    }

    public void update(List<Item> itens){
        Esconder();
        this.items = itens;

        try {
            this.Desenhar();
        }catch (RuntimeException e){
            //System.out.println("DEU RUIM"+e.getMessage());
        }

    }

    // setrs e getrs
    public void setPlanetaId(int i){
        this.id = i;
    }

    public void setImgpath(String imgpath){
        this.imgpath = imgpath;
    }

    public void setItems(List<Item> items){
        this.items = items;
    }

    public void setPositionsPixels(int[] positions){
        this.positions = positions;
        this.setLayoutX(positions[1]);
        this.setLayoutY(positions[0]);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
       ////System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+propertyChangeEvent);
        if(propertyChangeEvent.getPropertyName().equals("sorteado")){
            ImageView fundo = new ImageView(new Image("images/highlight.png"));
            fundo.setX(12);
            fundo.setY(10);
            this.getChildren().add(fundo);
            fundo.toBack();
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(2000);

                    }catch (InterruptedException e){

                    }return null;
                }
            };
            sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent workerStateEvent) {
                    getChildren().remove(fundo);
                }
            });
            new Thread(sleeper).start();

        }
    }
}
