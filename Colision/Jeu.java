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
    ArrayList<Personnage> listPersonnage = new ArrayList<>();

    static final int GAUCHE = 1;
    static final int DROITE = 2;
    static final int BAS = 3;
    static final int HAUT = 4;
    Jeu() {
        this.setBackground(Color.BLACK);
        perso = new Personnage(250, 250);
        listPersonnage.add(new Personnage(300,300));
        listPersonnage.add(new Personnage(200,200));
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
        for(int i = 0 ; i < listPersonnage.size();i++){
            g.drawRect(listPersonnage.get(i).x, listPersonnage.get(i).y, 10, 10);

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
    
    public ArrayList<Personnage> rectangleAProximite(int choix){
        ArrayList<Personnage> listProxi = new ArrayList<>();

        for(int i = 0 ; i < listPersonnage.size();i++){
            Rectangle courant = new Rectangle(listPersonnage.get(i).x,listPersonnage.get(i).y,10,10);
            if (choix == GAUCHE) {
                if ((courant.contains(perso.x - 1, perso.y)) && (courant.contains(perso.x + 10 - 1, perso.y + 10)) && (courant.contains(perso.x + 10 - 1 , perso.y)) && (courant.contains(perso.x -1 , perso.y + 10))) {
                    listProxi.add(listPersonnage.get(i));
                }
            } else if (choix == DROITE) {
                if ((courant.contains(perso.x + 1, perso.y)) && (courant.contains(perso.x + 10 + 1, perso.y + 10)) && (courant.contains(perso.x+1 , perso.y + 10)) && (courant.contains(perso.x + 10 + 1 ,perso.y ))) {
                    listProxi.add(listPersonnage.get(i));
                }
            } else if (choix == BAS) {
                if ((courant.contains(perso.x, perso.y + 1)) && (courant.contains(perso.x + 10, perso.y + 10 + 1)) && (courant.contains(perso.x , perso.y+10+1)) && (courant.contains(perso.x+10,perso.y+1))) {
                    listProxi.add(listPersonnage.get(i));
                }
            } else if (choix == HAUT) {
                if ((courant.contains(perso.x, perso.y - 1)) && (courant.contains(perso.x + 10, perso.y + 10 - 1)) && (courant.contains(perso.x,perso.y + 10 - 1 )) && (courant.contains(perso.x + 10 , perso.y -1))) {
                    listProxi.add(listPersonnage.get(i));
                }
            }
        }



        return listProxi;
    }
    public void verificationDeplacement(int choix) {
        Rectangle rect1 = new Rectangle(perso.x, perso.y, 10, 10);
    
        for (Personnage proxi : listPersonnage) {
            Rectangle rect2 = new Rectangle();
            if (choix == GAUCHE) {
                rect2 = new Rectangle(proxi.x+1, proxi.y, 10, 10);
            } else if (choix == DROITE) {
                rect2 = new Rectangle(proxi.x-1, proxi.y, 10, 10);
            } else if (choix == BAS) {
                rect2 = new Rectangle(proxi.x, proxi.y-1, 10, 10);
            } else if (choix == HAUT) {
                rect2 = new Rectangle(proxi.x, proxi.y+1, 10, 10);
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
