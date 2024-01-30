package Colision;

public class Personnage {
    int x;
    int y;
    int nombrePoints = 0;
    boolean canBeTp = true;
    Personnage(int x,int y){
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
    public void addPoints(int pts){
        this.nombrePoints += pts;
    }
}
