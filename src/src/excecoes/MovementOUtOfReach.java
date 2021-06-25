package excecoes;

public class MovementOUtOfReach extends invalidMovement{

    @Override
    public String getMessage(){
        return "O planeta está fora de alcance ";
    }
}
