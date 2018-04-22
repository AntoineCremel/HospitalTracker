package vue;

import controler.AmbivalentQueryException;
import controler.LectureBDD;
import controler.EcritureBDD;
import controler.NullQueryException;
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
 * Elle se compose de tous les éléments utilisés pour choisir les requêtes de
 * lecture et écriture ainsi que l'affichage des graphes
 */
public class Fenetre extends JFrame implements ActionListener{
    //Objet connexion
    private Connexion connex;
    
    private LectureBDD a;
    
    //panneaux utilisés dans la fenetre
    private JPanel bienvenue;
    private JPanel supprimer;
    private JPanel choix;
    private JPanel ajout;
    private JPanel modification;
    private JPanel graphe;
    private JPanel choixGraphe;
        
    //Boutons
    private JButton graphe1;
    private JButton graphe2;
    private JButton graphe3;
    private JButton clear;
    private JButton ok;
    private JButton modif;
    private JButton update1;
    private JButton update2;
    private JButton suppr1;
    private JButton lecture;
    
    //les zones de textes
    private JTextField request;
    private JTextField docteur;
    private JTextField patient;
    private JTextField nPatient;
    private JTextField n2Patient;
    private JTextField prePatient;
    private JTextField tPatient;
    private JTextField adrPatient;
    private JTextField mutPatient;
    private JTextField prenomDocteurUpdate;
    private JTextField nomDocteurUpdate;
    private JTextField telDocteurUpdate;
    private JTextField adresseDocteurUpdate;
    private JTextField speDocteurUpdate;
    
    //les textes
    private JLabel bienvenue1;
    private JLabel choixRequest;
    private JLabel texteRequete;
    private JLabel nomDocteur;
    private JLabel nomPatient;
    private JLabel prenomPatient;
    private JLabel adressePatient;
    private JLabel telPatient;
    private JLabel mutuellePatient;
    private JLabel prenomDocteur;
    private JLabel nomDoc;
    private JLabel telDocteur;
    private JLabel adresseDocteur;
    private JLabel speDocteur;
    private JLabel namePatient;
    private JLabel name2Patient;
    private JLabel selectGraphe;
    
    //les combos
    private JComboBox choixRequete;
    private JComboBox choixModif;
    private JComboBox choixAjout;
    private JComboBox choixSuppr;
    
    //le tableau de panneaux
    private JTabbedPane onglet;
   
    //les Strings
    private String[] tab_choix={"Afficher les patients en fonction de leur mutuelle","Infirmiers de nuit","Presentation services","Nombre moyen de lits","Salaire moyen des infirmières","Nombre de médecins par patient",
        "Rapport entre le nombre d'infirmières et le nombre de malades","Docteurs ayant au moins un malade hospitalisé","Bâtiments avec des malades","Bâtiment avec numéro des chambres et nombre de lits occupés",
        "Chambres avec lits dispo en Cardio","Bâtiment et numéro des chambres vides","Nombre de malades soignés par chaque docteur","Malade avec plus de 3 médecins + nombre de médecins et spé concernées","Caractéristiques du bâtiment B"};
    private String[] tab_modif={"Affecter docteur à malade","requête 2","requête 3"};
    private String[] tab_ajout={"Engager un docteur","Ajouter malade","requête 3"};
    private String[] tab_suppr={"Retirer malade","requête 2","requête 3"};
    
    // Constructeurs de notre fenêtre
    public Fenetre(Connexion connex)
    {
        super();
        setTitle("HospitalTracker");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.connex = connex;
        
    //On crée les boutons
        graphe1 = new JButton("GRAPHE1");
        graphe2 = new JButton("GRAPHE2");
        graphe3 = new JButton("GRAPHE3");
        clear = new JButton("CLEAR");
        ok = new JButton("ok");
        modif = new JButton("Modifier");
        update1 = new JButton("Ajout");
        suppr1 = new JButton("Retirer");
        update2 = new JButton("Ajout");
        lecture = new JButton("Afficher");
    
    //On crée les zones de texte
        request = new JTextField(15);
        docteur = new JTextField(15);
        patient = new JTextField(15);
        prePatient = new JTextField(15);
        tPatient = new JTextField(15);
        nPatient = new JTextField(15);
        n2Patient = new JTextField(15);
        adrPatient = new JTextField(15);
        mutPatient = new JTextField(15);
        prenomDocteurUpdate = new JTextField(15);
        nomDocteurUpdate = new JTextField(15);
        telDocteurUpdate = new JTextField(15);
        adresseDocteurUpdate = new JTextField(15);
        speDocteurUpdate = new JTextField(15);
    
    //On crée les labels
        bienvenue1 = new JLabel("Bienvenue que souhaitez-vous faire ?");
        choixRequest = new JLabel("Choisissez votre requête");
        texteRequete = new JLabel("Entrez le texte de votre requête");
        nomDocteur = new JLabel("Nom du docteur");
        nomPatient = new JLabel("Nom du patient");
        prenomPatient = new JLabel("Prenom du patient");
        prenomDocteur = new JLabel("Prenom du docteur");
        nomDoc = new JLabel("Nom du docteur");
        telDocteur = new JLabel("Telephone du docteur");
        adresseDocteur = new JLabel("Adresse du docteur");
        speDocteur = new JLabel("Specialite du docteur");
        adressePatient = new JLabel("Adresse du patient");
        telPatient = new JLabel("Telephone du patient");
        mutuellePatient = new JLabel("Mutuelle du patient");
        namePatient = new JLabel("Nom Patient");
        name2Patient = new JLabel("Nom Patient");
        selectGraphe = new JLabel("Choix du graphe");
        
    //On crée les combos box
        choixRequete = new JComboBox(tab_choix);
        choixAjout = new JComboBox(tab_ajout);
        choixModif = new JComboBox(tab_modif);
        choixSuppr = new JComboBox(tab_suppr);
        
       
        
        
                    
    
    //On ajoute les ActionListener pour les boutons et comboBox
        choixRequete.addActionListener(this);
        choixAjout.addActionListener(this);
        choixModif.addActionListener(this);
        choixSuppr.addActionListener(this);
        graphe1.addActionListener(this);
        graphe2.addActionListener(this);
        graphe3.addActionListener(this);
        clear.addActionListener(this);
        modif.addActionListener(this);
        update1.addActionListener(this);
        update2.addActionListener(this);
        suppr1.addActionListener(this);
        lecture.addActionListener(this);

        // on crée les panneaux et on les organise
        choix = new JPanel(new GridLayout(15,2,15,15));
        ajout = new JPanel(new GridLayout(15, 2, 15, 15));
        modification = new JPanel(new GridLayout(15,4,15,15));
        supprimer = new JPanel(new GridLayout(15, 2, 15, 15));
        graphe = new JPanel();
        bienvenue = new JPanel();
        
        //On ajoute les différents éléments aux panneaux
        bienvenue.add(bienvenue1);
        choix.add(choixRequest);
        choix.add(choixRequete);
        choix.add(texteRequete);
        choix.add(request);
        choix.add(lecture);
        choix.add(selectGraphe);
        choix.add(graphe1);
        choix.add(graphe2);
        choix.add(graphe3);
        choix.add(clear);
        modification.add(choixModif);
        ajout.add(choixAjout);
        supprimer.add(choixSuppr);
        onglet = new JTabbedPane();
        Camembert ab = new Camembert("Médecins par spécialité",connex);
        JPanel test = new JPanel();
        test = ab.createDemoPanel();
        graphe.add(test);
        
        //On ajoute les panneaux au tableau de panneaux
        onglet.add("Modification",modification);
        onglet.add("Ajout",ajout);
        onglet.add("Supprimer",supprimer);
        onglet.add("Graphe",graphe);
        
        //On ajoute les panneaux à la fenêtre
        setLayout(new BorderLayout());
        getContentPane().add(bienvenue,BorderLayout.NORTH);
        getContentPane().add(choix,BorderLayout.WEST);
        getContentPane().add(onglet,BorderLayout.CENTER);
        pack();
        setVisible(true);
       
    }
    
    /**
     *
     * Pour gerer les actions sur les boutons on utilise la fonction
     * actionPerformed
     *
     * @param ae
     */

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        String nom;
        if(source == choixRequete){
            texteRequete.setText("Entrer le texte de la requête");
            choix.repaint();
            choix.revalidate();
            int index = choixRequete.getSelectedIndex();
            switch(index){
                case 0:
                    texteRequete.setText("Entrer le nom de la mutuelle");
                    choix.repaint();
                    choix.revalidate();
                    break;
               
                case 1:
            {
                try {
                    Tableau c = LectureBDD.infirmiersNuit(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                 
                case 2:
            {
                try {
                    Tableau d = LectureBDD.presentationServices(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                    
                      case 3:
            {
                try {
                    Tableau e = LectureBDD.NombreLitMoyen(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                    
                     case 4:
            {
                try {
                    Tableau f = LectureBDD.SalaireMoyen(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                    
                       case 5:
            {
                try {
                    Tableau g = LectureBDD.NombreTotalMedecins(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                    
                    case 6:
            {
                try {
                    Tableau h = LectureBDD.Rapport(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                    
                    case 7:
            {
                try {
                    Tableau i =LectureBDD.MedecinsAvecMalade(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
               case 8:
            {
                try {
                    Tableau j = LectureBDD.BatimentMalade(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                      case 9:
            {
                try {
                    Tableau j = LectureBDD.NombreMoyenA(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                      case 10:
            {
                try {
                    Tableau j = LectureBDD.LitCardio(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                      case 11:
            {
                try {
                    Tableau j = LectureBDD.ChambreVide(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                     case 12:
            {
                try {
                    Tableau j = LectureBDD.MaladeParDocteur(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;                    
                    case 13:
            {
                try {
                    Tableau j = LectureBDD.Malade3(connex);
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                    
                    case 14:
            {
                try {
                    Tableau j = LectureBDD.BatimentB(connex);
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
            if(source == lecture){
            {
                try {
                    Tableau b = LectureBDD.maladesMutuelle(connex,request.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
            if(source == graphe1){
                Camembert ab = new Camembert("test",connex);
                JPanel test = new JPanel();
                test = ab.createDemoPanel();
                graphe.add(test);
                graphe.repaint();
                graphe.revalidate();
               
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
                        modification.add(modif);
                        modification.repaint();
                        modification.revalidate();
                        
                            
                        break;
                    case 1 :

                        break;
                        }
                }
                if(source == modif){
            try {
                EcritureBDD.affectDocteurPatient(connex,docteur.getText(),patient.getText());
                modification.remove(nomDocteur);
                modification.remove(docteur);
                modification.remove(nomPatient);
                modification.remove(patient);
                modification.remove(modif);
                modification.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AmbivalentQueryException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullQueryException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }  
                                }
                
                if(source==choixAjout){
                    int index = choixAjout.getSelectedIndex();
                    switch(index){
                        case 0:
                           
                            ajout.add(nomDoc);
                            ajout.add(nomDocteurUpdate);
                            ajout.add(prenomDocteur);
                            ajout.add(prenomDocteurUpdate);
                            ajout.add(telDocteur);
                            ajout.add(telDocteurUpdate);
                            ajout.add(adresseDocteur);
                            ajout.add(adresseDocteurUpdate);
                            ajout.add(speDocteur);
                            ajout.add(speDocteurUpdate);
                            ajout.add(update1);
                            ajout.repaint();
                            ajout.revalidate();
                            break;
                        case 1 :
                            ajout.add(namePatient);
                            ajout.add(nPatient);
                            ajout.add(prenomPatient);
                            ajout.add(prePatient);
                            ajout.add(adressePatient);
                            ajout.add(adrPatient);
                            ajout.add(telPatient);
                            ajout.add(tPatient);
                            ajout.add(mutuellePatient);
                            ajout.add(mutPatient);
                            ajout.add(update2);
                            ajout.repaint();
                            ajout.revalidate();
                            break;
                    }
    
                }
                if(source == update1){
            try {
                EcritureBDD.engagerDocteur(connex,nomDocteurUpdate.getText(), prenomDocteurUpdate.getText(), telDocteurUpdate.getText(), adresseDocteurUpdate.getText(), speDocteurUpdate.getText());
                    ajout.remove(nomDoc);
                    ajout.remove(nomDocteurUpdate);
                    ajout.remove(prenomDocteur);
                    ajout.remove(prenomDocteurUpdate);
                    ajout.remove(adresseDocteur);
                    ajout.remove(adresseDocteurUpdate);
                    ajout.remove(telDocteur);
                    ajout.remove(telDocteurUpdate);
                    ajout.remove(speDocteur);
                    ajout.remove(speDocteurUpdate);
                    ajout.remove(update1);
                    ajout.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
                }
               if(source == update2){
            try {
                EcritureBDD.ajouterMalade(connex, nPatient.getText(), prePatient.getText(), adrPatient.getText(), tPatient.getText(),  mutPatient.getText());
                    ajout.remove(namePatient);
                    ajout.remove(nPatient);
                    ajout.remove(prenomPatient);
                    ajout.remove(prePatient);
                    ajout.remove(telPatient);
                    ajout.remove(tPatient);
                    ajout.remove(adressePatient);
                    ajout.remove(adrPatient);
                    ajout.remove(mutuellePatient);
                    ajout.remove(mutPatient);
                    ajout.remove(update2);
                    ajout.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
               }
            if(source == choixSuppr){
                supprimer.add(name2Patient);
                supprimer.add(n2Patient);
                supprimer.add(suppr1);
                supprimer.repaint();
                supprimer.revalidate();
            }
            if(source == suppr1){
            try {
                EcritureBDD.retirerMalade(connex,n2Patient.getText());
                supprimer.remove(name2Patient);
                supprimer.remove(n2Patient);
                supprimer.remove(suppr1);
                supprimer.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AmbivalentQueryException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullQueryException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            }
        }
        
