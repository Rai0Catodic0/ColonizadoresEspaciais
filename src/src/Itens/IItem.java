package Itens;

import Recursos.Recursos;

import java.util.ArrayList;
import java.util.List;

public interface IItem {
    public static List<Recursos> coletados = new ArrayList<Recursos>();
    // acoes de jogo
    int lutar();
    void  recolherRecurso(Recursos coletado);
   // getters e setters
    List<Integer> getPosition();
    List<Recursos> getColetados();
    String getType();
    String getImgPath();
}
