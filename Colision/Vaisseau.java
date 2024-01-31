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
}
