package Tabuleiro;

import Itens.Item;
import Itens.NaveColonizadora;
import Itens.NaveGuerra;
import Recursos.Recursos;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Jogador {
    public int pontuacao;
    public List<Item> itens;
    public List<Recursos> recursos;
    public String repre;

    public Jogador(String repre){
        this.pontuacao = 0;
        this.repre = repre;
        this.itens = new ArrayList<>();
        this.recursos = new ArrayList<>();
    }

    public void setItens(Item nave, Item satelite){
        this.itens.add(nave);
        this.itens.add(satelite);
    }

//    @Override
//    public void update(Observable observable, Object o) {
//        Item item = (Item) o;
//        this.itens.add(item);
//        if(item instanceof NaveColonizadora){
//            this.pontuacao +=1;
//        }
//        else if(item instanceof NaveGuerra){
//            this.pontuacao += 2;
//        }
//        else{
//            this.pontuacao +=3;
//        }
//    }

    public void RemoverRecurso(){

    }
    public void setRecursos(){
        for(Recursos recurso : itens.get(0).getColetados()){
            if(recurso.getDono()==this.repre && recurso !=null){
                if(!this.recursos.contains(recurso)){
                    System.out.println("recurso "+this.repre+  " :"+recurso);
                    this.recursos.add(recurso);
                }
            }
        }
    }
    public int[] status(){
        //metal,municao,combustivel,satelite,guerra,colonizacao
        setRecursos();
        int[] saida = new int[6];
        Item item = itens.get(0);
        for(Recursos r : item.getColetados()){
            String tipo = r.getType();
            switch (tipo){
                case "Mineral":
                    saida[0]++;
                    break;
                case "Municao":
                    saida[1]++;
                    break;
                case "combustivel":
                    saida[2]++;
                default:
                    break;
            }
        }
        for(Item i : itens){
            String s = i.getType();
            switch (s){
                case "satelite":
                    saida[3]++;
                    break;
                case "naveguerra":
                    saida[4]++;
                    break;
                case "navecolonizadora":
                    saida[5]++;
                    break;
            }
        }
        return  saida;
    }

    public int getQtdItens(){
        return itens.size();
    }

    public int getPontuacao(){
        return this.pontuacao;
    }

    public void ExcluirItem(Object[] object){
       for(int i = 1; i<object.length;i++){
           itens.remove(object[i]);
           if(object[i] instanceof NaveColonizadora){
               pontuacao-=1;
           } else if(object[i] instanceof NaveGuerra){
               pontuacao-=2;
           } else {
               pontuacao-=3;
           }
       }
    }

    public void AdicionarItem(Object[] object){
        for(int i = 1; i<object.length;i++){
            Item item = (Item) object[i];
            itens.add(item);
            if(object[i] instanceof NaveColonizadora){
                pontuacao+=1;
            } else if(object[i] instanceof NaveGuerra){
                pontuacao+=2;
            } else {
                pontuacao+=3;
            }
        }
    }
}
