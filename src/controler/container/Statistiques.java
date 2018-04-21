package controler.container;

import java.util.ArrayList;
import java.util.List;

/**
 *  Classe qui sera utilisée comme variable de retour pour la construction de
 * camemberts.
 * 
 * @author Antoine
 */
public class Statistiques {
    // Variables
    // Categories contient une liste de Strings qui contiennent les noms des
    // catégories des chiffres contenus dans numbets
    private List categories;
    // Numbers contient les données numériques de l'élément.
    private List numbers;
    
    public Statistiques(){
        this.categories = new ArrayList();
        this.numbers = new ArrayList();
    }
    public Statistiques(ArrayList categ, ArrayList nums){
        this.categories = new ArrayList(categ);
        this.numbers = new ArrayList(nums);
    }
    
    // Accesseurs en écriture
    public void addElement(String name, int value)
    {
        /**
        Ajoute l'élément de nom name et de valeur value à l'objet statistique.
        */
        this.categories.add(name);
        this.numbers.add(value);
    }
    
    // Accesseurs en lecture
    public ArrayList getElement(int i)
    {
        /**
        Renvoit un ArrayList contenant en case 0 un nom de categorie et en case
        1 une valeur.
        */
        ArrayList retour = new ArrayList();
        
        retour.add(categories.get(i));
        retour.add(numbers.get(i));
        
        return retour;
    }
    public String getCategorie(int i)
    {
        /**
        Fonction pour récupérer la catégorie de la case numero i
        */
        return (String)categories.get(i);
    }
    public int getNumber(int i)
    {
        /**
        Fonction pour récupérer le chiffre de la case i
        */
        return (int)numbers.get(i);
    }
}
