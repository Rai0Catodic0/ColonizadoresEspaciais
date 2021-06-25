package excecoes;

public class InvalidItemIcon extends  InvalidImgPath{


    private final String msg;

    public InvalidItemIcon(String msg, String path){
        super(msg);
        this.msg = "o icone para o item: "+msg+"nao existe, o caminho fornecido foi :"+path;
    }
    @Override
    public String getMessage(){
        return this.msg;
    }
}
