package Recursos;

public  class Recursos {
    //descobrir oq fazer com isso :)
    String dono;
    public  Recursos(){
    }
    public String getType(){
        if(this instanceof Combustivel){
            return "combustivel";
        }
        else if(this instanceof  Mineral){
            return "Mineral";
        }
        return "Municao";
    }

    public void  setDono(String dono){
        this.dono = dono;
    }
    public String getDono(){
        return this.dono;
    }

}
