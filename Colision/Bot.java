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
