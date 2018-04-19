/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import modele.Connexion;

/**
 *
 * @author Jean
 */
public class Login extends JFrame implements ActionListener{
    
    private Connexion maconnexion;
    
    private JTabbedPane onglet;
    
    private JPanel login;
    private JPanel loginECE;
    private JPanel entete;
    
    private JButton log;
    private JButton logECE;
    
    private JLabel coucou;
    private JLabel mdp;
    private JLabel mdpSQL;
    private JLabel identifiant;
    private JLabel identifiantSQL;
    private JLabel usernameECE;
    private JLabel passwordEce;
    
    private JPasswordField password;
    private JTextField id;
    private JPasswordField passwordSQL;
    private JTextField idSQL;
    private JTextField userECE;
    private JPasswordField passECE;
    
    public Login(){
        super();
        setTitle("HospitalTracker");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        log = new JButton("LOGIN");
        logECE = new JButton("LOGIN ECE");
        
        password = new JPasswordField(8);
        id = new JTextField(30);
        passwordSQL = new JPasswordField(8);
        idSQL = new JTextField(30);
        userECE = new JTextField(30);
        passECE = new JPasswordField(8);
        
        coucou = new JLabel("Bienvenue sur Hospital Tracker",JLabel.CENTER);
        mdp = new JLabel("Mot de passe SQL", JLabel.CENTER);
        mdpSQL = new JLabel("Mot de passe SQL", JLabel.CENTER);
        identifiant = new JLabel("Identifiant SQL", JLabel.CENTER);
        identifiantSQL = new JLabel("Identifiant SQL", JLabel.CENTER);
        usernameECE = new JLabel("Identifiant ECE", JLabel.CENTER);
        passwordEce = new JLabel("Mot de passe ECE", JLabel.CENTER);
        
        login = new JPanel(new GridLayout(0,1,5,5));
        loginECE = new JPanel(new GridLayout(0,1,5,5));
        entete = new JPanel();
        
        log.addActionListener(this);
        logECE.addActionListener(this);
       
        login.add(identifiant);
        login.add(id);
        login.add(mdp);
        login.add(password);
        login.add(log);
        loginECE.add(usernameECE);
        loginECE.add(userECE);
        loginECE.add(passwordEce);
        loginECE.add(passECE);
        loginECE.add(identifiantSQL);
        loginECE.add(idSQL);
        loginECE.add(mdpSQL);
        loginECE.add(passwordSQL);
        loginECE.add(logECE);
        entete.add(coucou);
        
        onglet = new JTabbedPane();
        onglet.add("Login",login);
        onglet.add("Login ECE",loginECE);
        
        setLayout(new BorderLayout());
        
        getContentPane().add(entete,BorderLayout.NORTH);
        getContentPane().add(onglet,BorderLayout.CENTER);
        
        
        pack();
        setVisible(true);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source == log){ //Si on appuie sur login
            // On récupère l'id et le password donnés par l'utilisateur et on
            // ouvre une connexion à la base de donnée
            try{
                // On ouvre la connexion à la base de donnée
                maconnexion = new Connexion("hopital", id.getText(),
                        password.getText());
                //On met la fenetre en invisible
                setVisible(false);
                //On crée la nouvelle fenetre
                Fenetre fen = new Fenetre(maconnexion);
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
        if(source == logECE){ //Si on appuie sur login
            // On récupère l'id et le password donnés par l'utilisateur et on
            // ouvre une connexion à la base de donnée
            try{
                // On ouvre la connexion à la base de donnée
                maconnexion = new Connexion(userECE.getText(),
                        passECE.getText(),idSQL.getText(),passwordSQL.getText());
                //On met la fenetre en invisible
                setVisible(false);
                //On crée la nouvelle fenetre
                Fenetre fen = new Fenetre(maconnexion);
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
    }
    
}
