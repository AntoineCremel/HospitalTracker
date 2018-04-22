/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

/**
 * Classe contenant l'exception AmbivalentQueryException
 * @author Antoine
 */
public class AmbivalentQueryException extends Exception{
    public AmbivalentQueryException()
    {
        System.out.println
            ("La requete est ambivalente et ne peut être résolue.");
    }
    public AmbivalentQueryException(String message)
    {
        System.out.println(message);
    }
}
