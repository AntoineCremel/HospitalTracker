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
    
    public Tableau(ArrayList<ArrayList<String>> test,String nom){
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("JTable");
    this.setSize(300, 120);

    //Les données du tableau
    int tailleI = test.size();
    int tailleJ = test.get(0).size();
    //Object
    Object[][] test2 = new Object[tailleI][tailleJ];
    for(int i=0; i< tailleI; i++) {
        for(int j=0; j<tailleJ; j++) {
                test2[i][j] = test.get(i).get(j);
        }
    }

    //Les titres des colonnes
    String  title[] = {"Prénom","Nom"};
    JTable tableau = new JTable(test2, title);
    //On ajoute le panneau avec un scroll
    this.getContentPane().add(new JScrollPane(tableau));
    this.setVisible(true);
  }   
}
