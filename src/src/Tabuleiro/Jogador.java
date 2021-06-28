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
    int[] saida;

    public Jogador(String repre){

        support = new PropertyChangeSupport(this);

        this.pontuacao = 4;
        this.repre = repre;
        this.itens = new ArrayList<>();
        this.recursos = new ArrayList<>();
        Recursos a = new Municao();
        Recursos b = new Mineral();
        Recursos c = new Combustivel();
        Recursos d = new Mineral();
        a.setDono(this.repre);
        b.setDono(this.repre);
        c.setDono(this.repre);
        d.setDono(this.repre);
        this.recursos.add(a);
        this.recursos.add(b);
        this.recursos.add(c);
        this.recursos.add(d);
    }

    public void setItens(Item item){
        this.itens.add(item);
    }


        private  void remove(Class tipo){
            for(Item i : itens) {
                for (Recursos r : recursos) {
                    if (r.getClass() == tipo) {
                        recursos.remove(r);
                        i.getColetados().remove(r);
                        break;
                    }
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
                System.out.println("GASTOU");
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
        for(Item i: itens) {
            for (Recursos recurso : i.getColetados()) {
                if (recurso.getDono() == this.repre && recurso != null) {
                    if (!this.recursos.contains(recurso)) {
                        this.recursos.add(recurso);
                        //System.out.println(recurso+"JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
                    }
                }
            }
        }
     /*   for(Recursos r : recursos){
            System.out.println(this.repre+" tem "+r.getClass());
        }*/
    }

        public void updateRecursos(){
            support.firePropertyChange("status",new int[] {100,100,100,100,100,100}, status());

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
             System.out.println("aqui vamos ver o status do item movido: "+i.movido);
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
