/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import jsockets.server.logic.ServerLogic;
import jsockets.util.UtilFunctions;
import model.Album;
import model.ListAlbum;
import model.StandardObject;
import model.User;

/**
 *
 * @author ricardocasanova
 */
public class ManagementRequest implements ServerLogic
{
    private int operation;
    
    @Override
    public byte[] executeOperation(Object arg)
    {
        byte [] answer;
        answer = null;
        
        StandardObject objectFromClient = (StandardObject) arg;
        
        String[] protocolo = objectFromClient.getProtocol().split(":");

        if (UtilFunctions.isNumeric(protocolo[0])) {
            this.operation = Integer.parseInt(protocolo[0]);
        } else {
            throw new RuntimeException("Error. The operation is not supported");
        }
        
        switch (this.operation)
        {
            case 1:
                System.out.println("We receive from the user:");
                System.out.println("Username: " + protocolo[1]);
                System.out.println("Password: " + protocolo[2]);
                
                //This just an example.
                //We say "YES, the user is in the system"
                boolean isInTheSystem = true;
                
                if (isInTheSystem)
                {
                    //return TRUE (string)
                    answer = UtilFunctions.stringToByteArray("TRUE");
                }
                else {
                    answer = UtilFunctions.stringToByteArray("FALSE");
                }
                
                break;
                
            case 2:
                User user = (User) objectFromClient.getObject();
                System.out.println("We receive:");
                System.out.println("name: " + user.getName());
                System.out.println("lastname: " + user.getLastName());
                
                //This is an example
                //We return "YES, the user is saved in the system"
                answer = UtilFunctions.stringToByteArray("TRUE");
                
                break;
                
            case 3:
                answer = UtilFunctions.fileToByteArray("/Users/ricardocasanova/serverFiles/stevehome.jpg");
                
                break;
                
            case 4:
                ListAlbum la = new ListAlbum();
                la.addAlbum(new Album("Dig Out Your Soul"));
                la.addAlbum(new Album("Submarine"));
                la.addAlbum(new Album("With The Beatles"));
                la.addAlbum(new Album("The Death Of You And Me"));
                
                answer = UtilFunctions.objectToByteArray(la);
                break;
        }
        
        return answer;   
    }
}
