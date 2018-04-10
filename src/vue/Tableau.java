/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.BorderLayout;
import java.util.ArrayList; 
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Jean
 */
public class Tableau extends JFrame{
    
    public Tableau(){
        
    }
    
    public Tableau(ArrayList<ArrayList<String>> test, String[] entete, String nom){
    this.setLocationRelativeTo(null);
    this.setTitle(nom);
    this.setSize(400, 200);

    //Les données du tableau
    int tailleI = test.size();
    int tailleJ = test.get(0).size();
    //Object
    Object[][] liste = new Object[tailleI][tailleJ];
    for(int i=0; i< tailleI; i++) {
        for(int j=0; j<tailleJ; j++) {
                liste[i][j] = test.get(i).get(j);
        }
    }

    //Les titres des colonnes
    String  title[] = entete;
    JTable tableau = new JTable(liste, title);
    //On ajoute le panneau avec un scroll
    this.getContentPane().add(new JScrollPane(tableau));
    this.setVisible(true);
  }
    
}
