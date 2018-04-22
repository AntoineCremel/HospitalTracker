package controler;

import controler.container.Statistiques;
import java.sql.SQLException;
import modele.Connexion;
import java.util.ArrayList;
import vue.Tableau;
/**
 *  Classe regroupant toutes les fonctions de lecture de la Base De Données.
 *  Classe abstraite.
 *  
 * @exception Toutes les fonctions de cette classe renvoient des SQLException,
 *  qui peuvent se produire dans le cas de mauvaises requêtes.
 * 
 * @author Antoine
 */
public abstract class LectureBDD {
    
    /// Recherche
    /*
    Cette section de la classe liste les méthodes qui sont utilisées dans la
    page recherche du programme afin de remonter les informations à
    l'utilisateur
    */
    public static Tableau maladesMutuelle(Connexion connex,
            String mutuelle) throws SQLException
    {
        /**
        Fonction chargée de renvoyer une liste des noms et prénoms 
        de tous les malades ayant une
        certaine mutuelle.
            @param connex une instance de la classe connexion permettant les
            interactions avec la base de donnée.
            @param mutuelle, un string contenant le nom d'une mutuelle
            * 
            * 
            @return retour : Un ArrayList contenant des tableaux de deux cases :
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
        /**
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
        /**
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
        String[] entete = {"Code du service",
            "Nombre de lits moyen"};
        
        // 1 Composition de la requete
        requete = "SELECT `code_service`,  AVG(`nb_lits`) \n" +
"FROM chambre\n" +
"WHERE `code_service`LIKE 'REA' OR `code_service`LIKE 'CHG'\n" +
"GROUP BY `code_service`";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "Nombre moyen de lits");
        
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
        
        tab = new Tableau(retour, entete, "Rapport entre le nombre d’infirmier(ères) affecté(es) au service et le nombre de\n" +
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
        String[] entete = {"Service", "Salaire moyen par service"};
        
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
        String[] entete = {"Nom", "Prenom", "nombre soignants","nombre spécialités"};
        
        // 1 Composition de la requete
        requete = "select a.nom, a.prenom, count(*) as nb_soignants, count(distinct s.specialite) as nb_specialites from docteur s, soigne so, malade a where s.numero = so.no_docteur and so.no_malade = a.numero group by a.nom, a.prenom ";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "Nombre de médecins et spécialités par patient");
        
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
    
    public static Tableau BatimentMalade(Connexion connex)
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
        String[] entete = {"Batiment", "Numéro de chambre"};
        
        // 1 Composition de la requete
        requete = "select    distinct s.batiment, h.no_chambre from      service s, hospitalisation h where     s.code = h.code_service ;\n" +
"\n" +
" ";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "Bâtiment et numéro des chambres occupées par au moins un malade");
        
        return tab;
    }
    
     public static Tableau NombreMoyenA(Connexion connex)
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
        String[] entete = {"Batiment", "Numéro de chambre", "nombre de lites occupés"};
        
        // 1 Composition de la requete
        requete = "  select    s.batiment, c.no_chambre, c.nb_lits, count(*) as nb_lits_occupes from      service s, chambre c, hospitalisation h where     s.code = c.code_service and       c.code_service = h.code_service and       c.no_chambre = h.no_chambre group by  s.batiment, c.no_chambre, c.nb_lits union select    s.batiment, c.no_chambre, c.nb_lits, 0 as nb_lits_occupes from      service s, chambre c where     s.code = c.code_service and       (c.code_service, c.no_chambre) not in (           select  code_service, no_chambre           from    hospitalisation )  ;";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "Bâtiment, numéro et nombre de lits occupés par les malades qui y sont hospitalisés");
        
        return tab;
    }
      public static Tableau LitCardio(Connexion connex)
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
        String[] entete = { "Numéro de chambre"};
        
        // 1 Composition de la requete
        requete = "select    no_chambre from      chambre c where     code_service in (         select    code         from      service         where     nom = 'Cardiologie' ) and       nb_lits > (         select    count(*)         from      hospitalisation h         where     h.code_service = c.code_service         and       h.no_chambre = c.no_chambre ) ;";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "Chambres qui ont des lits disponibles dans le service de Cardiologie");
        
        return tab;
    }
       public static Tableau DirecteurMalade(Connexion connex)
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
        String[] entete = {"prenom", "Nom"};
        
        // 1 Composition de la requete
        requete = "select    prenom, nom from      malade where     numero in (         select    h.no_malade         from      hospitalisation h, service s, soigne so         where     h.code_service = s.code         and       s.directeur = so.no_docteur         and       so.no_malade = h.no_malade ) ;\n" +
"\n" +
" ";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "Prénom et nom des malades soignés par le directeur du service dans lequel ils sont hospitalisés.");
        
        return tab;
    }
        public static Tableau ChambreVide(Connexion connex)
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
        String[] entete = {"batiment", "numero chambre"};
        
        // 1 Composition de la requete
        requete = "select    s.batiment, c.no_chambre from      service s, chambre c where     s.code = c.code_service and       (c.code_service, c.no_chambre) not in (         select    code_service, no_chambre         from      hospitalisation ) ;\n" +
"";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "Bâtiment et numéro des chambres vides");
        
        return tab;
    }
    
    public static Tableau MaladeParDocteur(Connexion connex)
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
        String[] entete = {"prenom", "nom","nombre"};
        
        // 1 Composition de la requete
        requete = "select e.prenom, e.nom, count(*) as nb_hospitalisations from employe e, soigne s, hospitalisation h where e.numero = s.no_docteur and s.no_malade = h.no_malade group by e.nom, e.prenom union all select prenom, nom, 0 as nb_hospitalisations from employe where numero in ( select numero from docteur )and numero not in ( select no_docteur from soigne where no_malade in ( select no_malade from hospitalisation ) )";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "Nombre de malades hospitalisés par chaque docteur");
        
        return tab;
    }
    
    public static Tableau Malade3(Connexion connex)
            throws SQLException
    {
        /*
        Fonction renvoyant la alade soigné par plus de 3 médecins donner le nombre total de ses--      médecins ainsi que le nombre correspondant de spécialités médicales concernées.
        */
        // 0 Variables
        ArrayList<ArrayList<String>> retour;
        Tableau tab;
        String requete;
        String[] entete = {"prenom", "nom","nombre médecins","nombre spécialités concernées"};
        
        // 1 Composition de la requete
        requete = " select    m.nom, m.prenom, count(*) as nb_soignants,           count(distinct d.specialite) as nb_specialites from      docteur d, soigne so, malade m where     d.numero = so.no_docteur and       so.no_malade = m.numero group by  m.nom, m.prenom having    count(*) > 3 ;";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "Malade soigné par plus de 3 médecins,noms et spécialités des  médecins");
        
        return tab;
    }
    
    public static Tableau BatimentB(Connexion connex)
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
        String[] entete = {"no_chambre", "lits","nom service","prenom du malade","nom du malade","malade mutuelle"};
        
        // 1 Composition de la requete
        requete = "select    h.no_chambre, h.lit, s.nom, m.prenom, m.nom, m.mutuelle from      service s, hospitalisation h, malade m where     s.batiment = 'B'and       s.code = h.code_service and       h.no_malade = m.numero and       m.mutuelle like 'MN%' ;\n" +
"\n" +
" " +
"";
        
        retour = new ArrayList<>(connex.remplirChampsRequete(requete));
        
        tab = new Tableau(retour, entete, "Caractéristiques du bâtiment B");
        
        return tab;
    }
    
    public static Tableau litsOcupeBatiment(Connexion connex, String batiment,
            String debutMutuelle)
            throws SQLException
    {
        /**
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
        /**
        Fonction qui permet de récupérer une liste de docteurs répondant au nom
        nomDocteur. Renvoit tous les champs de chaque ligne correspondant à ce
        nom.
        */
        // 0 Variables
        // Création d'un string de requête SQL
        ArrayList<ArrayList<String>> tab;
        String requete;
        
        // 1 Composition de la requête
        requete = "SELECT employe.numero FROM docteur INNER JOIN employe ON docteur.numero LIKE employe.numero WHERE employe.nom = \""+nomDocteur+"\"";
        
        // 2 Executioon et récupération du résultat
        tab = connex.remplirChampsRequete(requete);
        
        return tab;
    }
    public static ArrayList<ArrayList<String>> patientByName(Connexion connex,
            String nomPatient) throws SQLException
    {
        /**
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
        requete = "SELECT * FROM `malade` WHERE `nom`= \"" + nomPatient + "\"";
        
        // 2 Executioon et récupération du résultat
        retour = connex.remplirChampsRequete(requete);
        
        return retour;
    }
    public static int getHighestID(Connexion connex, String table)
            throws SQLException
    {
        /**
        Fonction chargée de détecter et de renvoyer l'ID le plus haut dans une
        table donnée. Cela permet de générer un nouvel ID vierge.
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
        /**
        Fonction chargée de vérifier que le résultat renvoyé ne comporte qu'une
        seule ligne. Génère des exceptions si ce n'est pas le cas
        * @throws AmbivalentQueryException si le résultat de la query contient 
        * plus d'un résultat.
        * @throws NullQueryException si le résultat de la query est vide
        */
        
        if(queryResponse.size() > 1)
            throw new AmbivalentQueryException();
        else if(queryResponse.isEmpty())
            throw new NullQueryException();
    }
}
