package vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Contient la classe Fenetre qui sert de base à l'interface graphique.
 */
public class Fenetre extends JFrame{
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
    
    //les zones de textes
    private JTextField password;
    private JTextField id;
    private JTextField request;
    private JTextField ecrire;
    
    //les textes
    private JLabel demande;
    private JLabel modifier;
    
    //les combos
   private JComboBox choixRequete;
   
   //les Strings
   private String[] tab_choix={"Maaf","choix2","choix3","choix4"};
    
    
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
    
    //On crée les zones de texte
        password = new JTextField("password");
        id = new JTextField("id");
        request = new JTextField();
        ecrire = new JTextField();
    
    //On crée les labels
        demande = new JLabel("REQUETE");
        modifier = new JLabel("MODIFIER");
        
    //On crée les combos box
        choixRequete = new JComboBox(tab_choix);
    }
    
    public Fenetre(String nom){
        super();
        setTitle(nom);
        setSize(400, 400);
        setLocationRelativeTo(null);    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    // Methodes
    public void affiche(){
        //on remplit les panneaux
        password.setPreferredSize(new Dimension(150, 30));
        id.setPreferredSize(new Dimension(150, 30));
        request.setPreferredSize(new Dimension(150, 30));
        ecrire.setPreferredSize(new Dimension(150, 30));
        choixRequete.setPreferredSize(new Dimension(150, 30));
        login.add(id);
        login.add(password);
        login.add(log);
        choix.add(modif);
        choix.add(requete);
        choix.add(graphe);
        recherche.add(demande);
        //recherche.add(request);
        recherche.add(choixRequete);
        modification.add(modifier);
        modification.add(ecrire);
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

public JComboBox getChoixRequete(){
    return choixRequete;
}

//Récuperer l'id entré par l'utilisateur
public String getId(){
    return id.getText();
}

//Récupérer le mot de passe entré par l'utilisateur
public String getPassword(){
    return password.getText();
}

public int getSelectedIndex(){
    return choixRequete.getSelectedIndex();
}


public void nextContenu(int c){//Changer le contenu
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
    revalidate();//Changer de panel
}

}
