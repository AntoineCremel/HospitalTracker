package vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Contient la classe Fenetre qui sert de base à l'interface graphique.
 */
public class Fenetre extends JFrame{
    // Variables
    private CardLayout c;
    private JPanel contenu;
    private String[] listContenu = {"login","choix","recherche","modification"};
    
    // on crée les panneaux
    private JPanel contenant;
    private JPanel login;
    private JPanel choix;
    private JPanel recherche;
    private JPanel modification;
        
    //On crée les boutons
    private JButton log;
    private JButton modif;
    private JButton requete;
    private JButton graphe;
    
    // Constructeurs
    public Fenetre()
    {
        super();
        setTitle("HospitalTracker");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    
    // on crée les panneaux
        login = new JPanel();
        choix = new JPanel();
        recherche = new JPanel();
        modification = new JPanel();
        
    //On crée les boutons
        log = new JButton("LOGIN");
        modif = new JButton("MODIFIER");
        requete = new JButton("REQUETE");
        graphe = new JButton("GRAPHE");
    
    }
    
    // Methodes
    public void affiche(){
        //on remplit les panneaux
        login.add(log);
        choix.add(modif);
        choix.add(requete);
        choix.add(graphe);
        setContentPane(login);
        setVisible(true);
      
        
    }


public JButton getLog(){
    return log;
}

public JButton getModif(){
    return modif;
}

public JButton getRecherche(){
    return requete;
}

public JButton getGraphe(){
    return graphe;
}

public void nextContenu(int c){
    switch(c)
    {
        case 1:
            setContentPane(choix);
            break;
        case 2:
            setContentPane(recherche);
            break;
        case 3:
            setContentPane(modification);
            break;
        default:
            setContentPane(login);
    }
    revalidate();
}

}
