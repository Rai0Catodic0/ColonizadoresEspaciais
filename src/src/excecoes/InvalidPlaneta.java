package excecoes;

public class InvalidPlaneta extends  InvalidImgPath{

    public InvalidPlaneta(String msg) {
        super(msg);
    }

    @Override
    public String getMessage(){
        return "o icone do planeta não existe";
    }
}
