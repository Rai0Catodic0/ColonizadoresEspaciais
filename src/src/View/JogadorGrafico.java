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

        this.root = root;
    }

    public void Desenhar(String nome){
        this.nome.setText(nome);
        root.getChildren().add(barraPontuacao);
        root.getChildren().add(this.nome);
        root.getChildren().add(pontos);
    }

    public void update(int pontos){
        double progresso = pontos/12.0;
        this.barraPontuacao.setProgress(progresso);
        this.pontos.setText(String.valueOf(pontos));
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        update((int) propertyChangeEvent.getNewValue());
    }
}
