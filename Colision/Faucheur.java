package Colision;

public class Faucheur {
    int x1;
    int y1;
    int x2;
    int y2;
    int direction;
    int vitesse;
    static final int GAUCHE = 1;
    static final int DROITE = 2;
    static final int BAS = 3;
    static final int HAUT = 4;

    Faucheur(int x1,int y1,int x2 ,int y2, int direction,int vitesse ){

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.direction = direction;
        this.vitesse = vitesse;
      

    }
    public void gauche(){

        x1 = x1-vitesse;
        x2 = x2-vitesse;
    }
    public void droite(){
        x1 = x1+vitesse;
        x2 = x2+vitesse;
    }    
    public void haut(){
        y1 = y1-vitesse;
        y2 = y2-vitesse;
    }
    public void bas(){
        y1 = y1+vitesse;
        y2 = y2+vitesse;
    }
    public void avancer(){
        if(direction == GAUCHE){
            this.gauche();
        }else if(direction == DROITE){
            this.droite();
        }else if(direction == HAUT){
            this.haut();
        }else {
            this.bas();
        }
    }
}
