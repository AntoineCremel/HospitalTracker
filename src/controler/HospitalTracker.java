/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import vue.Fenetre;
import modele.Connexion;
import vue.Tableau;

/**
 *
 * @author Antoine
 */
public class HospitalTracker implements ActionListener{
    private Fenetre fen,fen2;
    private Connexion connex;
    private ArrayList<ArrayList<String>> test;
    
    public HospitalTracker(Fenetre fen){
        this.fen = fen;
        this.connex = null;
        this.fen.getLog().addActionListener(this);
        this.fen.getModif().addActionListener(this);
        this.fen.getRecherche().addActionListener(this);
        this.fen.getChoixRequete().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(fen.getLog())){ //Si on appuie sur login
            // On récupère l'id et le password donnés par l'utilisateur et on
            // ouvre une connexion à la base de donnée
            try{
                // On ouvre la connexion à la base de donnée
                connex = new Connexion("hopital", fen.getId(),
                        fen.getPassword());
                // On passe à la fenêtre suivante
                fen.nextContenu(1);
            }
            catch(SQLException e){
                System.out.println("Exception SQL");
                // Afficher un message d'erreur en graphique
            }
            catch(ClassNotFoundException e){
                System.out.println("Exception class not found");
                // Afficher un message d'erreur en graphique
            }
        }
        if(ae.getSource().equals(fen.getModif())){ //modifier
            fen.nextContenu(3);
        }
        if(ae.getSource().equals(fen.getRecherche())){//recherche
            fen.nextContenu(2);
        }
        if(ae.getSource().equals(fen.getChoixRequete())){
            int index = fen.getSelectedIndex();
            String nom;
            switch(index){
                case 0:
            {
                try {
                    nom = "MAAF";
                    Tableau b = LectureBDD.maladesMutuelle(connex,nom);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(HospitalTracker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                case 1:
                    nom = "";
                    break;
                case 2:
                    nom = "";
                    break;
                default:
                    nom= "HospitalTracker";
                    break;    
            }
            //fen2= new Fenetre(nom);
            
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Fenetre fen = new Fenetre();
        HospitalTracker a = new HospitalTracker(fen);
        fen.affiche();
    }
    
    // Methodes 
     
}
