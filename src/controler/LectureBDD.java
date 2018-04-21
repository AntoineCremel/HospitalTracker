package controler;

import java.sql.SQLException;
import modele.Connexion;
import java.util.ArrayList;
import vue.Tableau;
/**
 *  Classe regroupant toutes les fonctions de lecture de la Base De Données
 * 
 * @author Antoine
 */
public class LectureBDD {
    
    /// Recherche
    /*
    Cette section de la classe liste les méthodes qui sont utilisées dans la
    page recherche du programme afin de remonter les informations à
    l'utilisateur
    */
    public static Tableau maladesMutuelle(Connexion connex,
            String mutuelle) throws SQLException
    {
        /*
        Fonction chargée de renvoyer une liste des noms et prénoms 
        de tous les malades ayant une
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
        ArrayList<ArrayList<String>> retour;
        Tableau tab;
        String[] entete = {"Nom", "Prénom"};
        
        requete = "SELECT `malade`.`nom`, `malade`.`prenom` FROM `malade`" +
                "WHERE (`malade`.`mutuelle` = \""+ mutuelle + "\")";
                
        retour = connex.remplirChampsRequete(requete);
        
        tab = new Tableau(retour, entete, "Malades ayant la "
        + mutuelle);
        
        return tab;
    }
    public static Tableau infirmiersNuit(Connexion connex)
            throws SQLException
    {
        /*
        Fonction renvoyant la liste des noms et prénoms de tous les infirmiers
        travaillant pendant la rotation de nuit.
        */
        // 0 Variables
        ArrayList<ArrayList<String>> retour;
        Tableau tab;
        String requete;
        String[] entete = {"Nom", "Prenom"};
        
        // 1 Composition de la requete
        requete = "SELECT `nom`,`prenom` FROM `employe` "
                + "JOIN `infirmier` ON `employe`.`numero` = `infirmier`.`numero` "
                + "WHERE `infirmier`.`rotation` = 'NUIT'";
        
        retour = connex.remplirChampsRequete(requete);
        
        tab = new Tableau(retour, entete, "Infirmiers travaillant de nuit");
        
        return tab;
    }
    public static Tableau presentationServices(Connexion connex)
            throws SQLException
    {
        /*
        Fonction renvoyant le nom, bâtiment, prenom, nom et spécialité du
        directeur de chaque service.
        */
        // 0 Variables
        Tableau tab;
        String requete;
        String[] entete = {"Service", "Batiment", "Prenom du directeur",
            "Nom du directeur", "Specialite du directeur"};
        
        //1 Composition de la requête
        requete = "SELECT `service`.`nom`,`service`.`batiment`,`employe`"
                + ".`prenom`,`employe`.`nom`,`docteur`.`specialite` FROM "
                + "`service`, `docteur`,`employe` WHERE `service`.`directeur` "
                + "= `docteur`.`numero`AND `service`.`directeur` =`employe`"
                + ".`numero`";
        
        tab = new Tableau(connex.remplirChampsRequete(requete), entete,
                "Services");
        
        return tab;
    }
    
    
    public static Tableau NombreLitMoyen(Connexion connex)
            throws SQLException
    {
        /*
        Fonction renvoyant la liste des noms et prénoms de tous les infirmiers
        travaillant pendant la rotation de nuit.
        */
        // 0 Variables
        ArrayList<ArrayList<String>> retour;
        Tableau tab;
        String requete;
        String[] entete = {"Nom", "Prenom"};
        
        // 1 Composition de la requete
        requete = "SELECT `code_service`,  AVG(`nb_lits`) \n" +
"FROM chambre\n" +
"WHERE `code_service`LIKE 'REA' OR `code_service`LIKE 'CHG'\n" +
"GROUP BY `code_service`";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "Le nombre moyen de lits");
        
        return tab;
    }
    
     public static Tableau Rapport(Connexion connex)
            throws SQLException
    {
        /*
        Fonction renvoyant la liste des noms et prénoms de tous les infirmiers
        travaillant pendant la rotation de nuit.
        */
        // 0 Variables
        ArrayList<ArrayList<String>> retour;
        Tableau tab;
        String requete;
        String[] entete = {"Nom", "Prenom"};
        
        // 1 Composition de la requete
        requete = "select nom, ( select count(*) from infirmier i where i.code_service = s.code ) / ( select count(*) from hospitalisation h where h.code_service = s.code ) as rapport_i_sur_m from service s";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "rapport entre le nombre d’infirmier(ères) affecté(es) au service et le nombre de\n" +
"malades hospitalisés dans le service ");
        
        return tab;
    }
     
      public static Tableau SalaireMoyen(Connexion connex)
            throws SQLException
    {
        /*
        Fonction renvoyant la liste des noms et prénoms de tous les infirmiers
        travaillant pendant la rotation de nuit.
        */
        // 0 Variables
        ArrayList<ArrayList<String>> retour;
        Tableau tab;
        String requete;
        String[] entete = {"Nom", "Prenom"};
        
        // 1 Composition de la requete
        requete = "SELECT `code_service`,  AVG(salaire) \n" +
"FROM infirmier\n" +
"WHERE `code_service`LIKE 'REA' OR `code_service`LIKE 'CHG' OR `code_service`LIKE 'CAR'\n" +
"GROUP BY `code_service`";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "Salaire moyen des infirmières ");
        
        return tab;
    }
    
       public static Tableau NombreTotalMedecins(Connexion connex)
            throws SQLException
    {
        /*
        Fonction renvoyant la liste des noms et prénoms de tous les infirmiers
        travaillant pendant la rotation de nuit.
        */
        // 0 Variables
        ArrayList<ArrayList<String>> retour;
        Tableau tab;
        String requete;
        String[] entete = {"Nom", "Prenom"};
        
        // 1 Composition de la requete
        requete = "select a.nom, a.prenom, count(*) as nb_soignants, count(distinct s.specialite) as nb_specialites from docteur s, soigne so, malade a where s.numero = so.no_docteur and so.no_malade = a.numero group by a.nom, a.prenom having count(*) > 3";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "Infirmiers travaillant de nuit");
        
        return tab;
    }
       public static Tableau MedecinsAvecMalade(Connexion connex)
            throws SQLException
    {
        /*
        Fonction renvoyant la liste des noms et prénoms de tous les infirmiers
        travaillant pendant la rotation de nuit.
        */
        // 0 Variables
        ArrayList<ArrayList<String>> retour;
        Tableau tab;
        String requete;
        String[] entete = {"Nom", "Prenom"};
        
        // 1 Composition de la requete
        requete = "select prenom, nom from employe where numero in ( select no_docteur from soigne where no_malade in ( select no_malade from hospitalisation ) )";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "Prénom et nom des docteurs ayant au moins un malade hospitalisé.");
        
        return tab;
    }
    
          public static Tableau MedecinsSansMalade(Connexion connex)
            throws SQLException
    {
        /*
        Fonction renvoyant la liste des noms et prénoms de tous les infirmiers
        travaillant pendant la rotation de nuit.
        */
        // 0 Variables
        ArrayList<ArrayList<String>> retour;
        Tableau tab;
        String requete;
        String[] entete = {"Nom", "Prenom"};
        
        // 1 Composition de la requete
        requete = "select prenom, nom from employe where numero in ( select numero from docteur ) and numero not in ( select no_docteur from soigne where no_malade in ( select no_malade from hospitalisation ) )";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "Prénom et nom des docteurs n’ayant aucun malade hospitalisé. ");
        
        return tab;
    }
    
    
    public static Tableau litsOcupeBatiment(Connexion connex, String batiment,
            String debutMutuelle)
            throws SQLException
    {
        /*
        Fonction renvoyant la liste des lits occupés dans le batiment par des
        malades dont la mutuelle commence par debutMutuelle
        */
        // 0 Variables
        Tableau tab;
        String requete;
        String[] entete = {"Chambre", "nombre de lits", "lit",
            "Nom du service", "nom malade", "prenom malade", "Mutuelle malade"};
        
        //1 Composition de la requête
        requete = "SELECT `chambre`.`no_chambre`,`chambre`.`nb_lits`,"
                + "`hospitalisation`.`lit`,`service`.`nom`,`malade`.`nom`,"
                + "`malade`.`prenom`,`malade`.`mutuelle` FROM `chambre`, "
                + "`hospitalisation`,`service`,`malade` WHERE `malade`."
                + "`mutuelle` LIKE '"+ debutMutuelle +"%'";
                //AND "
                //+ "`service`.`batiment` = " + batiment;
        
        tab = new Tableau(connex.remplirChampsRequete(requete), entete,
                "Services");
        
        return tab;
    }
    /// Support de l'écriture
    /*
    Cette section de la classe contient des méthodes qui fonctionnent en support
    des méthodes d'écriture.
    */
    public static ArrayList<ArrayList<String>> docteurByName(Connexion connex,
            String nomDocteur) throws SQLException
    {
        /*
        Fonction qui permet de récupérer une liste de docteurs répondant au nom
        nomDocteur. Renvoit tous les champs de chaque ligne correspondant à ce
        nom.
        */
        // 0 Variables
        // Création d'un string de requête SQL
        ArrayList<ArrayList<String>> tab;
        String requete;
        
        // 1 Composition de la requête
        requete = "SELECT * FROM `employe` WHERE `nom`=" + nomDocteur +
                "INNER JOIN `docteur` ON `employe`.`numero` = `docteur`" + 
                ".`numero`";
        
        // 2 Executioon et récupération du résultat
        tab = connex.remplirChampsRequete(requete);
        
        return tab;
    }
    public static ArrayList<ArrayList<String>> patientByName(Connexion connex,
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
    public static int getHighestID(Connexion connex, String table)
            throws SQLException
    {
        /*
        Fonction chargée de détecter et de renvoyer l'ID le plus haut dans une
        table donnée.
        */
        // 0 Variables
        int retour;
        ArrayList<ArrayList<String>> lecture;
        
        // 1 Récupération de la requête
        lecture = connex.remplirChampsRequete("SELECT MAX(numero) FROM "+table);
        
        // 2 Récupération de l'ID la plus haute
        retour = Integer.parseInt(lecture.get(0).get(0));
        
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
