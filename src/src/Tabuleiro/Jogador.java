package Tabuleiro;

import Itens.Item;
import Itens.NaveColonizadora;
import Itens.NaveGuerra;
import Itens.Satelite;
import Recursos.Combustivel;
import Recursos.Mineral;
import Recursos.Municao;
import Recursos.Recursos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private PropertyChangeSupport support;
    public int pontuacao;
    public List<Item> itens;
    public List<Recursos> recursos;
    public String repre;
    public String nome;
    boolean vez = false;

    public Jogador(String repre){

        support = new PropertyChangeSupport(this);

        this.pontuacao = 0;
        this.repre = repre;
        this.itens = new ArrayList<>();
        this.recursos = new ArrayList<>();
        Recursos metal = new Mineral();
        Recursos municao = new Municao();
        Recursos combustivel = new Combustivel();
        metal.setDono(this.repre);
        municao.setDono(this.repre);
        combustivel.setDono(this.repre);
        this.recursos.add(metal);
        this.recursos.add(municao);
        this.recursos.add(combustivel);

        Recursos metal2 = new Mineral();
        Recursos municao2 = new Municao();
        Recursos combustivel2 = new Combustivel();
        metal2.setDono(this.repre);
        municao2.setDono(this.repre);
        combustivel2.setDono(this.repre);
        this.recursos.add(metal2);
        this.recursos.add(municao2);
        this.recursos.add(combustivel2);
    }

    public void setItens(Item item){
        this.itens.add(item);
    }


        private  void remove(Class tipo){
            for(Recursos r : recursos){
                if(r.getClass() == tipo){
                    break;
                }
            }

        }

    public void RemoverRecurso(String item){
        switch (item){
            case "naveColonizadora":
                this.remove(Mineral.class);
                this.remove(Combustivel.class);
                break;
            case "naveGuerra":
                this.remove(Mineral.class);
                this.remove(Combustivel.class);
                this.remove(Municao.class);
                break;
            case "satelite":
                this.remove(Mineral.class);
                this.remove(Mineral.class);
                this.remove(Municao.class);
        }
    }

    public void setRecursos(){
        for(Recursos recurso : itens.get(0).getColetados()){
            if(recurso.getDono()==this.repre && recurso !=null){
                if(!this.recursos.contains(recurso)){
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
        for(Recursos r : this.recursos){
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
        int statusAnterior[] = status();
        int pontuacaoAnterior = pontuacao;
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
       support.firePropertyChange("status",statusAnterior,status());
       support.firePropertyChange("pontuacao",pontuacaoAnterior, pontuacao);
    }

    public void AdicionarItem(Object[] object){
        int statusAnterior[] = status();
        int pontuacaoAnterior = pontuacao;
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
        support.firePropertyChange("status",statusAnterior,status());
        support.firePropertyChange("pontuacao",pontuacaoAnterior,pontuacao);
    }

    public boolean MoveuTodosItens(){
        int totalItens = 0;
        int itensMovidos = 0;
        for(Item i : itens){
            String s = i.getType();
         if(!(i instanceof Satelite)){
             totalItens++;
             if(i.movido){
                 itensMovidos++;
             }
         }
        }
        if(itensMovidos==totalItens){
            return true;
        } else {
            return false;
        }
    }

    public void ResetarMovimentos(){
        int totalItens = 0;
        int itensMovidos = 0;
        for(Item i : itens){
            if(!(i instanceof Satelite)) {
                i.movido = false;
            }
        }

    }

    public void TrocarVez(){
        vez = !vez;
        support.firePropertyChange("vez",!vez,vez);
    }

    public void IniciarJogador(){
        support.firePropertyChange("iniciar",true,false);
    }

    public void setNome(String nome){
        support.firePropertyChange("nome",this.nome,nome);
        support.firePropertyChange("pontuacao",0,this.pontuacao);
        support.firePropertyChange("status", new int[] {0,0,0,0,0,0},status());
        this.nome = nome;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
}
