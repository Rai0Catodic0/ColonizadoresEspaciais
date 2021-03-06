package Itens;
import Interfaces.IItem;
import Recursos.Recursos;
import Tabuleiro.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Item implements IItem {
    public int i;
    public int j;
    public boolean movido;

    List<Recursos> coletados = new ArrayList<Recursos>();
    public String representacao;
    Tabuleiro tabuleiro;

    public Item(int i, int j, String repre, Tabuleiro tab){

        //repre dever ser ou a ou v
        //a = jogador azul
        //v = jogador verde
        this.tabuleiro = tab;
        this.i = i;
        this.j = j;
        this.representacao = repre;
        movido = false;
    }

    // acoes do jogo
    @Override
    public int lutar() {
        Random gerador = new Random();
        return gerador.ints(1,0,6).toArray()[0];
    }

    public void recolherRecurso(Recursos coletado) {
        coletado.setDono(this.representacao);
        coletados.add(coletado);
    }

    // getters e setters
    @Override
    public List<Integer> getPosition() {
        List<Integer> position = new ArrayList<>();
        position.add(this.i);
        position.add(this.j);
        return  position;
    }

    public List<Recursos> getColetados() {
        return coletados;
    }

    public String getRepresentacao(){
        return representacao;
    }

    public String getType() {
        /**
         *  retorna o tipo do item em forma de string tudo junto e minúsculo
         */
        String type;
        if(this instanceof NaveGuerra){
            type = "naveguerra";
        }else if(this instanceof NaveColonizadora){
             type = "navecolonizadora";
        }
        else {
            type = "satelite";
        }
        return type;
    }

    public String getImgPath(){
        /**
         *  retorna o cominho que contem a imagem pra esse item
         */
        String imgPath;
        imgPath = "images/"+this.getType()+this.representacao+".png";
        return imgPath;
    }
}
