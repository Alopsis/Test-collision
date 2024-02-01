package Colision;

public class Bot {
    int x;
    int y;
    int accumulateurPourDeplacement;
    int valeurDeRenversement;
    Bot(int x,int y,int valeurDeRenversement){

        this.x = x;
        this.y = y;
        this.valeurDeRenversement = valeurDeRenversement;
      

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
