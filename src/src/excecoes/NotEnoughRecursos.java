package excecoes;

public class NotEnoughRecursos extends UnableTobuild{

    String msg;
    public NotEnoughRecursos(String tipoItem){
        msg = "Vocẽ não tem recursos o suficiente para construir "+tipoItem;
    }
    @Override
    public String getMessage(){
        return msg;
    }
}
