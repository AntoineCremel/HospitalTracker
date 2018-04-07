package controler;

import java.sql.SQLException;
import modele.Connexion;

/**
 *Classe abstraite contenant toutes les méthodes d'écritures sur la base de 
 * donnée
 * 
 * @author Antoine
 */
abstract class EcritureBDD {
    public void affectDocteurPatient(Connexion connex, int IDDocteur,
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
}
