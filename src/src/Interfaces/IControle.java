package Interfaces;

public interface IControle extends ITrocarVez {
    boolean Mover(int planetaClicado, int planetaRecebeAcao, String objeto);
    int getVez();
    boolean Construir(int id, String objeto);
}
