package controler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modele.Connexion;

/**
 *Classe abstraite contenant toutes les méthodes d'écritures sur la base de 
 * donnée
 * 
 * @author Antoine
 */
abstract class EcritureBDD {
    public static void affectDocteurPatient(Connexion connex, int IDDocteur,
            int IDMalade)
            throws SQLException
    {
        /*
        Fonction utilisée pour affecter un docteur à un patient.
        Paramètres :
            - connex : Instances de la classe connexion qui est connectée à la 
            base de donnée correspondante.
            - IDDocteur : ID du docteur dans la base de donnée
            - IDPatient : ID du patient dans la base de donnée
        */
        // 0 Variables
        String requete;
        // 1 Composition de la requete d'écriture
        requete = "INSERT INTO soigne (no_docteur, no_malade)" +
                "VALUES(" + IDDocteur + ", " + IDMalade + ");";
        // 2 Execution de la requête
        connex.executeUpdate(requete);
    }
    public static void affectDocteurPatient(Connexion connex, String nomDocteur, 
            String nomPatient)
            throws SQLException, AmbivalentQueryException, NullQueryException
    {
        /*
        Fonction utilisée pour affecter un docteur à un patient.
        Paramètres :
            - connex : Instances de la classe connexion qui est connectée à la 
            base de donnée correspondante.
            - nomDocteur : nom du docteur dans la base de donnée
            - nomPatient : nom du patient dans la base de donnée
        ///ATTENTION
        Il faut que le nom corresponde exactement. La fonction est case
        sensitive et ne fait pas d'auto correct
        */
        // 0 Variables
        String requete;
        int IDDocteur;
        int IDMalade;
        ArrayList<ArrayList<String>> lecture;
        // Recuperation des IDs du patient et du docteur
        // Récupération de l'ID du docteur
        lecture = new ArrayList<>
            (LectureBDD.docteurByName(connex, nomDocteur));
        LectureBDD.assertSingle(lecture);
        IDDocteur = Integer.parseInt(lecture.get(0).get(0));
        
        // Récupération de l'ID du patient
        lecture = new ArrayList<>
            (LectureBDD.patientByName(connex, nomPatient));
        LectureBDD.assertSingle(lecture);
        IDMalade = Integer.parseInt(lecture.get(0).get(0));
        
        // 1 Composition de la requete d'écriture
        requete = "INSERT INTO soigne (no_docteur, no_malade)" +
                "VALUES(" + IDDocteur + ", " + IDMalade + ");";
        // 2 Execution de la requête
        connex.executeUpdate(requete);
        
    }
    public static void engagerDocteur(Connexion connex, String nom,
            String prenom, String tel, String adresse, String sepcialite)
    {
        /*
        Fonction utilisée pour ajouter un docteur à la base de données.
        */
    }
}

