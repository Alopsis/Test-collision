package Colision;

public class Bot {
    int x;
    int y;
    int accumulateurPourDeplacement;

    Bot(int x,int y){

        this.x = x;
        this.y = y;
      

    }
    public void gauche(){

        x = x-1;
    }
    public void droite(){
        x = x+1;
    }    
    public void haut(){
        y = y-1;
    }
    public void bas(){
        y = y+1;
    }
}
