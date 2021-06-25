package excecoes;

public class InvalidPlaneta extends  InvalidImgPath{

    String msg;
    public InvalidPlaneta(String msg) {
        super(msg);
        this.msg = "o icone do planeta não foi encontrado, o seguinte caminho foi fornecido :"+msg;
    }

    @Override
    public String getMessage(){
        return msg;
    }
}
