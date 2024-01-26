package Colision;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Jeu extends JPanel implements KeyListener {
    Personnage perso;
    Personnage perso2;
    boolean[] keys; 
    List<Personnage> listPersonnage;
    static final int GAUCHE = 1;
    static final int DROITE = 2;
    static final int BAS = 3;
    static final int HAUT = 4;
    Jeu() {
        this.setBackground(Color.BLACK);
        perso = new Personnage(250, 250);
        perso2 = new Personnage(300,300);
        listPersonnage.add(perso2);
        keys = new boolean[256];

        Timer envoi = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keys[KeyEvent.VK_Z]) {
                    verificationDeplacement(HAUT,perso);
                }
                if (keys[KeyEvent.VK_D]){
                    verificationDeplacement(DROITE,perso);
                }
                if (keys[KeyEvent.VK_S]){
                    verificationDeplacement(BAS,perso);
                    
                }
                if (keys[KeyEvent.VK_Q]){
                    verificationDeplacement(GAUCHE,perso);
                    
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
        for (int i = 0 ; i < listPersonnage.size(); i++) {
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

    public Rectangle chercherRectangle(int x,int y, int choix){
        Rectangle target = new Rectangle();

        if(choix == GAUCHE){
            target = new Rectangle(x-10,y,10,10);
        }else if (choix == DROITE){
            target = new Rectangle(x+10,y,10,10);

        }else if (choix == HAUT){
            target = new Rectangle(x,y-10,10,10);

        }else if (choix == BAS){
            target = new Rectangle(x,y+10,10,10);

        }

        if (choix == GAUCHE) {
            if (!(target.contains(perso.x - 1, perso.y)) && !(target.contains(perso.x + 10 - 1, perso.y + 10)) && !(target.contains(perso.x + 10 - 1 , perso.y)) && !(target.contains(perso.x -1 , perso.y + 10))) {
                perso.gauche();
            }
        } else if (choix == DROITE) {
            if (!(target.contains(perso.x + 1, perso.y)) && !(target.contains(perso.x + 10 + 1, perso.y + 10)) && !(target.contains(perso.x+1 , perso.y + 10)) && !(target.contains(perso.x + 10 + 1 ,perso.y ))) {
                perso.droite();
            }
        } else if (choix == BAS) {
            if (!(target.contains(perso.x, perso.y + 1)) && !(target.contains(perso.x + 10, perso.y + 10 + 1)) && !(target.contains(perso.x , perso.y+10+1)) && !(target.contains(perso.x+10,perso.y+1))) {
                perso.bas();
            }
        } else if (choix == HAUT) {
            if (!(target.contains(perso.x, perso.y - 1)) && !(target.contains(perso.x + 10, perso.y + 10 - 1)) && !(target.contains(perso.x,perso.y + 10 - 1 )) && !(target.contains(perso.x + 10 , perso.y -1))) {
                perso.haut();
            }
        }
    }

    public void verificationDeplacement(int choix,Personnage actuel) {
        Rectangle recFocus = new Rectangle(actuel.x, actuel.y, 10, 10);
        Rectangle recTrouve = chercherRectangle(actuel.x, actuel.y,choix);
        Rectangle rect2 = new Rectangle(perso2.x, perso2.y, 10, 10);
    
        if (choix == GAUCHE) {
            if (!(rect2.contains(perso.x - 1, perso.y)) && !(rect2.contains(perso.x + 10 - 1, perso.y + 10)) && !(rect2.contains(perso.x + 10 - 1 , perso.y)) && !(rect2.contains(perso.x -1 , perso.y + 10))) {
                perso.gauche();
            }
        } else if (choix == DROITE) {
            if (!(rect2.contains(perso.x + 1, perso.y)) && !(rect2.contains(perso.x + 10 + 1, perso.y + 10)) && !(rect2.contains(perso.x+1 , perso.y + 10)) && !(rect2.contains(perso.x + 10 + 1 ,perso.y ))) {
                perso.droite();
            }
        } else if (choix == BAS) {
            if (!(rect2.contains(perso.x, perso.y + 1)) && !(rect2.contains(perso.x + 10, perso.y + 10 + 1)) && !(rect2.contains(perso.x , perso.y+10+1)) && !(rect2.contains(perso.x+10,perso.y+1))) {
                perso.bas();
            }
        } else if (choix == HAUT) {
            if (!(rect2.contains(perso.x, perso.y - 1)) && !(rect2.contains(perso.x + 10, perso.y + 10 - 1)) && !(rect2.contains(perso.x,perso.y + 10 - 1 )) && !(rect2.contains(perso.x + 10 , perso.y -1))) {
                perso.haut();
            }
        }
    }
    
}
