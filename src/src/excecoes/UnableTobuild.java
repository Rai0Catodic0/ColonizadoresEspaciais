package excecoes;

public class UnableTobuild  extends RuntimeException{

    @Override
    public String getMessage(){
        return "Não é possivel construir esse Item";
    }
}
