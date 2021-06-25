package excecoes;

public class ItemAlreadyMoved extends Exception{

    @Override
    public String getMessage(){
        return "O item já foi movido";
    }
}
