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

        this.x = (int)(Math.random() * Iconfig.WINDOW_SIZE_HEIGHT - Iconfig.PIECE_SIZE);
        this.y = (int)(Math.random() * Iconfig.WINDOW_SIZE_WIDTH - Iconfig.PIECE_SIZE);
        this.valeur = (int)(Math.random() * 20);
        System.out.println("Piece crée ! " + this.x + ","+this.y);

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
