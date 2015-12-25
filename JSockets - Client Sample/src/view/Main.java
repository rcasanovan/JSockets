/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import jsockets.client.SocketClient;
import jsockets.util.UtilFunctions;
import model.ListAlbum;
import model.StandardObject;
import model.User;

/**
 *
 * @author ricardocasanova
 */
public class Main
{
    public static void main(String [] args)
    {
        SocketClient clientRequest = new SocketClient();

        StandardObject objectToServer;
        byte [] answer;
        
        objectToServer = new StandardObject("1:rcasanov:1234", null);
        answer = clientRequest.executeRequestWithAnswer(objectToServer,"127.0.0.1", 7687);
        
        System.out.println("We receive from the server: " + UtilFunctions.byteArrayToString(answer));
        
        
        User newUser = new User("Ricardo", "Casanova");
        objectToServer = new StandardObject("2:", newUser);
        answer = clientRequest.executeRequestWithAnswer(objectToServer,"127.0.0.1", 7687);
                
        if (UtilFunctions.byteArrayToString(answer).compareTo("TRUE") == 0)
            System.out.println("User register process sucessfuly");
        else
            System.out.println("User register process error");

        
        objectToServer = new StandardObject("3:", null);
        answer = clientRequest.executeRequestWithAnswer(objectToServer,"127.0.0.1", 7687);
        
        try
        {
            UtilFunctions.createFileFromByteArray(answer, "/Users/ricardocasanova/clientFiles/", "test", "jpg", true);
        } 
        catch (Exception e)
        {
            
        }
        
        objectToServer = new StandardObject("4:", null);
        answer = clientRequest.executeRequestWithAnswer(objectToServer,"127.0.0.1", 7687);
        
        ListAlbum la = (ListAlbum) UtilFunctions.byteArrayToObject(answer);
        la.printAllAlbums();
    }
}
