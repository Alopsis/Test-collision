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
    ArrayList<Personnage> listPersonnage = new ArrayList<>();
    ArrayList<Bot> listBot = new ArrayList<>();
    ArrayList<Teleporteur> listTp = new ArrayList<>();

    static final int GAUCHE = 1;
    static final int DROITE = 2;
    static final int BAS = 3;
    static final int HAUT = 4;

    Jeu() {
        this.setBackground(Color.BLACK);
        listObstacle.add(new Obstacle(300, 300, 50, 20));
        listObstacle.add(new Obstacle(200, 200, 20, 50));
        listBot.add(new Bot(600, 600,100));
        listPiece.add(new Piece());
        listPiece.add(new Piece());
        listPiece.add(new Piece());
        listPersonnage.add(new Personnage(20, 20));
        listPersonnage.add(new Personnage(100, 250));
        listTp.add(new Teleporteur(250, 750, 750, 250));
        listTp.add(new Teleporteur(250, 250, 750, 750));
        keys = new boolean[256];

        Timer envoi = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keys[KeyEvent.VK_Z]) {
                    verificationDeplacement(listPersonnage.get(1), HAUT);
                }
                if (keys[KeyEvent.VK_D]) {
                    verificationDeplacement(listPersonnage.get(1), DROITE);
                }
                if (keys[KeyEvent.VK_S]) {
                    verificationDeplacement(listPersonnage.get(1), BAS);
                }
                if (keys[KeyEvent.VK_Q]) {
                    verificationDeplacement(listPersonnage.get(1), GAUCHE);

                }
                if (keys[KeyEvent.VK_O]) {
                    verificationDeplacement(listPersonnage.get(0), HAUT);

                }
                if (keys[KeyEvent.VK_M]) {
                    verificationDeplacement(listPersonnage.get(0), DROITE);

                }
                if (keys[KeyEvent.VK_L]) {
                    verificationDeplacement(listPersonnage.get(0), BAS);

                }
                if (keys[KeyEvent.VK_K]) {
                    verificationDeplacement(listPersonnage.get(0), GAUCHE);
                }
                deplacementBot();
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
        g.setColor(Color.BLUE);
        for (int i = 0; i < listObstacle.size(); i++) {
            g.drawRect(listObstacle.get(i).x, listObstacle.get(i).y, listObstacle.get(i).largeur,
                    listObstacle.get(i).longueur);
        }
        g.setColor(Color.yellow);
        for (int i = 0; i < listPiece.size(); i++) {
            g.drawOval(listPiece.get(i).x, listPiece.get(i).y, Iconfig.PIECE_SIZE, Iconfig.PIECE_SIZE);
        }
        g.setColor(Color.green);
        for (int i = 0; i < listPersonnage.size(); i++) {
            g.drawRect(listPersonnage.get(i).x, listPersonnage.get(i).y, 10, 10);
        }
        g.setColor(Color.GRAY);
        for (int i = 0; i < listBot.size(); i++) {
            g.drawRect(listBot.get(i).x, listBot.get(i).y, 10, 10);
        }
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < listTp.size(); i++) {
            g.drawRect(listTp.get(i).x1, listTp.get(i).y1, 10, 10);
            g.drawRect(listTp.get(i).x2, listTp.get(i).y2, 10, 10);
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

    public void verificationPiece(Personnage perso) {
        Rectangle rectJoueur = new Rectangle(perso.x, perso.y, 10, 10);

        for (int i = 0; i < listPiece.size(); i++) {
            Rectangle rectPiece = new Rectangle(listPiece.get(i).x, listPiece.get(i).y, Iconfig.PIECE_SIZE,
                    Iconfig.PIECE_SIZE);
            if (rectPiece.intersects(rectJoueur)) {
                perso.addPoints(listPiece.get(i).valeur);
                listPiece.remove(i);
                listPiece.add(new Piece());

            }
        }
    }

    public void deplacementBot() {
        for (int i = 0; i < listBot.size(); i++) {
            // ICI VERIFIER LES OBSTACLES
            Rectangle rectBot = new Rectangle(listBot.get(i).x, listBot.get(i).y, 10, 10);

            for (int j = 0; j < listPersonnage.size(); j++) {
                if(listPersonnage.get(j) != null){
                    Rectangle rectJoueur = new Rectangle(listPersonnage.get(j).x, listPersonnage.get(j).y, 10, 10);
                    if (rectBot.intersects(rectJoueur)) {
                        listPersonnage.remove(listPersonnage.get(j));
                    }
                    if (listBot.get(i).accumulateurPourDeplacement % listBot.get(i).valeurDeRenversement < listBot.get(i).valeurDeRenversement/2) {
                        listBot.get(i).gauche();
                    }else{
                        listBot.get(i).droite();
                    }
                }

            }
            listBot.get(i).accumulateurPourDeplacement++;
        }
    }

    public void verificationDeplacement(Personnage perso, int choix) {
        Rectangle rect1 = new Rectangle(perso.x, perso.y, 10, 10);
        for (Obstacle proxi : listObstacle) {
            Rectangle rect2 = new Rectangle();
            if (choix == GAUCHE) {
                rect2 = new Rectangle(proxi.x + Iconfig.VALEUR_DEPLACEMENT, proxi.y, proxi.largeur, proxi.longueur);
            } else if (choix == DROITE) {
                rect2 = new Rectangle(proxi.x - Iconfig.VALEUR_DEPLACEMENT, proxi.y, proxi.largeur, proxi.longueur);
            } else if (choix == BAS) {
                rect2 = new Rectangle(proxi.x, proxi.y - Iconfig.VALEUR_DEPLACEMENT, proxi.largeur, proxi.longueur);
            } else if (choix == HAUT) {
                rect2 = new Rectangle(proxi.x, proxi.y + Iconfig.VALEUR_DEPLACEMENT, proxi.largeur, proxi.longueur);
            }
            if (rect2.intersects(rect1)) {
                return;
            }
        }
        for (Personnage proxi : listPersonnage) {
            if(!proxi.equals(perso)){

            
            Rectangle rect2 = new Rectangle();
            if (choix == GAUCHE) {
                rect2 = new Rectangle(proxi.x + Iconfig.VALEUR_DEPLACEMENT, proxi.y, 10, 10);
            } else if (choix == DROITE) {
                rect2 = new Rectangle(proxi.x - Iconfig.VALEUR_DEPLACEMENT, proxi.y, 10, 10);
            } else if (choix == BAS) {
                rect2 = new Rectangle(proxi.x, proxi.y - Iconfig.VALEUR_DEPLACEMENT, 10, 10);
            } else if (choix == HAUT) {
                rect2 = new Rectangle(proxi.x, proxi.y + Iconfig.VALEUR_DEPLACEMENT, 10, 10);
            }
            if (rect2.intersects(rect1)) {
                if (deplacementPossible(proxi, choix)) {
                    deplacerPersonnage(proxi, choix);
                }
                return;
            }
        }
        }
        for (Teleporteur proxi : listTp) {
            Rectangle rect2 = new Rectangle();
            if (choix == GAUCHE) {
                rect2 = new Rectangle(proxi.x1 + Iconfig.VALEUR_DEPLACEMENT, proxi.y1, 10, 10);
            } else if (choix == DROITE) {
                rect2 = new Rectangle(proxi.x1 - Iconfig.VALEUR_DEPLACEMENT, proxi.y1, 10, 10);
            } else if (choix == BAS) {
                rect2 = new Rectangle(proxi.x1, proxi.y1 - Iconfig.VALEUR_DEPLACEMENT, 10, 10);
            } else if (choix == HAUT) {
                rect2 = new Rectangle(proxi.x1, proxi.y1 + Iconfig.VALEUR_DEPLACEMENT, 10, 10);
            }
            if (rect2.intersects(rect1)) {
                if(perso.canBeTp == true){
                    Timer envoi = new Timer(3000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            perso.canBeTp = true;
                            ((Timer) e.getSource()).stop();

                        }
                    });
                    perso.canBeTp = false;
                    envoi.start();
                    perso.x = proxi.x2;
                    perso.y = proxi.y2;
                    return;
                }

            }
            if (choix == GAUCHE) {
                rect2 = new Rectangle(proxi.x2 + Iconfig.VALEUR_DEPLACEMENT, proxi.y2, 10, 10);
            } else if (choix == DROITE) {
                rect2 = new Rectangle(proxi.x2 - Iconfig.VALEUR_DEPLACEMENT, proxi.y2, 10, 10);
            } else if (choix == BAS) {
                rect2 = new Rectangle(proxi.x2, proxi.y2 - Iconfig.VALEUR_DEPLACEMENT, 10, 10);
            } else if (choix == HAUT) {
                rect2 = new Rectangle(proxi.x2, proxi.y2 + Iconfig.VALEUR_DEPLACEMENT, 10, 10);
            }
            if (rect2.intersects(rect1)) {
                if(perso.canBeTp == true){
                    Timer envoi = new Timer(3000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            perso.canBeTp = true;
                            ((Timer) e.getSource()).stop();
                        }
                    });
                    perso.canBeTp = false;
                    envoi.start();
                    perso.x = proxi.x1;
                    perso.y = proxi.y1;
                    return;
                }

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
        verificationPiece(perso);

    }

    private boolean deplacementPossible(Personnage personnage, int direction) {
        int newX = personnage.x;
        int newY = personnage.y;

        Rectangle rectJoueur = new Rectangle(newX, newY, 10, 10);
        for(Personnage proxi : listPersonnage){
            if(proxi.equals(personnage) == false){
                Rectangle rect2 = new Rectangle();
                if (direction == GAUCHE) {
                    rect2 = new Rectangle(proxi.x + Iconfig.VALEUR_DEPLACEMENT, proxi.y, 10, 10);
                } else if (direction == DROITE) {
                    rect2 = new Rectangle(proxi.x - Iconfig.VALEUR_DEPLACEMENT, proxi.y, 10, 10);
                } else if (direction == BAS) {
                    rect2 = new Rectangle(proxi.x, proxi.y - Iconfig.VALEUR_DEPLACEMENT, 10, 10);
                } else if (direction == HAUT) {
                    rect2 = new Rectangle(proxi.x, proxi.y + Iconfig.VALEUR_DEPLACEMENT, 10, 10);
                }
                if (rect2.intersects(rectJoueur)) {
                    if (deplacementPossible(proxi, direction)) {
                        deplacerPersonnage(proxi, direction);
                    }
                    return false;
                }
            }

        }
        if (direction == GAUCHE) {
            newX++;
        } else if (direction == DROITE) {
            newX--;
        } else if (direction == HAUT) {
            newY++;
        } else if (direction == BAS) {
            newY--;
        }
        rectJoueur = new Rectangle(newX, newY, 10, 10);
        for (Obstacle proxi : listObstacle) {
            
            Rectangle rect2 = new Rectangle();
            if (direction == GAUCHE) {
                rect2 = new Rectangle(proxi.x + Iconfig.VALEUR_DEPLACEMENT, proxi.y, 10, 10);
            } else if (direction == DROITE) {
                rect2 = new Rectangle(proxi.x - Iconfig.VALEUR_DEPLACEMENT, proxi.y, 10, 10);
            } else if (direction == BAS) {
                rect2 = new Rectangle(proxi.x, proxi.y - Iconfig.VALEUR_DEPLACEMENT, 10, 10);
            } else if (direction == HAUT) {
                rect2 = new Rectangle(proxi.x, proxi.y + Iconfig.VALEUR_DEPLACEMENT, 10, 10);
            }
            if (rect2.intersects(rectJoueur)) {
                return false;
            }
        }

        return true; // À remplacer avec la logique réelle
    }

    private void deplacerPersonnage(Personnage personnage, int direction) {
        if (direction == GAUCHE) {
            personnage.gauche();
        } else if (direction == DROITE) {
            personnage.droite();
        } else if (direction == BAS) {
            personnage.bas();
        } else if (direction == HAUT) {
            personnage.haut();
        }
        verificationPiece(personnage);

    }
}
