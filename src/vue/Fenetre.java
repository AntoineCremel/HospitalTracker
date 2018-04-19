package vue;

import controler.LectureBDD;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import modele.Connexion;

/**
 * Contient la classe Fenetre qui sert de base à l'interface graphique.
 */
public class Fenetre extends JFrame implements ActionListener{
    // on crée les panneaux
    private Connexion connex;
    
    private LectureBDD a;
    
    private JPanel bienvenue;
    private JPanel supprimer;
    private JPanel choix;
    private JPanel ajout;
    private JPanel modification;
    private JPanel graphe;
    private JPanel choixGraphe;
        
    //On crée les boutons
    
    private JButton graphe1;
    private JButton graphe2;
    private JButton graphe3;
    private JButton ok;
    
    //les zones de textes
    private JTextField request;
    
    //les textes
    private JLabel bienvenue1;
    private JLabel choixRequest;
    private JLabel texteRequete;
    
    //les combos
    private JComboBox choixRequete;
    
    private JTabbedPane onglet;
   
    //les Strings
    private String[] tab_choix={"Maaf","infirmiers nuit","presentation services"};
    
    // Constructeurs
    public Fenetre(Connexion connex)
    {
        super();
        setTitle("HospitalTracker");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        LectureBDD a = new LectureBDD();
        this.connex = connex;
        
    //On crée les boutons
        graphe1 = new JButton("GRAPHE1");
        graphe2 = new JButton("GRAPHE2");
        graphe3 = new JButton("GRAPHE3");
        ok = new JButton("ok");
    
    //On crée les zones de texte
        request = new JTextField(15);
    
    //On crée les labels
        bienvenue1 = new JLabel("Bienvenue que souhaitez-vous faire");
        choixRequest = new JLabel("Choisissez votre requête");
        texteRequete = new JLabel("Entrez le texte de votre requête");
        
    //On crée les combos box
        choixRequete = new JComboBox(tab_choix);
        choixRequete.addActionListener(this);

        // on crée les panneaux
        choix = new JPanel(new GridLayout(15,2,15,15));
        ajout = new JPanel();
        modification = new JPanel();
        supprimer = new JPanel();
        graphe = new JPanel();
        bienvenue = new JPanel();
        
        bienvenue.add(bienvenue1);
        choix.add(choixRequest);
        choix.add(choixRequete);
        choix.add(texteRequete);
        choix.add(request);
        graphe.add(graphe1);
        graphe.add(graphe2);
        graphe.add(graphe3);
        
        onglet = new JTabbedPane();
        
        onglet.add("modification",modification);
        onglet.add("Ajout",ajout);
        onglet.add("Supprimer",supprimer);
        onglet.add("Graphe",graphe);
        
        setLayout(new BorderLayout());
        getContentPane().add(bienvenue,BorderLayout.NORTH);
        getContentPane().add(choix,BorderLayout.WEST);
        getContentPane().add(onglet,BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        String nom;
        if(source == choixRequete){
            int index = choixRequete.getSelectedIndex();
            switch(index){
                case 0:
                    nom = "MAAF";
            {
                try {
                    Tableau b = a.maladesMutuelle(connex,nom);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
               
                case 1:
            {
                try {
                    Tableau c = a.infirmiersNuit(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                 
                case 2:
            {
                try {
                    Tableau d = a.presentationServices(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                default:
                    System.out.println("erreur");
                    break;    
            }  
        }
        /*if(ae.getSource().equals(fen.getOk())){
           try {
                    nom = fen.getDocteur();
                    Tableau e = LectureBDD.docteurByName(connex,nom);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(HospitalTracker.class.getName()).log(Level.SEVERE, null, ex);
                }
        }*/
    }

}
