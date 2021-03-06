package View;

import Interfaces.ITile;
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

public class Tile extends Pane implements PropertyChangeListener, ITile {

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

    public void IniciarTile(){
        try{
            botao = new BotaoPlaneta(imgpath, positions);
        }catch (RuntimeException e){
            return;
        }
        botao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                barraSelecao.ClicouPlaneta(id, items);
                showHighLight("images/highlightvermelho.png", 37, 30);
            }
        });

        this.getChildren().add(botao);
        try {
            this.Desenhar();
        }catch (Exception e){
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
        int i = 0;
        int [] positionsX = {0,100,0};
        int [] positionsY = {0,100,100};
        for(Item item : this.items){
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

    public void update(){
        Esconder();
        try {
            this.Desenhar();
        }catch (RuntimeException e){
        }

    }

    // setrs e getrs
    public void setPlanetaId(int i){
        this.id = i;
    }

    public void setImgpath(String imgpath){
        this.imgpath = imgpath;
    }

    public void setItens(List<Item> items){
        this.items = items;
    }

    public void setPositionsPixels(int[] positions){
        this.positions = positions;
        this.setLayoutX(positions[1]);
        this.setLayoutY(positions[0]);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if(propertyChangeEvent.getPropertyName().equals("sorteado")){
            this.showHighLight("images/highlight.png", 12, 10);
        } else if(propertyChangeEvent.getPropertyName().equals("itens")){
            setItens((List<Item>) propertyChangeEvent.getNewValue());
            update();
        }
    }
    public void showHighLight(String highLightPath, int x, int y){
        ImageView fundo = new ImageView(new Image(highLightPath));
        fundo.setX(x);
        fundo.setY(y);
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
