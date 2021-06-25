package excecoes;

public class invalidMovement extends RuntimeException{

    public  String getMessage(){
        return "movimento invalido";
    }
}
