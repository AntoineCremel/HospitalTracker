package controler;

import java.sql.SQLException;
import modele.Connexion;
import java.util.ArrayList;
/**
 *  Classe regroupant toutes les fonctions de lecture de la Base De Données
 * 
 * @author Antoine
 */
abstract class LectureBDD {
    
    /// Recherche
    /*
    Cette section de la classe liste les méthodes qui sont utilisées dans la
    page recherche du programme afin de remonter les informations à
    l'utilisateur
    */
    public static ArrayList maladesMutuelle(Connexion connex,
            String mutuelle) throws SQLException
    {
        /*
        Fonction chargée de renvoyer une liste de tous les malades ayant une
        certaine mutuelle.
        Entrées : 
            - connex, une instance de la classe connexion permettant les
            interactions avec la base de donnée.
            - mutuelle, un string contenant le nom d'une mutuelle
        Sorties :
            - retour : Un ArrayList contenant des tableaux de deux cases :
            le nom est contenu dans la case 0, le prénom dans la case 1.
        */
        // 0 Variables
        // Création d'un string de requête SQL
        String requete;
        // Création de la variable de retour
        ArrayList retour;
        
        requete = "SELECT `malade`.`nom`, `malade`.`prenom` FROM `malade`" +
                "WHERE (`malade`.`mutuelle` = \""+ mutuelle + "\")";
                
        retour = connex.remplirChampsRequete(requete);
        
        return retour;
    }
    /// Support de l'écriture
    /*
    Cette section de la classe contient des méthodes qui fonctionnent en support
    des méthodes d'écriture.
    */
    public static ArrayList docteurByName(Connexion connex,
            String nomDocteur) throws SQLException
    {
        /*
        Fonction qui permet de récupérer une liste de docteurs répondant au nom
        nomDocteur. Renvoit tous les champs de chaque ligne correspondant à ce
        nom.
        */
        // 0 Variables
        // Création d'un string de requête SQL
        String requete;
        //Création de la variable de retour 
        ArrayList retour;
        
        // 1 Composition de la requête
        requete = "SELECT * FROM `employe` WHERE `nom`=" + nomDocteur +
                "INNER JOIN `docteur` ON `employe`.`numero` = `docteur`" + 
                ".`numero`";
        
        // 2 Executioon et récupération du résultat
        retour = connex.remplirChampsRequete(requete);
        
        return retour;
    }
    public static ArrayList patientByName(Connexion connex,
            String nomPatient) throws SQLException
    {
        /*
        Fonction qui permet de récupérer une liste de patients répondant au nom
        nomPatient. Renvoit tous les champs de chaque ligne correspondant à ce
        nom.
        */
        // 0 Variables
        // Création d'un string de requête SQL
        String requete;
        //Création de la variable de retour 
        ArrayList retour;
        
        // 1 Composition de la requête
        requete = "SELECT * FROM `malade` WHERE `nom`=" + nomPatient;
        
        // 2 Executioon et récupération du résultat
        retour = connex.remplirChampsRequete(requete);
        
        return retour;
    }
    // Fonctions d'erreur
    public static void assertSingle(ArrayList<ArrayList<String>> queryResponse)
            throws AmbivalentQueryException, NullQueryException
    {
        /*
        Fonction chargée de vérifier que le résultat renvoyé ne comporte qu'une
        seule ligne.
        */
        
        if(queryResponse.size() > 1)
            throw new AmbivalentQueryException();
        else if(queryResponse.isEmpty())
            throw new NullQueryException();
    }
}
