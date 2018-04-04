package vue;

import javax.swing.JFrame;
/**
 * Contient la classe Fenetre qui sert de base à l'interface graphique.
 */
public class Fenetre extends JFrame{
    // Variables
    
    // Constructeurs
    public Fenetre()
    {
        super();
        this.setTitle("HospitalTracker");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
