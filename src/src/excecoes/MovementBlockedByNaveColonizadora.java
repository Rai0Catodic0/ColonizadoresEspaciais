package excecoes;

public class MovementBlockedByNaveColonizadora extends invalidMovement{

    @Override
    public String getMessage(){
        return "esse movimento não pode ser executado, o planeta já esta ocupado";
    }
}
