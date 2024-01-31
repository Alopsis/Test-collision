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

        x = x-Iconfig.VALEUR_DEPLACEMENT;
    }
    public void droite(){
        x = x+Iconfig.VALEUR_DEPLACEMENT;
    }    
    public void haut(){
        y = y-Iconfig.VALEUR_DEPLACEMENT;
    }
    public void bas(){
        y = y+Iconfig.VALEUR_DEPLACEMENT;
    }
    public void addPoints(int pts){
        this.nombrePoints += pts;
    }
}
