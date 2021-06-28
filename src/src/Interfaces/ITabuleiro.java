package Interfaces;

public interface ITabuleiro extends IGerarRecurso, IConstruir {
    public Object[] Mover(int idDestino, int idOrigem, String itemMovidoTexto) throws RuntimeException;
}
