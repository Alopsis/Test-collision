package Colision;

import javax.swing.JFrame;

public class Fenetre extends JFrame{

    public Fenetre(){
        this.setTitle("Client");
        this.setSize(500, 500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        Jeu jeu = new Jeu();
        jeu.setBounds(0,0,500,500);
        this.add(jeu);
        repaint();

    }
}
