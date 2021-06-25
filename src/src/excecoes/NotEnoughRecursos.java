package excecoes;

public class NotEnoughRecursos extends UnableTobuild{

    @Override
    public String getMessage(){
        return "Vocẽ não tem recusos o suficiente pra construir esse item";
    }
}
