package excecoes;

public class UnableTobuild  extends Exception{

    @Override
    public String getMessage(){
        return "Não é possivel construir esse Item";
    }
}
