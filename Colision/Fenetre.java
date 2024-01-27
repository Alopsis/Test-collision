package Colision;

import javax.swing.Icon;
import javax.swing.JFrame;

public class Fenetre extends JFrame{

    public Fenetre(){
        this.setTitle("Test collision");
        this.setSize(Iconfig.WINDOW_SIZE_HEIGHT, Iconfig.WINDOW_SIZE_WIDTH);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        Jeu jeu = new Jeu();
        jeu.setBounds(0,0,Iconfig.WINDOW_SIZE_HEIGHT,Iconfig.WINDOW_SIZE_WIDTH);
        this.add(jeu);
        repaint();

    }
}
