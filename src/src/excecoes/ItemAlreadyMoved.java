package excecoes;

public class ItemAlreadyMoved extends invalidMovement{

    @Override
    public String getMessage(){
        return "O item já foi movido";
    }
}
