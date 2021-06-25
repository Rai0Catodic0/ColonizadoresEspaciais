package excecoes;

public class NotEnoughEspace extends UnableTobuild{

    @Override
    public String getMessage(){
        return "Esse Planeta Já tem itens demais";
    }
}
