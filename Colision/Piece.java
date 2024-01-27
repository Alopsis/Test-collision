package Colision;

public class Piece {
    int x;
    int y;
    int valeur;
    Piece(int x,int y,int valeur){
        this.x = x;
        this.y = y;
        this.valeur = valeur;
    }
    Piece(){
        this.x = (int)(Math.random() * 500);
        this.y = (int)(Math.random() * 500);
        this.valeur = (int)(Math.random() * 20);

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
