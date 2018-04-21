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
    private JButton clear;
    private JButton ok;
    private JButton modif;
    
    //les zones de textes
    private JTextField request;
    private JTextField docteur;
    private JTextField patient;
    
    //les textes
    private JLabel bienvenue1;
    private JLabel choixRequest;
    private JLabel texteRequete;
    private JLabel nomDocteur;
    private JLabel nomPatient;
    
    //les combos
    private JComboBox choixRequete;
    private JComboBox choixModif;
    private JComboBox choixAjout;
    private JComboBox choixSuppr;
    
    private JTabbedPane onglet;
   
    //les Strings
    private String[] tab_choix={"Maaf","infirmiers nuit","presentation services","Nombre moyen de lits","Salaire moyen des infirmières","Infirmiers travaillant de nuit ","rapport entre le nombre d'infirmières et le nombre de malades","docteurs ayant au moins un malade hospitalisé","Medecins soignant un Malade","Medecins Sans Malade"};
    private String[] tab_modif={"Affecter docteur à malade","requête 2","requête 3"};
    private String[] tab_ajout={"Engager un docteur","requête 2","requête 3"};
    private String[] tab_suppr={"requête 1","requête 2","requête 3"};
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
        clear = new JButton("CLEAR");
        ok = new JButton("ok");
        modif = new JButton("Modifier");
    
    //On crée les zones de texte
        request = new JTextField(15);
    
    //On crée les labels
        bienvenue1 = new JLabel("Bienvenue que souhaitez-vous faire ?");
        choixRequest = new JLabel("Choisissez votre requête");
        texteRequete = new JLabel("Entrez le texte de votre requête");
        
    //On crée les combos box
        choixRequete = new JComboBox(tab_choix);
        choixAjout = new JComboBox(tab_ajout);
        choixModif = new JComboBox(tab_modif);
        choixSuppr = new JComboBox(tab_suppr);
        
        nomDocteur = new JLabel("Nom du docteur");
        nomPatient = new JLabel("Nom du patient");
        docteur = new JTextField(15);
        patient = new JTextField(15);
        
        choixRequete.addActionListener(this);
        choixAjout.addActionListener(this);
        choixModif.addActionListener(this);
        choixSuppr.addActionListener(this);
        graphe1.addActionListener(this);
        graphe2.addActionListener(this);
        graphe3.addActionListener(this);
        clear.addActionListener(this);
        modif.addActionListener(this);

        // on crée les panneaux
        choix = new JPanel(new GridLayout(15,2,15,15));
        ajout = new JPanel();
        modification = new JPanel(new GridLayout(15,4,15,15));
        supprimer = new JPanel();
        graphe = new JPanel();
        bienvenue = new JPanel();
        
        bienvenue.add(bienvenue1);
        choix.add(choixRequest);
        choix.add(choixRequete);
        choix.add(texteRequete);
        choix.add(request);
        choix.add(graphe1);
        choix.add(graphe2);
        choix.add(graphe3);
        choix.add(clear);
        modification.add(choixModif);
        modification.add(modif);
        ajout.add(choixAjout);
        supprimer.add(choixSuppr);
        onglet = new JTabbedPane();
        Camembert ab = new Camembert("test");
        JPanel test = new JPanel();
        test = ab.createDemoPanel();
        graphe.add(test);
        
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
                    
                      case 3:
            {
                try {
                    Tableau e = a.NombreLitMoyen(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                    
                     case 4:
            {
                try {
                    Tableau f = a.SalaireMoyen(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                    
                       case 5:
            {
                try {
                    Tableau g = a.NombreTotalMedecins(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                    
                    case 6:
            {
                try {
                    Tableau h = a.Rapport(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                    
                    case 7:
            {
                try {
                    Tableau i = a.MedecinsAvecMalade(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                     case 8:
            {
                try {
                    Tableau j = a.MedecinsSansMalade(connex);
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
            if(source == graphe1){
                Camembert ab = new Camembert("test");
                JPanel test = new JPanel();
                test = ab.createDemoPanel();
                graphe.add(test);
                graphe.repaint();
               
            }
            if(source == graphe2){
                //coder ici
            }
            if(source == graphe3){
                //coder ici
            }
            if(source == clear){
                graphe.removeAll();
                graphe.repaint();
            }
            
            if(source == choixModif){
                int index = choixModif.getSelectedIndex();
                switch(index){
                    case 0:
                        
                        modification.add(nomDocteur);
                        modification.add(docteur);
                        modification.add(nomPatient);
                        modification.add(patient);
                        modification.repaint();
                            
                        break;
                    case 1 :

                        break;
                        }
                }
                if(source == modif){
                                modification.remove(nomDocteur);
                                modification.remove(docteur);
                                modification.remove(nomPatient);
                                modification.remove(patient);
                                //modification.remove(modif);
                                //modification.removeAll();
                                modification.repaint();
                                }
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
