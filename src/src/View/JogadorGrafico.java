package View;

import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class JogadorGrafico implements PropertyChangeListener {
    private ProgressBar barraPontuacao;
    private Text nome;
    private Text pontos;
    private Group root;
    BarraLateral barraLateral;
    boolean vez = false;

    public JogadorGrafico(Group root, String cor, int x, int y){
        this.barraPontuacao = new ProgressBar(0);
        this.barraPontuacao.setLayoutX(x);
        this.barraPontuacao.setLayoutY(y);
        this.barraPontuacao.setStyle("-fx-accent: "+cor);

        this.pontos = new Text(String.valueOf(0));
        this.pontos.setLayoutX(x+45);
        this.pontos.setLayoutY(y+16);
        this.pontos.setFont(Font.font("Verdana", 18));

        this.nome = new Text();
        this.nome.setLayoutX(x);
        this.nome.setLayoutY(y+35);
        this.nome.setFont(Font.font("Verdana", 18));
        this.nome.setFill(Color.WHITE);

        if(cor.equals("blue")){
            barraLateral = new BarraLateral(root, "a");
        } else {
            barraLateral = new BarraLateral(root, "v");
        }


        this.root = root;
    }

    public void Desenhar(){
        root.getChildren().add(barraPontuacao);
        root.getChildren().add(nome);
        root.getChildren().add(pontos);
    }

    public void update(int pontos){
        double progresso = pontos/12.0;
        this.barraPontuacao.setProgress(progresso);
        this.pontos.setText(String.valueOf(pontos));
    }

    public void TrocarVez(){
        vez = !vez;
        if(vez){
            barraLateral.Desenhar();
        } else {
            barraLateral.Esconder();
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if(propertyChangeEvent.getPropertyName().equals("pontuacao")){
            update((int) propertyChangeEvent.getNewValue());
        } else if (propertyChangeEvent.getPropertyName().equals("status")){
            int status[] = (int[]) propertyChangeEvent.getNewValue();
            int statusVelho[] = (int[]) propertyChangeEvent.getOldValue();
            //System.out.println("velho"+statusVelho[0]+statusVelho[1]+statusVelho[2]+statusVelho[3]+statusVelho[4]+statusVelho[5]);
            //System.out.println("novo"+status[0]+status[1]+status[2]+status[3]+status[4]+status[5]);
            barraLateral.update(status);
        } else if(propertyChangeEvent.getPropertyName().equals("vez")){
            TrocarVez();
        } else if(propertyChangeEvent.getPropertyName().equals("nome")){
            nome.setText((String) propertyChangeEvent.getNewValue());
        } else if(propertyChangeEvent.getPropertyName().equals("iniciar")){
        Desenhar();
    }

    }
}
