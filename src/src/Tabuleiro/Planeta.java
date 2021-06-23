package Tabuleiro;

import Itens.Item;
import Itens.NaveColonizadora;
import Itens.NaveGuerra;
import Itens.Satelite;
import Recursos.*;
import View.Tile;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Planeta  implements IPlaneta {
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
        this.i = i;
        this.j = j;
        this.ipixels = ipixels;
        this.jpixels = jpixels;
        this.id = id;
        //this.idVizinhos = idVizinhos;
        this.type = type;
        int imgNumber = new Random().nextInt(2);
        this.imgpath = "images/planeta"+type+ 1;
    }

    @Override
    public void Remover(Item item) {
        this.itens.remove(item);
        if(this.tile!=null){
            this.tile.update(this.itens);
        }
    }

    public List<Item> getItens() {
        return itens;
    }


    @Override
    public void Inserir(Item item) {
        itens.add(item);
        if(this.tile!=null){
            this.tile.update(this.itens);
        }
    }

    public void Inserir(String itemInseridoString, Item itemInserido) {
        Inserir(itemInserido);
        System.out.println("INSERIR "+id);
    }

    public Item Remover(String itemRemovido) {
        //FIXME so funciona com nave de guerra ??
        System.out.println("Essa é a lista do planeta " + this +"antes de remover :"+itens);
        Item itemRemoverI = null;
        if(itemRemovido.equals("naveColonizadora")){
            for(Item item : itens){
                //System.out.println("nave colonizadora");
                if(item instanceof NaveColonizadora){
                    itemRemoverI = item;
                    break;
                }
            }
        }else if(itemRemovido.equals("naveGuerra")){
            for(Item item : itens){
                if(item instanceof NaveGuerra){
                    //System.out.println("nave guera");
                    itemRemoverI = item;
                    break;
                }
            }
        }else{
            System.out.println("removendo");
            for(Item item : itens){
                if(item instanceof  Satelite){
                    itemRemoverI = item;
                    break;
                }
            }
        }
        Remover(itemRemoverI);
        System.out.println("Essa é a lista do planeta " + this +"depois de remover :"+itens);
        return itemRemoverI;
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
            default:
                result  = new Recursos();
        }
        if(this.itens.size() !=0){
            Item coletor = this.itens.get(0);
            coletor.recolherRecurso(result);
        }
    }


    public void setTile(Tile tile){
        this.tile = tile;
        tile.setImgpath(this.getImgpath());
        tile.setPositionsPixels(this.getPixelsPosition());
        tile.setItems(this.getItens());
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

    public Item Construir(String objeto) {
        Item construtor = null;
        Item construido = null;
        if (objeto.equals("naveGuerra")) {
            for (Item item : itens) {
                if (item instanceof Satelite) {
                    construtor = item;
                    break;
                }
            }
            construido = ((Satelite) construtor).ConstruirNave('g');
            this.Inserir(construido);
        } else if (objeto.equals("satelite")) {
            for (Item item : itens) {
                if (item instanceof NaveColonizadora) {
                    construtor = item;
                    break;
                }
            }
            construido = ((NaveColonizadora) construtor).Construir('g');
            this.Inserir(construido);
        } else {
            for (Item item : itens) {
                if (item instanceof Satelite) {
                    construtor = item;
                    break;
                }
            }
            construido = ((Satelite) construtor).ConstruirNave('c');
            this.Inserir(construido);
        }

        return construido;
    }

    public <tipo> Item hasItem(Class<?> tipo){
        for(Item item: this.itens){
            if(item.getClass() == tipo){
                return item;
            }
        }
        return null;
    }

}
