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
    public Tableau(ArrayList test,String nom){
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("JTable");
    this.setSize(300, 120);

    //Les données du tableau
    int tailleTab = test.size();
    //Object
    Object[][] test2 = null;
    for(int i=0; i< tailleTab; i++) {
    for(int j=0; j<tailleTab; j++) {
            test2[j][i] = test.get(j);
    }
}

    //Les titres des colonnes
    String  title[] = {"Prénom","Nom"};
    JTable tableau = new JTable(test2, title);
    //On ajoute le panneau avec un scroll
    this.getContentPane().add(new JScrollPane(tableau));
  }   
}
