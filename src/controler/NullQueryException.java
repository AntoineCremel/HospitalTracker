/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

/**
 *
 * @author Antoine
 */
public class NullQueryException extends Exception{
    public NullQueryException()
    {
        System.out.println("Une requete demandée n'a pas de retour");
    }
    public NullQueryException(String message)
    {
        System.out.println(message);
    }
}
