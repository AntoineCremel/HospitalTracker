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
public abstract class EcritureBDD {
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
                "VALUES(\"" + IDDocteur + "\", \"" + IDMalade + "\")";
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
        
        // 1 Appel de la fonction d'écriture :
        affectDocteurPatient(connex, IDDocteur, IDMalade);
    }
    public static void engagerDocteur(Connexion connex, String nom,
            String prenom, String tel, String adresse, String specialite)
            throws SQLException
    {
        /*
        Fonction utilisée pour ajouter un docteur à la base de données.
        Reçoit en paramètre toutes les informations d'un médecin dans la base
        de données.
        */
        // 0 Variables
        String requete;
        int newID;
        
        // 1 Récupération de l'ID la plus haute :
        newID = LectureBDD.getHighestID(connex, "employe") + 1;
        
        // 2 Composition de la requête sur la table employe
        requete = "INSERT INTO employe (numero, nom, prenom, adresse, tel, "
                + ") VALUES (\""+newID+"\", \""+nom+"\", \""+prenom+"\", \""
                +adresse+"\", \""+tel+"\")\"";
        
        // 3 Ajout du médecin à la table employé
        connex.executeUpdate(requete);
        
        // 4 A présent il faut ajouter le médecin à la table médecin
        connex.executeUpdate("INSERT INTO docteur (numero, sepcialite) "
            + "VALUES (\"" + newID + "\", \"" + specialite + "\")");
    }
    public static void retirerMalade(Connexion connex, String nom)
            throws SQLException, AmbivalentQueryException, NullQueryException
    {
        /*
        Méthode qui sert à retirer un malade de la liste des hospitatlisations.
        Le malade sera gardé dans la liste des malades, afin de permettre à l'
        hopital de garder une liste des patients qui ont été hospitatlisés chez
        lui.
        */
        // 0 Variables
        String requete;
        ArrayList<ArrayList<String>> lecture;
        int patientID;
        
        // 1 Récupération de l'ID du patient
        lecture = LectureBDD.patientByName(connex, nom);
        // 1.1 Vérification de la requête a renvoyé un résultat valide
        LectureBDD.assertSingle(lecture);
        // 1.2 Récupération de l'ID
        patientID = Integer.parseInt(lecture.get(0).get(0));
        
        // 2 Génération de la requête d'effaçage
        requete = "DELETE FROM hospitalisation WHERE no_malade = \""
                + patientID + "\"";
        
        // 3 Execution de la requete
        connex.executeUpdate(requete);
    }
}

