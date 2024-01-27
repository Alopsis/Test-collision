package Colision;

import java.awt.Rectangle;

public class Cercle {
    double x; // Coordonnée x du centre
    double y; // Coordonnée y du centre
    double rayon; // Rayon du cercle

    // Constructeur
    public Cercle(double x, double y, double rayon) {
        this.x = x;
        this.y = y;
        this.rayon = rayon;
    }

    public boolean intersects(Rectangle rect) {
        double distanceX = Math.abs(this.x - (rect.getX() + rect.getWidth() / 2));
        double distanceY = Math.abs(this.y - (rect.getY() + rect.getHeight() / 2));
        if (distanceX > (rect.getWidth() / 2 + this.rayon)) {
            return false;
        }
        if (distanceY > (rect.getHeight() / 2 + this.rayon)) {
            return false;
        }
        if (distanceX <= (rect.getWidth() / 2) || distanceY <= (rect.getHeight() / 2)) {
            return true;
        }
        double cornerDistanceSquared = Math.pow((distanceX - rect.getWidth() / 2), 2) +
                                      Math.pow((distanceY - rect.getHeight() / 2), 2);
        return cornerDistanceSquared <= Math.pow(this.rayon, 2);
    }
}
