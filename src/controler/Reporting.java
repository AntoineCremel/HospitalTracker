package controler;

import controler.container.Statistiques;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Connexion;

/**
 *
 * @author Antoine
 */
public abstract class Reporting {
    public static Statistiques repartitionSpecialitesDocteurs(Connexion connex)
            throws SQLException
    {
        /**
        Fonction permettant de récupérer le nombre de médecin pour chaque
        spécialité.
        */
        // 0 Variables
        String requete;
        ArrayList<ArrayList<String>> lecture;
        Statistiques retour = new Statistiques();
        
        // 1 Création de la requete
        requete = "SELECT COUNT(numero),specialite FROM docteur GROUP BY"
                + "specialite";
        // 2 Récupération du résultat
        lecture = connex.remplirChampsRequete(requete);
        // 3 Remplissage du resultat dans une variable de type statistiques
        for(int i = 0; i<lecture.size(); i++)
        {
            retour.addElement(lecture.get(i).get(1), 
                    Integer.parseInt(lecture.get(i).get(0)));
        }
        
        return retour;
    }
}
