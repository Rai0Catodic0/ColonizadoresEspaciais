package excecoes;

public class InvalidImgPath extends RuntimeException{

    public InvalidImgPath(String msg) {
        super(msg);
    }

    @Override
    public String getMessage(){
        return "esse arquivo não existe";
    }
}
