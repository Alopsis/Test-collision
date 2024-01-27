package Colision;

public class Vaisseau {
    int x;
    int y;
    int largeur;
    int longueur;

    Vaisseau(int x,int y,int largeur,int longueur){
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.longueur = longueur;
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
