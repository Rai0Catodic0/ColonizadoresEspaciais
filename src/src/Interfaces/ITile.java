package Interfaces;

import Itens.Item;

import java.util.List;

public interface ITile {
    void IniciarTile();
    void update();
    void setPlanetaId(int i);
    void setImgpath(String imgpath);
    void setItens(List<Item> items);
    void setPositionsPixels(int[] positions);
}
