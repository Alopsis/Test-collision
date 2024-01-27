package Colision;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;



public class Jeu extends JPanel implements KeyListener {
    Personnage perso;
    boolean[] keys; 
    ArrayList<Obstacle> listObstacle = new ArrayList<>(); // Objet immobiles
    ArrayList<Vaisseau> listVaisseau = new ArrayList<>(); // Objet qui bouge
    ArrayList<Piece> listPiece = new ArrayList<>(); // Piece;
    static final int GAUCHE = 1;
    static final int DROITE = 2;
    static final int BAS = 3;
    static final int HAUT = 4;
    Jeu() {
        this.setBackground(Color.BLACK);
        perso = new Personnage(250, 250);
        listObstacle.add(new Obstacle(300,300,50,20));
        listObstacle.add(new Obstacle(200,200,20,50));
        listPiece.add(new Piece());
        listPiece.add(new Piece());
        listPiece.add(new Piece());
        keys = new boolean[256];

        Timer envoi = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keys[KeyEvent.VK_Z]) {
                    verificationDeplacement(HAUT);
                }
                if (keys[KeyEvent.VK_D]){
                    verificationDeplacement(DROITE);
                }
                if (keys[KeyEvent.VK_S]){
                    verificationDeplacement(BAS);
                }
                if (keys[KeyEvent.VK_Q]){
                    verificationDeplacement(GAUCHE);
                    
                }
                verificationPiece();
                repaint();
            }
        });
        envoi.start();

        setFocusable(true); 
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawRect(perso.x, perso.y, 10, 10);
        g.setColor(Color.BLUE);
        for(int i = 0 ; i < listObstacle.size();i++){
            g.drawRect(listObstacle.get(i).x, listObstacle.get(i).y, listObstacle.get(i).largeur, listObstacle.get(i).longueur);
        }
        g.setColor(Color.yellow);
        for(int i = 0 ; i < listPiece.size() ; i++){
            g.drawOval(listPiece.get(i).x,listPiece.get(i).y,20,20);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    
    public void verificationPiece() {
        Rectangle rectJoueur = new Rectangle(perso.x, perso.y, 10, 10);
    
        for (int i = 0 ; i < listPiece.size() ; i++) {
            Rectangle rectPiece = new Rectangle(listPiece.get(i).x, listPiece.get(i).y, 20,20);
            if (rectPiece.intersects(rectJoueur)) {
                System.out.println("On mange");
                perso.addPoints(listPiece.get(i).valeur);
                listPiece.remove(i);
                listPiece.add(new Piece());

            }
        }
    }
    
    public void verificationDeplacement(int choix) {
        Rectangle rect1 = new Rectangle(perso.x, perso.y, 10, 10);
    
        for (Obstacle proxi : listObstacle) {
            Rectangle rect2 = new Rectangle();
            if (choix == GAUCHE) {
                rect2 = new Rectangle(proxi.x+1, proxi.y, proxi.largeur, proxi.longueur);
            } else if (choix == DROITE) {
                rect2 = new Rectangle(proxi.x-1, proxi.y, proxi.largeur, proxi.longueur);
            } else if (choix == BAS) {
                rect2 = new Rectangle(proxi.x, proxi.y-1, proxi.largeur, proxi.longueur);
            } else if (choix == HAUT) {
                rect2 = new Rectangle(proxi.x, proxi.y+1, proxi.largeur, proxi.longueur);
            }
            if (rect2.intersects(rect1)) {
                return;
            }
        }
    
        if (choix == GAUCHE) {
            perso.gauche();
        } else if (choix == DROITE) {
            perso.droite();
        } else if (choix == BAS) {
            perso.bas();
        } else if (choix == HAUT) {
            perso.haut();
        }
    }
}
