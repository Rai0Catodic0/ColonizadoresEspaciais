package Tabuleiro;

import Interfaces.IPlaneta;
import Itens.Item;
import Itens.NaveColonizadora;
import Itens.NaveGuerra;
import Itens.Satelite;
import Recursos.Combustivel;
import Recursos.Mineral;
import Recursos.Municao;
import Recursos.Recursos;
import View.Tile;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Planeta  implements IPlaneta {
    private PropertyChangeSupport support;
    int i;
    int j;
    int ipixels;
    int jpixels;
    int id;
    int[] idVizinhos;
    Tile tile;
    public List<Item> itens = new ArrayList<>();
    public String type;
    String imgpath;


    public String getImgpath() {
        return imgpath+".png";
    }

    public int[] getPixelsPosition() {
        return new int[]{ipixels, jpixels};
    }

    public int getI(){
        return  i;
    }

    public int getJ(){
        return j;
    }

    public Planeta(int i, int j, int ipixels, int jpixels, int id, String  type) {
        support = new PropertyChangeSupport(this);
        this.i = i;
        this.j = j;
        this.ipixels = ipixels;
        this.jpixels = jpixels;
        this.id = id;
        //this.idVizinhos = idVizinhos;
        this.type = type;
        int imgNumber = new Random().nextInt(2);
        this.imgpath = "images/planeta"+type+ imgNumber;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }


    @Override
    public void Remover(Item item) {
        List<Item> velhos = new ArrayList<>();
        velhos.addAll(itens);
        this.itens.remove(item);
        support.firePropertyChange("itens",velhos,itens);
    }

    public List<Item> getItens() {
        return itens;
    }

    public boolean MesmaCor(Item item){
        if((itens.size()>0 && itens.get(0).getRepresentacao().equals(item.getRepresentacao()))||(itens.size()==0)){ //planeta vazio ou itens de cores iguais
            return true;
        }
        return false; //itens de cores diferentes
    }

    public boolean EhVazio(){
        if(itens.size()==0){
            return true;
        }
        return false;
    }

    public boolean EhLotado(){
        if(itens.size()==3){
            return true;
        }
        return false;
    }

    public int AvaliarSituacaoIntruso(Item item){
        if(!EhLotado() && MesmaCor(item)){
            //pode inserir
            return 0;
        } else if(!MesmaCor(item)){ //planeta tem itens já e com cores diferentes do intruso
            //haverá luta com luta
            return 1;
        } else {
            return 2; //não pode inserir
        }
    }

    public Object[] Lutar(Item itemIntruso){
        boolean vitoriaIntruso = true;
        for(Item item : itens){
           if(item.lutar()>itemIntruso.lutar()){
               vitoriaIntruso = false;
               break;
           }
        }
        if(!vitoriaIntruso){
            return new Object[] {-1,itemIntruso};
        }else{
            Object resultado[] = new Object[itens.size()+1];
            int i = 1;
            resultado[0] = -2;
            for(Item item : itens){
                resultado[i] = item;
            }
            return resultado;
        }
    }

    public void RemoverTodosItens(){
        itens.clear();
    }

    @Override
    public boolean Inserir(Item item) {
        if(AvaliarSituacaoIntruso(item)==0){
            List<Item> itensAnteriores = new ArrayList<>();
            itensAnteriores.addAll(itens);
            this.itens.add(item);
            support.firePropertyChange("itens",itensAnteriores,itens);
            return true;
        }
        return false;
    }


    public void setTile(Tile tile){
        this.tile = tile;
        tile.setImgpath(this.getImgpath());
        tile.setPositionsPixels(this.getPixelsPosition());
        tile.setItens(this.getItens());
        tile.setPlanetaId(id);
        tile.IniciarTile();
    }


    public void setIdVizinhos(int[] idVizinhos) {
        this.idVizinhos = idVizinhos;
    }

    public boolean  isVizinho(int idVizinho){
        boolean ehVizinho = false;
        for(int i = 0 ; i<this.idVizinhos.length; i++){
            if(idVizinho==this.idVizinhos[i]){
                ehVizinho = true;
                break;
            }
        }
        return ehVizinho;
    }

    public Item Construir(int id, String objeto) {

        Item construtor = null;
        Item construido = null;

        if (objeto.equals("naveGuerra")) {

            construtor = this.hasItem("satelite");
            if (construtor != null) {
                construido = ((Satelite) construtor).ConstruirNave('g');
            } else {
                return null;
            }
            if (this.Inserir(construido)) {
                return construido;
            } else {
                return null;
            }

        } else if (objeto.equals("satelite")) {

            construtor = this.hasItem("naveColonizadora");
            if (construtor != null) {
                construido = ((NaveColonizadora) construtor).ConstruirSatelite();
            } else {
                return null;
            }
            if (this.Inserir(construido)) {
                return construido;
            } else {
                return null;
            }

        } else {
            construtor = this.hasItem("satelite");
            if (construtor != null) {
                construido = ((Satelite) construtor).ConstruirNave('c');
            } else {
                return null;
            }
            if (this.Inserir(construido)) {
                return construido;
            } else {
                return null;
            }
        }
    }

    public <tipo> Item hasItem(Class<?> tipo){
        for(Item item: this.itens){
            if(item.getClass() == tipo){
                return item;
            }
        }
        return null;
    }

    public Item hasItem(String item){
        if(item.equals("naveGuerra")){
            return this.hasItem(NaveGuerra.class);
        } else if (item.equals("naveColonizadora")){
            return this.hasItem(NaveColonizadora.class);
        } else {
            return hasItem(Satelite.class);
        }
    }

    @Override
    public void GerarRecursos() {
                Recursos result = null;
                switch (this.type){
                    case "metal":
                        result = new Mineral();
                        break;
                    case "municao":
                        result = new Municao();
                        break;
                    case "combustivel":
                        result = new Combustivel();
                        break;
                }
                if(this.itens.size() !=0){
                    Item coletor = this.itens.get(0);
                    coletor.recolherRecurso(result);
                }
                support.firePropertyChange("sorteado",false,true);
    }
}
