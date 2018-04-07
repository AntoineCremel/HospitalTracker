/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vue.Fenetre;

/**
 *
 * @author Antoine
 */
public class HospitalTracker implements ActionListener{
    private Fenetre fen = new Fenetre();
    
    public HospitalTracker(Fenetre fen){
        this.fen = fen;
        this.fen.getLog().addActionListener(this);
        this.fen.getModif().addActionListener(this);
        this.fen.getRecherche().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(fen.getLog())){
            fen.nextContenu(1);
            //System.out.println("coucou");
        }
        if(ae.getSource().equals(fen.getModif())){
            fen.nextContenu(3);
        }
        if(ae.getSource().equals(fen.getRecherche())){
            fen.nextContenu(2);
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
